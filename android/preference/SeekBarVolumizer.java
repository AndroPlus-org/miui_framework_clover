// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.app.NotificationManager;
import android.content.*;
import android.database.ContentObserver;
import android.media.*;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.widget.SeekBar;

public class SeekBarVolumizer
    implements android.widget.SeekBar.OnSeekBarChangeListener, android.os.Handler.Callback
{
    public static interface Callback
    {

        public abstract void onMuted(boolean flag, boolean flag1);

        public abstract void onProgressChanged(SeekBar seekbar, int i, boolean flag);

        public abstract void onSampleStarting(SeekBarVolumizer seekbarvolumizer);
    }

    private final class H extends Handler
    {

        public void handleMessage(Message message)
        {
            if(message.what == 1 && SeekBarVolumizer._2D_get8(SeekBarVolumizer.this) != null)
            {
                SeekBarVolumizer._2D_set1(SeekBarVolumizer.this, message.arg1);
                SeekBarVolumizer._2D_set0(SeekBarVolumizer.this, message.arg2);
                boolean flag = ((Boolean)message.obj).booleanValue();
                if(flag != SeekBarVolumizer._2D_get5(SeekBarVolumizer.this))
                {
                    SeekBarVolumizer._2D_set2(SeekBarVolumizer.this, flag);
                    if(SeekBarVolumizer._2D_get2(SeekBarVolumizer.this) != null)
                        SeekBarVolumizer._2D_get2(SeekBarVolumizer.this).onMuted(SeekBarVolumizer._2D_get5(SeekBarVolumizer.this), SeekBarVolumizer._2D_wrap1(SeekBarVolumizer.this));
                }
                updateSeekBar();
            }
        }

        public void postUpdateSlider(int i, int j, boolean flag)
        {
            obtainMessage(1, i, j, new Boolean(flag)).sendToTarget();
        }

        private static final int UPDATE_SLIDER = 1;
        final SeekBarVolumizer this$0;

        private H()
        {
            this$0 = SeekBarVolumizer.this;
            super();
        }

        H(H h)
        {
            this();
        }
    }

    private final class Observer extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            super.onChange(flag);
            SeekBarVolumizer._2D_wrap2(SeekBarVolumizer.this);
        }

        final SeekBarVolumizer this$0;

        public Observer(Handler handler)
        {
            this$0 = SeekBarVolumizer.this;
            super(handler);
        }
    }

    private final class Receiver extends BroadcastReceiver
    {

        private void updateVolumeSlider(int i, int j)
        {
            boolean flag;
            if(SeekBarVolumizer._2D_get7(SeekBarVolumizer.this))
                flag = SeekBarVolumizer._2D_wrap0(i);
            else
            if(i == SeekBarVolumizer._2D_get9(SeekBarVolumizer.this))
                flag = true;
            else
                flag = false;
            if(SeekBarVolumizer._2D_get8(SeekBarVolumizer.this) != null && flag && j != -1)
            {
                if(!SeekBarVolumizer._2D_get1(SeekBarVolumizer.this).isStreamMute(SeekBarVolumizer._2D_get9(SeekBarVolumizer.this)))
                {
                    if(j == 0)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = true;
                }
                SeekBarVolumizer._2D_get10(SeekBarVolumizer.this).postUpdateSlider(j, SeekBarVolumizer._2D_get4(SeekBarVolumizer.this), flag);
            }
        }

        public void onReceive(Context context, Intent intent)
        {
            context = intent.getAction();
            if(!"android.media.VOLUME_CHANGED_ACTION".equals(context)) goto _L2; else goto _L1
_L1:
            updateVolumeSlider(intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1), intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1));
_L4:
            return;
