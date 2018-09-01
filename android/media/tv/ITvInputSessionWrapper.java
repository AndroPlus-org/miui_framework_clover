// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.Context;
import android.graphics.Rect;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;

public class ITvInputSessionWrapper extends ITvInputSession.Stub
    implements com.android.internal.os.HandlerCaller.Callback
{
    private final class TvInputEventReceiver extends InputEventReceiver
    {

        public void onInputEvent(InputEvent inputevent, int i)
        {
            boolean flag = true;
            if(ITvInputSessionWrapper._2D_get0(ITvInputSessionWrapper.this) == null)
            {
                finishInputEvent(inputevent, false);
                return;
            }
            i = ITvInputSessionWrapper._2D_get0(ITvInputSessionWrapper.this).dispatchInputEvent(inputevent, this);
            if(i != -1)
            {
                if(i != 1)
                    flag = false;
                finishInputEvent(inputevent, flag);
            }
        }

        final ITvInputSessionWrapper this$0;

        public TvInputEventReceiver(InputChannel inputchannel, Looper looper)
        {
            this$0 = ITvInputSessionWrapper.this;
            super(inputchannel, looper);
        }
    }


    static TvInputService.Session _2D_get0(ITvInputSessionWrapper itvinputsessionwrapper)
    {
        return itvinputsessionwrapper.mTvInputSessionImpl;
    }

    public ITvInputSessionWrapper(Context context, TvInputService.RecordingSession recordingsession)
    {
        mIsRecordingSession = true;
        mCaller = new HandlerCaller(context, null, this, true);
        mTvInputRecordingSessionImpl = recordingsession;
    }

    public ITvInputSessionWrapper(Context context, TvInputService.Session session, InputChannel inputchannel)
    {
        mIsRecordingSession = false;
        mCaller = new HandlerCaller(context, null, this, true);
        mTvInputSessionImpl = session;
        mChannel = inputchannel;
        if(inputchannel != null)
            mReceiver = new TvInputEventReceiver(inputchannel, context.getMainLooper());
    }

    public void appPrivateCommand(String s, Bundle bundle)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageOO(9, s, bundle));
    }

    public void createOverlayView(IBinder ibinder, Rect rect)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageOO(10, ibinder, rect));
    }

    public void dispatchSurfaceChanged(int i, int j, int k)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageIIII(4, i, j, k, 0));
    }

    public void executeMessage(Message message)
    {
        long l;
        while(mIsRecordingSession && mTvInputRecordingSessionImpl == null || !mIsRecordingSession && mTvInputSessionImpl == null) 
            return;
        l = System.nanoTime();
        message.what;
        JVM INSTR tableswitch 1 21: default 136
    //                   1 289
    //                   2 364
    //                   3 384
    //                   4 401
    //                   5 440
    //                   6 460
    //                   7 533
    //                   8 553
    //                   9 596
    //                   10 669
    //                   11 709
    //                   12 726
    //                   13 737
    //                   14 754
    //                   15 771
    //                   16 781
    //                   17 791
    //                   18 811
    //                   19 828
    //                   20 848
    //                   21 865;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L1:
        Log.w("TvInputSessionWrapper", (new StringBuilder()).append("Unhandled message code: ").append(message.what).toString());
_L24:
        l = (System.nanoTime() - l) / 0xf4240L;
        if(l <= 50L)
            break MISSING_BLOCK_LABEL_941;
        Log.w("TvInputSessionWrapper", (new StringBuilder()).append("Handling message (").append(message.what).append(") took too long time (duration=").append(l).append("ms)").toString());
        if(message.what == 6 && l > 2000L)
            throw new RuntimeException((new StringBuilder()).append("Too much time to handle tune request. (").append(l).append("ms > ").append(2000).append("ms) ").append("Consider handling the tune request in a separate thread.").toString());
        break; /* Loop/switch isn't completed */
_L2:
        if(mIsRecordingSession)
        {
            mTvInputRecordingSessionImpl.release();
            mTvInputRecordingSessionImpl = null;
        } else
        {
            mTvInputSessionImpl.release();
            mTvInputSessionImpl = null;
            if(mReceiver != null)
            {
                mReceiver.dispose();
                mReceiver = null;
            }
            if(mChannel != null)
            {
                mChannel.dispose();
                mChannel = null;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mTvInputSessionImpl.setMain(((Boolean)message.obj).booleanValue());
        continue; /* Loop/switch isn't completed */
_L4:
        mTvInputSessionImpl.setSurface((Surface)message.obj);
        continue; /* Loop/switch isn't completed */
_L5:
        SomeArgs someargs = (SomeArgs)message.obj;
        mTvInputSessionImpl.dispatchSurfaceChanged(someargs.argi1, someargs.argi2, someargs.argi3);
        someargs.recycle();
        continue; /* Loop/switch isn't completed */
_L6:
        mTvInputSessionImpl.setStreamVolume(((Float)message.obj).floatValue());
        continue; /* Loop/switch isn't completed */
_L7:
        SomeArgs someargs1 = (SomeArgs)message.obj;
        if(mIsRecordingSession)
            mTvInputRecordingSessionImpl.tune((Uri)someargs1.arg1, (Bundle)someargs1.arg2);
        else
            mTvInputSessionImpl.tune((Uri)someargs1.arg1, (Bundle)someargs1.arg2);
        someargs1.recycle();
        continue; /* Loop/switch isn't completed */
_L8:
        mTvInputSessionImpl.setCaptionEnabled(((Boolean)message.obj).booleanValue());
        continue; /* Loop/switch isn't completed */
_L9:
        SomeArgs someargs2 = (SomeArgs)message.obj;
        mTvInputSessionImpl.selectTrack(((Integer)someargs2.arg1).intValue(), (String)someargs2.arg2);
        someargs2.recycle();
        continue; /* Loop/switch isn't completed */
_L10:
        SomeArgs someargs3 = (SomeArgs)message.obj;
        if(mIsRecordingSession)
            mTvInputRecordingSessionImpl.appPrivateCommand((String)someargs3.arg1, (Bundle)someargs3.arg2);
        else
            mTvInputSessionImpl.appPrivateCommand((String)someargs3.arg1, (Bundle)someargs3.arg2);
        someargs3.recycle();
        continue; /* Loop/switch isn't completed */
_L11:
        SomeArgs someargs4 = (SomeArgs)message.obj;
        mTvInputSessionImpl.createOverlayView((IBinder)someargs4.arg1, (Rect)someargs4.arg2);
        someargs4.recycle();
        continue; /* Loop/switch isn't completed */
_L12:
        mTvInputSessionImpl.relayoutOverlayView((Rect)message.obj);
        continue; /* Loop/switch isn't completed */
_L13:
        mTvInputSessionImpl.removeOverlayView(true);
        continue; /* Loop/switch isn't completed */
_L14:
        mTvInputSessionImpl.unblockContent((String)message.obj);
        continue; /* Loop/switch isn't completed */
_L15:
        mTvInputSessionImpl.timeShiftPlay((Uri)message.obj);
        continue; /* Loop/switch isn't completed */
_L16:
        mTvInputSessionImpl.timeShiftPause();
        continue; /* Loop/switch isn't completed */
_L17:
        mTvInputSessionImpl.timeShiftResume();
        continue; /* Loop/switch isn't completed */
_L18:
        mTvInputSessionImpl.timeShiftSeekTo(((Long)message.obj).longValue());
        continue; /* Loop/switch isn't completed */
_L19:
        mTvInputSessionImpl.timeShiftSetPlaybackParams((PlaybackParams)message.obj);
        continue; /* Loop/switch isn't completed */
_L20:
        mTvInputSessionImpl.timeShiftEnablePositionTracking(((Boolean)message.obj).booleanValue());
        continue; /* Loop/switch isn't completed */
_L21:
        mTvInputRecordingSessionImpl.startRecording((Uri)message.obj);
        continue; /* Loop/switch isn't completed */
_L22:
        mTvInputRecordingSessionImpl.stopRecording();
        if(true) goto _L24; else goto _L23
_L23:
        if(l > 5000L)
            throw new RuntimeException((new StringBuilder()).append("Too much time to handle a request. (type=").append(message.what).append(", ").append(l).append("ms > ").append(5000).append("ms).").toString());
    }

    public void relayoutOverlayView(Rect rect)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(11, rect));
    }

    public void release()
    {
        if(!mIsRecordingSession)
            mTvInputSessionImpl.scheduleOverlayViewCleanup();
        mCaller.executeOrSendMessage(mCaller.obtainMessage(1));
    }

    public void removeOverlayView()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(12));
    }

    public void selectTrack(int i, String s)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageOO(8, Integer.valueOf(i), s));
    }

    public void setCaptionEnabled(boolean flag)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(7, Boolean.valueOf(flag)));
    }

    public void setMain(boolean flag)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(2, Boolean.valueOf(flag)));
    }

    public void setSurface(Surface surface)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(3, surface));
    }

    public final void setVolume(float f)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(5, Float.valueOf(f)));
    }

    public void startRecording(Uri uri)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(20, uri));
    }

    public void stopRecording()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(21));
    }

    public void timeShiftEnablePositionTracking(boolean flag)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(19, Boolean.valueOf(flag)));
    }

    public void timeShiftPause()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(15));
    }

    public void timeShiftPlay(Uri uri)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(14, uri));
    }

    public void timeShiftResume()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(16));
    }

    public void timeShiftSeekTo(long l)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(17, Long.valueOf(l)));
    }

    public void timeShiftSetPlaybackParams(PlaybackParams playbackparams)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(18, playbackparams));
    }

    public void tune(Uri uri, Bundle bundle)
    {
        mCaller.removeMessages(6);
        mCaller.executeOrSendMessage(mCaller.obtainMessageOO(6, uri, bundle));
    }

    public void unblockContent(String s)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(13, s));
    }

    private static final int DO_APP_PRIVATE_COMMAND = 9;
    private static final int DO_CREATE_OVERLAY_VIEW = 10;
    private static final int DO_DISPATCH_SURFACE_CHANGED = 4;
    private static final int DO_RELAYOUT_OVERLAY_VIEW = 11;
    private static final int DO_RELEASE = 1;
    private static final int DO_REMOVE_OVERLAY_VIEW = 12;
    private static final int DO_SELECT_TRACK = 8;
    private static final int DO_SET_CAPTION_ENABLED = 7;
    private static final int DO_SET_MAIN = 2;
    private static final int DO_SET_STREAM_VOLUME = 5;
    private static final int DO_SET_SURFACE = 3;
    private static final int DO_START_RECORDING = 20;
    private static final int DO_STOP_RECORDING = 21;
    private static final int DO_TIME_SHIFT_ENABLE_POSITION_TRACKING = 19;
    private static final int DO_TIME_SHIFT_PAUSE = 15;
    private static final int DO_TIME_SHIFT_PLAY = 14;
    private static final int DO_TIME_SHIFT_RESUME = 16;
    private static final int DO_TIME_SHIFT_SEEK_TO = 17;
    private static final int DO_TIME_SHIFT_SET_PLAYBACK_PARAMS = 18;
    private static final int DO_TUNE = 6;
    private static final int DO_UNBLOCK_CONTENT = 13;
    private static final int EXECUTE_MESSAGE_TIMEOUT_LONG_MILLIS = 5000;
    private static final int EXECUTE_MESSAGE_TIMEOUT_SHORT_MILLIS = 50;
    private static final int EXECUTE_MESSAGE_TUNE_TIMEOUT_MILLIS = 2000;
    private static final String TAG = "TvInputSessionWrapper";
    private final HandlerCaller mCaller;
    private InputChannel mChannel;
    private final boolean mIsRecordingSession;
    private TvInputEventReceiver mReceiver;
    private TvInputService.RecordingSession mTvInputRecordingSessionImpl;
    private TvInputService.Session mTvInputSessionImpl;
}