_L2:
            if("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION".equals(context))
            {
                if(SeekBarVolumizer._2D_get7(SeekBarVolumizer.this))
                    SeekBarVolumizer._2D_set3(SeekBarVolumizer.this, SeekBarVolumizer._2D_get1(SeekBarVolumizer.this).getRingerModeInternal());
                if(SeekBarVolumizer._2D_get0(SeekBarVolumizer.this))
                    SeekBarVolumizer._2D_wrap2(SeekBarVolumizer.this);
            } else
            if("android.media.STREAM_DEVICES_CHANGED_ACTION".equals(context))
            {
                int i = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                updateVolumeSlider(i, SeekBarVolumizer._2D_get1(SeekBarVolumizer.this).getStreamVolume(i));
            } else
            if("android.app.action.INTERRUPTION_FILTER_CHANGED".equals(context))
            {
                SeekBarVolumizer._2D_set4(SeekBarVolumizer.this, SeekBarVolumizer._2D_get6(SeekBarVolumizer.this).getZenMode());
                SeekBarVolumizer._2D_wrap2(SeekBarVolumizer.this);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void setListening(boolean flag)
        {
            if(mListening == flag)
                return;
            mListening = flag;
            if(flag)
            {
                IntentFilter intentfilter = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
                intentfilter.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
                intentfilter.addAction("android.app.action.INTERRUPTION_FILTER_CHANGED");
                intentfilter.addAction("android.media.STREAM_DEVICES_CHANGED_ACTION");
                SeekBarVolumizer._2D_get3(SeekBarVolumizer.this).registerReceiver(this, intentfilter);
            } else
            {
                SeekBarVolumizer._2D_get3(SeekBarVolumizer.this).unregisterReceiver(this);
            }
        }

        private boolean mListening;
        final SeekBarVolumizer this$0;

        private Receiver()
        {
            this$0 = SeekBarVolumizer.this;
            super();
        }

        Receiver(Receiver receiver)
        {
            this();
        }
    }


    static boolean _2D_get0(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mAffectedByRingerMode;
    }

    static AudioManager _2D_get1(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mAudioManager;
    }

    static H _2D_get10(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mUiHandler;
    }

    static Callback _2D_get2(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mCallback;
    }

    static Context _2D_get3(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mContext;
    }

    static int _2D_get4(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mLastAudibleStreamVolume;
    }

    static boolean _2D_get5(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mMuted;
    }

    static NotificationManager _2D_get6(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mNotificationManager;
    }

    static boolean _2D_get7(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mNotificationOrRing;
    }

    static SeekBar _2D_get8(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mSeekBar;
    }

    static int _2D_get9(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.mStreamType;
    }

    static int _2D_set0(SeekBarVolumizer seekbarvolumizer, int i)
    {
        seekbarvolumizer.mLastAudibleStreamVolume = i;
        return i;
    }

    static int _2D_set1(SeekBarVolumizer seekbarvolumizer, int i)
    {
        seekbarvolumizer.mLastProgress = i;
        return i;
    }

    static boolean _2D_set2(SeekBarVolumizer seekbarvolumizer, boolean flag)
    {
        seekbarvolumizer.mMuted = flag;
        return flag;
    }

    static int _2D_set3(SeekBarVolumizer seekbarvolumizer, int i)
    {
        seekbarvolumizer.mRingerMode = i;
        return i;
    }

    static int _2D_set4(SeekBarVolumizer seekbarvolumizer, int i)
    {
        seekbarvolumizer.mZenMode = i;
        return i;
    }

    static boolean _2D_wrap0(int i)
    {
        return isNotificationOrRing(i);
    }

    static boolean _2D_wrap1(SeekBarVolumizer seekbarvolumizer)
    {
        return seekbarvolumizer.isZenMuted();
    }

    static void _2D_wrap2(SeekBarVolumizer seekbarvolumizer)
    {
        seekbarvolumizer.updateSlider();
    }

    public SeekBarVolumizer(Context context, int i, Uri uri, Callback callback)
    {
        mLastProgress = -1;
        mVolumeBeforeMute = -1;
        mContext = context;
        mAudioManager = (AudioManager)context.getSystemService(android/media/AudioManager);
        mNotificationManager = (NotificationManager)context.getSystemService(android/app/NotificationManager);
        mStreamType = i;
        mAffectedByRingerMode = mAudioManager.isStreamAffectedByRingerMode(mStreamType);
        mNotificationOrRing = isNotificationOrRing(mStreamType);
        if(mNotificationOrRing)
            mRingerMode = mAudioManager.getRingerModeInternal();
        mZenMode = mNotificationManager.getZenMode();
        mMaxStreamVolume = mAudioManager.getStreamMaxVolume(mStreamType);
        mCallback = callback;
        mOriginalStreamVolume = mAudioManager.getStreamVolume(mStreamType);
        mLastAudibleStreamVolume = mAudioManager.getLastAudibleStreamVolume(mStreamType);
        mMuted = mAudioManager.isStreamMute(mStreamType);
        if(mCallback != null)
            mCallback.onMuted(mMuted, isZenMuted());
        context = uri;
        if(uri == null)
            if(mStreamType == 2)
                context = android.provider.Settings.System.DEFAULT_RINGTONE_URI;
            else
            if(mStreamType == 5)
                context = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI;
            else
                context = android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI;
        mDefaultUri = context;
    }

    private static boolean isNotificationOrRing(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 2)
            if(i == 5)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private boolean isZenMuted()
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((!mNotificationOrRing || mZenMode != 3) && mZenMode != 2)
            flag = false;
        return flag;
    }

    private void onInitSample()
    {
        this;
        JVM INSTR monitorenter ;
        mRingtone = RingtoneManager.getRingtone(mContext, mDefaultUri);
        if(mRingtone != null)
            mRingtone.setStreamType(mStreamType);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void onStartSample()
    {
        if(isSamplePlaying()) goto _L2; else goto _L1
_L1:
        if(mCallback != null)
            mCallback.onSampleStarting(this);
        this;
        JVM INSTR monitorenter ;
        Ringtone ringtone = mRingtone;
        if(ringtone == null)
            break MISSING_BLOCK_LABEL_76;
        Ringtone ringtone1 = mRingtone;
        android.media.AudioAttributes.Builder builder = JVM INSTR new #237 <Class android.media.AudioAttributes$Builder>;
        builder.android.media.AudioAttributes.Builder(mRingtone.getAudioAttributes());
        ringtone1.setAudioAttributes(builder.setFlags(128).build());
        mRingtone.play();
_L3:
        this;
        JVM INSTR monitorexit ;
_L2:
        return;
        Object obj;
        obj;
        StringBuilder stringbuilder = JVM INSTR new #259 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("SeekBarVolumizer", stringbuilder.append("Error playing ringtone, stream ").append(mStreamType).toString(), ((Throwable) (obj)));
          goto _L3
        obj;
        throw obj;
    }

    private void onStopSample()
    {
        this;
        JVM INSTR monitorenter ;
        if(mRingtone != null)
            mRingtone.stop();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void postSetVolume(int i)
    {
        if(mHandler == null)
        {
            return;
        } else
        {
            mLastProgress = i;
            mHandler.removeMessages(0);
            mHandler.sendMessage(mHandler.obtainMessage(0));
            return;
        }
    }

    private void postStartSample()
    {
        if(mHandler == null)
            return;
        mHandler.removeMessages(1);
        Handler handler = mHandler;
        Message message = mHandler.obtainMessage(1);
        int i;
        if(isSamplePlaying())
            i = 1000;
        else
            i = 0;
        handler.sendMessageDelayed(message, i);
    }

    private void postStopSample()
    {
        if(mHandler == null)
        {
            return;
        } else
        {
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
            mHandler.sendMessage(mHandler.obtainMessage(2));
            return;
        }
    }

    private void updateSlider()
    {
        if(mSeekBar != null && mAudioManager != null)
        {
            int i = mAudioManager.getStreamVolume(mStreamType);
            int j = mAudioManager.getLastAudibleStreamVolume(mStreamType);
            boolean flag = mAudioManager.isStreamMute(mStreamType);
            mUiHandler.postUpdateSlider(i, j, flag);
        }
    }

    public void changeVolumeBy(int i)
    {
        mSeekBar.incrementProgressBy(i);
        postSetVolume(mSeekBar.getProgress());
        postStartSample();
        mVolumeBeforeMute = -1;
    }

    public SeekBar getSeekBar()
    {
        return mSeekBar;
    }

    public boolean handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 3: default 36
    //                   0 67
    //                   1 147
    //                   2 154
    //                   3 161;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Log.e("SeekBarVolumizer", (new StringBuilder()).append("invalid SeekBarVolumizer message: ").append(message.what).toString());
_L10:
        return true;
_L2:
        if(!mMuted || mLastProgress <= 0) goto _L7; else goto _L6
_L6:
        mAudioManager.adjustStreamVolume(mStreamType, 100, 0);
_L8:
        mAudioManager.setStreamVolume(mStreamType, mLastProgress, 1024);
        continue; /* Loop/switch isn't completed */
_L7:
        if(!mMuted && mLastProgress == 0)
            mAudioManager.adjustStreamVolume(mStreamType, -100, 0);
        if(true) goto _L8; else goto _L3
_L3:
        onStartSample();
        continue; /* Loop/switch isn't completed */
_L4:
        onStopSample();
        continue; /* Loop/switch isn't completed */
_L5:
        onInitSample();
        if(true) goto _L10; else goto _L9
_L9:
    }

    public boolean isSamplePlaying()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mRingtone == null)
            break MISSING_BLOCK_LABEL_21;
        flag = mRingtone.isPlaying();
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
        flag = false;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void muteVolume()
    {
        if(mVolumeBeforeMute != -1)
        {
            mSeekBar.setProgress(mVolumeBeforeMute, true);
            postSetVolume(mVolumeBeforeMute);
            postStartSample();
            mVolumeBeforeMute = -1;
        } else
        {
            mVolumeBeforeMute = mSeekBar.getProgress();
            mSeekBar.setProgress(0, true);
            postStopSample();
            postSetVolume(0);
        }
    }

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        if(flag)
            postSetVolume(i);
        if(mCallback != null)
            mCallback.onProgressChanged(seekbar, i, flag);
    }

    public void onRestoreInstanceState(VolumePreference.VolumeStore volumestore)
    {
        if(volumestore.volume != -1)
        {
            mOriginalStreamVolume = volumestore.originalVolume;
            mLastProgress = volumestore.volume;
            postSetVolume(mLastProgress);
        }
    }

    public void onSaveInstanceState(VolumePreference.VolumeStore volumestore)
    {
        if(mLastProgress >= 0)
        {
            volumestore.volume = mLastProgress;
            volumestore.originalVolume = mOriginalStreamVolume;
        }
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        postStartSample();
    }

    public void revertVolume()
    {
        mAudioManager.setStreamVolume(mStreamType, mOriginalStreamVolume, 0);
    }

    public void setSeekBar(SeekBar seekbar)
    {
        if(mSeekBar != null)
            mSeekBar.setOnSeekBarChangeListener(null);
        mSeekBar = seekbar;
        mSeekBar.setOnSeekBarChangeListener(null);
        mSeekBar.setMax(mMaxStreamVolume);
        updateSeekBar();
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void start()
    {
        if(mHandler != null)
        {
            return;
        } else
        {
            HandlerThread handlerthread = new HandlerThread("SeekBarVolumizer.CallbackHandler");
            handlerthread.start();
            mHandler = new Handler(handlerthread.getLooper(), this);
            mHandler.sendEmptyMessage(3);
            mVolumeObserver = new Observer(mHandler);
            mContext.getContentResolver().registerContentObserver(android.provider.Settings.System.getUriFor(android.provider.Settings.System.VOLUME_SETTINGS[mStreamType]), false, mVolumeObserver);
            mReceiver.setListening(true);
            return;
        }
    }

    public void startSample()
    {
        postStartSample();
    }

    public void stop()
    {
        if(mHandler == null)
        {
            return;
        } else
        {
            postStopSample();
            mContext.getContentResolver().unregisterContentObserver(mVolumeObserver);
            mReceiver.setListening(false);
            mSeekBar.setOnSeekBarChangeListener(null);
            mHandler.getLooper().quitSafely();
            mHandler = null;
            mVolumeObserver = null;
            return;
        }
    }

    public void stopSample()
    {
        postStopSample();
    }

    protected void updateSeekBar()
    {
        boolean flag = isZenMuted();
        mSeekBar.setEnabled(flag ^ true);
        if(flag)
            mSeekBar.setProgress(mLastAudibleStreamVolume, true);
        else
        if(mNotificationOrRing && mRingerMode == 1)
            mSeekBar.setProgress(0, true);
        else
        if(mMuted)
        {
            mSeekBar.setProgress(0, true);
        } else
        {
            SeekBar seekbar = mSeekBar;
            int i;
            if(mLastProgress > -1)
                i = mLastProgress;
            else
                i = mOriginalStreamVolume;
            seekbar.setProgress(i, true);
        }
    }

    private static final int CHECK_RINGTONE_PLAYBACK_DELAY_MS = 1000;
    private static final int MSG_INIT_SAMPLE = 3;
    private static final int MSG_SET_STREAM_VOLUME = 0;
    private static final int MSG_START_SAMPLE = 1;
    private static final int MSG_STOP_SAMPLE = 2;
    private static final String TAG = "SeekBarVolumizer";
    private boolean mAffectedByRingerMode;
    private final AudioManager mAudioManager;
    private final Callback mCallback;
    private final Context mContext;
    private final Uri mDefaultUri;
    private Handler mHandler;
    private int mLastAudibleStreamVolume;
    private int mLastProgress;
    private final int mMaxStreamVolume;
    private boolean mMuted;
    private final NotificationManager mNotificationManager;
    private boolean mNotificationOrRing;
    private int mOriginalStreamVolume;
    private final Receiver mReceiver = new Receiver(null);
    private int mRingerMode;
    private Ringtone mRingtone;
    private SeekBar mSeekBar;
    private final int mStreamType;
    private final H mUiHandler = new H(null);
    private int mVolumeBeforeMute;
    private Observer mVolumeObserver;
    private int mZenMode;
}
