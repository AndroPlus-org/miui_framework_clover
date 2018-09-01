// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.content.Intent;
import android.graphics.Rect;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.*;
import android.view.*;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.media.tv:
//            ITvInputManager, TvInputInfo, TvContentRating, ITvInputClient, 
//            TvStreamConfig, DvbDeviceInfo, ITvInputHardware, TvTrackInfo

public final class TvInputManager
{
    public static final class Hardware
    {

        static ITvInputHardware _2D_wrap0(Hardware hardware)
        {
            return hardware.getInterface();
        }

        private ITvInputHardware getInterface()
        {
            return mInterface;
        }

        public boolean dispatchKeyEventToHdmi(KeyEvent keyevent)
        {
            boolean flag;
            try
            {
                flag = mInterface.dispatchKeyEventToHdmi(keyevent);
            }
            // Misplaced declaration of an exception variable
            catch(KeyEvent keyevent)
            {
                throw new RuntimeException(keyevent);
            }
            return flag;
        }

        public void overrideAudioSink(int i, String s, int j, int k, int l)
        {
            try
            {
                mInterface.overrideAudioSink(i, s, j, k, l);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new RuntimeException(s);
            }
        }

        public void setStreamVolume(float f)
        {
            try
            {
                mInterface.setStreamVolume(f);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeException(remoteexception);
            }
        }

        public boolean setSurface(Surface surface, TvStreamConfig tvstreamconfig)
        {
            boolean flag;
            try
            {
                flag = mInterface.setSurface(surface, tvstreamconfig);
            }
            // Misplaced declaration of an exception variable
            catch(Surface surface)
            {
                throw new RuntimeException(surface);
            }
            return flag;
        }

        private final ITvInputHardware mInterface;

        private Hardware(ITvInputHardware itvinputhardware)
        {
            mInterface = itvinputhardware;
        }

        Hardware(ITvInputHardware itvinputhardware, Hardware hardware)
        {
            this(itvinputhardware);
        }
    }

    public static abstract class HardwareCallback
    {

        public abstract void onReleased();

        public abstract void onStreamConfigChanged(TvStreamConfig atvstreamconfig[]);

        public HardwareCallback()
        {
        }
    }

    public static final class Session
    {

        static void _2D_wrap0(Session session, PendingEvent pendingevent)
        {
            session.recyclePendingEventLocked(pendingevent);
        }

        static void _2D_wrap1(Session session)
        {
            session.releaseInternal();
        }

        static void _2D_wrap2(Session session, PendingEvent pendingevent)
        {
            session.sendInputEventAndReportResultOnMainLooper(pendingevent);
        }

        private boolean containsTrack(List list, String s)
        {
            for(list = list.iterator(); list.hasNext();)
                if(((TvTrackInfo)list.next()).getId().equals(s))
                    return true;

            return false;
        }

        private void flushPendingEventsLocked()
        {
            mHandler.removeMessages(3);
            int i = mPendingEvents.size();
            for(int j = 0; j < i; j++)
            {
                int k = mPendingEvents.keyAt(j);
                Message message = mHandler.obtainMessage(3, k, 0);
                message.setAsynchronous(true);
                message.sendToTarget();
            }

        }

        private PendingEvent obtainPendingEventLocked(InputEvent inputevent, Object obj, FinishedInputEventCallback finishedinputeventcallback, Handler handler)
        {
            PendingEvent pendingevent = (PendingEvent)mPendingEventPool.acquire();
            PendingEvent pendingevent1 = pendingevent;
            if(pendingevent == null)
                pendingevent1 = new PendingEvent(null);
            pendingevent1.mEvent = inputevent;
            pendingevent1.mEventToken = obj;
            pendingevent1.mCallback = finishedinputeventcallback;
            pendingevent1.mEventHandler = handler;
            return pendingevent1;
        }

        private void recyclePendingEventLocked(PendingEvent pendingevent)
        {
            pendingevent.recycle();
            mPendingEventPool.release(pendingevent);
        }

        private void releaseInternal()
        {
            mToken = null;
            InputEventHandler inputeventhandler = mHandler;
            inputeventhandler;
            JVM INSTR monitorenter ;
            if(mChannel != null)
            {
                if(mSender != null)
                {
                    flushPendingEventsLocked();
                    mSender.dispose();
                    mSender = null;
                }
                mChannel.dispose();
                mChannel = null;
            }
            inputeventhandler;
            JVM INSTR monitorexit ;
            Object obj = mSessionCallbackRecordMap;
            obj;
            JVM INSTR monitorenter ;
            mSessionCallbackRecordMap.remove(mSeq);
            obj;
            JVM INSTR monitorexit ;
            return;
            obj;
            throw obj;
            Exception exception;
            exception;
            throw exception;
        }

        private void sendInputEventAndReportResultOnMainLooper(PendingEvent pendingevent)
        {
            InputEventHandler inputeventhandler = mHandler;
            inputeventhandler;
            JVM INSTR monitorenter ;
            int i = sendInputEventOnMainLooperLocked(pendingevent);
            if(i != -1)
                break MISSING_BLOCK_LABEL_21;
            inputeventhandler;
            JVM INSTR monitorexit ;
            return;
            inputeventhandler;
            JVM INSTR monitorexit ;
            invokeFinishedInputEventCallback(pendingevent, false);
            return;
            pendingevent;
            throw pendingevent;
        }

        private int sendInputEventOnMainLooperLocked(PendingEvent pendingevent)
        {
            if(mChannel != null)
            {
                if(mSender == null)
                    mSender = new TvInputEventSender(mChannel, mHandler.getLooper());
                InputEvent inputevent = pendingevent.mEvent;
                int i = inputevent.getSequenceNumber();
                if(mSender.sendInputEvent(i, inputevent))
                {
                    mPendingEvents.put(i, pendingevent);
                    pendingevent = mHandler.obtainMessage(2, pendingevent);
                    pendingevent.setAsynchronous(true);
                    mHandler.sendMessageDelayed(pendingevent, 2500L);
                    return -1;
                }
                Log.w("TvInputManager", (new StringBuilder()).append("Unable to send input event to session: ").append(mToken).append(" dropping:").append(inputevent).toString());
            }
            return 0;
        }

        void createOverlayView(View view, Rect rect)
        {
            Preconditions.checkNotNull(view);
            Preconditions.checkNotNull(rect);
            if(view.getWindowToken() == null)
                throw new IllegalStateException("view must be attached to a window");
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.createOverlayView(mToken, view.getWindowToken(), rect, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw view.rethrowFromSystemServer();
            }
        }

        public int dispatchInputEvent(InputEvent inputevent, Object obj, FinishedInputEventCallback finishedinputeventcallback, Handler handler)
        {
            Preconditions.checkNotNull(inputevent);
            Preconditions.checkNotNull(finishedinputeventcallback);
            Preconditions.checkNotNull(handler);
            InputEventHandler inputeventhandler = mHandler;
            inputeventhandler;
            JVM INSTR monitorenter ;
            InputChannel inputchannel = mChannel;
            if(inputchannel != null)
                break MISSING_BLOCK_LABEL_41;
            inputeventhandler;
            JVM INSTR monitorexit ;
            return 0;
            int i;
            inputevent = obtainPendingEventLocked(inputevent, obj, finishedinputeventcallback, handler);
            if(Looper.myLooper() != Looper.getMainLooper())
                break MISSING_BLOCK_LABEL_73;
            i = sendInputEventOnMainLooperLocked(inputevent);
            inputeventhandler;
            JVM INSTR monitorexit ;
            return i;
            inputevent = mHandler.obtainMessage(1, inputevent);
            inputevent.setAsynchronous(true);
            mHandler.sendMessage(inputevent);
            inputeventhandler;
            JVM INSTR monitorexit ;
            return -1;
            inputevent;
            throw inputevent;
        }

        public void dispatchSurfaceChanged(int i, int j, int k)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.dispatchSurfaceChanged(mToken, i, j, k, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void finishedInputEvent(int i, boolean flag, boolean flag1)
        {
            InputEventHandler inputeventhandler = mHandler;
            inputeventhandler;
            JVM INSTR monitorenter ;
            i = mPendingEvents.indexOfKey(i);
            if(i >= 0)
                break MISSING_BLOCK_LABEL_26;
            inputeventhandler;
            JVM INSTR monitorexit ;
            return;
            PendingEvent pendingevent;
            pendingevent = (PendingEvent)mPendingEvents.valueAt(i);
            mPendingEvents.removeAt(i);
            if(!flag1) goto _L2; else goto _L1
_L1:
            StringBuilder stringbuilder = JVM INSTR new #272 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w("TvInputManager", stringbuilder.append("Timeout waiting for session to handle input event after 2500 ms: ").append(mToken).toString());
_L4:
            inputeventhandler;
            JVM INSTR monitorexit ;
            invokeFinishedInputEventCallback(pendingevent, flag);
            return;
_L2:
            mHandler.removeMessages(2, pendingevent);
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        public String getSelectedTrack(int i)
        {
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            if(i != 0)
                break MISSING_BLOCK_LABEL_20;
            String s = mSelectedAudioTrackId;
            obj;
            JVM INSTR monitorexit ;
            return s;
            if(i != 1)
                break MISSING_BLOCK_LABEL_34;
            s = mSelectedVideoTrackId;
            obj;
            JVM INSTR monitorexit ;
            return s;
            if(i != 2)
                break MISSING_BLOCK_LABEL_49;
            s = mSelectedSubtitleTrackId;
            obj;
            JVM INSTR monitorexit ;
            return s;
            throw new IllegalArgumentException((new StringBuilder()).append("invalid type: ").append(i).toString());
            Exception exception;
            exception;
            throw exception;
        }

        IBinder getToken()
        {
            return mToken;
        }

        public List getTracks(int i)
        {
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            if(i != 0)
                break MISSING_BLOCK_LABEL_40;
            Object obj1 = mAudioTracks;
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_24;
            obj;
            JVM INSTR monitorexit ;
            return null;
            obj1 = new ArrayList(mAudioTracks);
            obj;
            JVM INSTR monitorexit ;
            return ((List) (obj1));
            if(i != 1)
                break MISSING_BLOCK_LABEL_74;
            obj1 = mVideoTracks;
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_58;
            obj;
            JVM INSTR monitorexit ;
            return null;
            obj1 = new ArrayList(mVideoTracks);
            obj;
            JVM INSTR monitorexit ;
            return ((List) (obj1));
            if(i != 2)
                break MISSING_BLOCK_LABEL_109;
            obj1 = mSubtitleTracks;
            if(obj1 != null)
                break MISSING_BLOCK_LABEL_92;
            obj;
            JVM INSTR monitorexit ;
            return null;
            obj1 = new ArrayList(mSubtitleTracks);
            obj;
            JVM INSTR monitorexit ;
            return ((List) (obj1));
            throw new IllegalArgumentException((new StringBuilder()).append("invalid type: ").append(i).toString());
            Exception exception;
            exception;
            throw exception;
        }

        TvTrackInfo getVideoTrackToNotify()
        {
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            TvTrackInfo tvtrackinfo;
            if(mVideoTracks.isEmpty() || mSelectedVideoTrackId == null)
                break MISSING_BLOCK_LABEL_115;
            Iterator iterator = mVideoTracks.iterator();
            int i;
            int j;
            do
            {
                do
                {
                    if(!iterator.hasNext())
                        break MISSING_BLOCK_LABEL_115;
                    tvtrackinfo = (TvTrackInfo)iterator.next();
                } while(!tvtrackinfo.getId().equals(mSelectedVideoTrackId));
                i = tvtrackinfo.getVideoWidth();
                j = tvtrackinfo.getVideoHeight();
            } while(mVideoWidth == i && mVideoHeight == j);
            mVideoWidth = i;
            mVideoHeight = j;
            obj;
            JVM INSTR monitorexit ;
            return tvtrackinfo;
            obj;
            JVM INSTR monitorexit ;
            return null;
            Exception exception;
            exception;
            throw exception;
        }

        void invokeFinishedInputEventCallback(PendingEvent pendingevent, boolean flag)
        {
            pendingevent.mHandled = flag;
            if(pendingevent.mEventHandler.getLooper().isCurrentThread())
            {
                pendingevent.run();
            } else
            {
                pendingevent = Message.obtain(pendingevent.mEventHandler, pendingevent);
                pendingevent.setAsynchronous(true);
                pendingevent.sendToTarget();
            }
        }

        void relayoutOverlayView(Rect rect)
        {
            Preconditions.checkNotNull(rect);
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.relayoutOverlayView(mToken, rect, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Rect rect)
            {
                throw rect.rethrowFromSystemServer();
            }
        }

        public void release()
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.releaseSession(mToken, mUserId);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            releaseInternal();
        }

        void removeOverlayView()
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.removeOverlayView(mToken, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void selectTrack(int i, String s)
        {
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            if(i != 0)
                break MISSING_BLOCK_LABEL_64;
            if(s == null)
                break MISSING_BLOCK_LABEL_220;
            if(!(containsTrack(mAudioTracks, s) ^ true))
                break MISSING_BLOCK_LABEL_220;
            StringBuilder stringbuilder = JVM INSTR new #272 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w("TvInputManager", stringbuilder.append("Invalid audio trackId: ").append(s).toString());
            obj;
            JVM INSTR monitorexit ;
            return;
            if(i != 1)
                break MISSING_BLOCK_LABEL_122;
            if(s == null)
                break MISSING_BLOCK_LABEL_220;
            if(!(containsTrack(mVideoTracks, s) ^ true))
                break MISSING_BLOCK_LABEL_220;
            StringBuilder stringbuilder1 = JVM INSTR new #272 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.w("TvInputManager", stringbuilder1.append("Invalid video trackId: ").append(s).toString());
            obj;
            JVM INSTR monitorexit ;
            return;
            if(i != 2)
                break MISSING_BLOCK_LABEL_180;
            if(s == null)
                break MISSING_BLOCK_LABEL_220;
            if(!(containsTrack(mSubtitleTracks, s) ^ true))
                break MISSING_BLOCK_LABEL_220;
            StringBuilder stringbuilder2 = JVM INSTR new #272 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            Log.w("TvInputManager", stringbuilder2.append("Invalid subtitle trackId: ").append(s).toString());
            obj;
            JVM INSTR monitorexit ;
            return;
            IllegalArgumentException illegalargumentexception = JVM INSTR new #369 <Class IllegalArgumentException>;
            s = JVM INSTR new #272 <Class StringBuilder>;
            s.StringBuilder();
            illegalargumentexception.IllegalArgumentException(s.append("invalid type: ").append(i).toString());
            throw illegalargumentexception;
            s;
            obj;
            JVM INSTR monitorexit ;
            throw s;
            obj;
            JVM INSTR monitorexit ;
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.selectTrack(mToken, i, s, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
        }

        public void sendAppPrivateCommand(String s, Bundle bundle)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.sendAppPrivateCommand(mToken, s, bundle, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
        }

        public void setCaptionEnabled(boolean flag)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.setCaptionEnabled(mToken, flag, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void setMain()
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.setMainSession(mToken, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public void setStreamVolume(float f)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            if(f < 0.0F || f > 1.0F)
                try
                {
                    IllegalArgumentException illegalargumentexception = JVM INSTR new #369 <Class IllegalArgumentException>;
                    illegalargumentexception.IllegalArgumentException("volume should be between 0.0f and 1.0f");
                    throw illegalargumentexception;
                }
                catch(RemoteException remoteexception)
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
            mService.setVolume(mToken, f, mUserId);
            return;
        }

        public void setSurface(Surface surface)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.setSurface(mToken, surface, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Surface surface)
            {
                throw surface.rethrowFromSystemServer();
            }
        }

        void startRecording(Uri uri)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.startRecording(mToken, uri, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                throw uri.rethrowFromSystemServer();
            }
        }

        void stopRecording()
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.stopRecording(mToken, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void timeShiftEnablePositionTracking(boolean flag)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.timeShiftEnablePositionTracking(mToken, flag, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void timeShiftPause()
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.timeShiftPause(mToken, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void timeShiftPlay(Uri uri)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.timeShiftPlay(mToken, uri, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                throw uri.rethrowFromSystemServer();
            }
        }

        void timeShiftResume()
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.timeShiftResume(mToken, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void timeShiftSeekTo(long l)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.timeShiftSeekTo(mToken, l, mUserId);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        void timeShiftSetPlaybackParams(PlaybackParams playbackparams)
        {
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.timeShiftSetPlaybackParams(mToken, playbackparams, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(PlaybackParams playbackparams)
            {
                throw playbackparams.rethrowFromSystemServer();
            }
        }

        public void tune(Uri uri)
        {
            tune(uri, null);
        }

        public void tune(Uri uri, Bundle bundle)
        {
            Preconditions.checkNotNull(uri);
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            mAudioTracks.clear();
            mVideoTracks.clear();
            mSubtitleTracks.clear();
            mSelectedAudioTrackId = null;
            mSelectedVideoTrackId = null;
            mSelectedSubtitleTrackId = null;
            mVideoWidth = 0;
            mVideoHeight = 0;
            obj;
            JVM INSTR monitorexit ;
            try
            {
                mService.tune(mToken, uri, bundle, mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                throw uri.rethrowFromSystemServer();
            }
            uri;
            throw uri;
        }

        void unblockContent(TvContentRating tvcontentrating)
        {
            Preconditions.checkNotNull(tvcontentrating);
            if(mToken == null)
            {
                Log.w("TvInputManager", "The session has been already released");
                return;
            }
            try
            {
                mService.unblockContent(mToken, tvcontentrating.flattenToString(), mUserId);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(TvContentRating tvcontentrating)
            {
                throw tvcontentrating.rethrowFromSystemServer();
            }
        }

        boolean updateTrackSelection(int i, String s)
        {
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            if(i != 0)
                break MISSING_BLOCK_LABEL_33;
            if(!(TextUtils.equals(s, mSelectedAudioTrackId) ^ true))
                break MISSING_BLOCK_LABEL_33;
            mSelectedAudioTrackId = s;
            obj;
            JVM INSTR monitorexit ;
            return true;
            if(i != 1)
                break MISSING_BLOCK_LABEL_60;
            if(!(TextUtils.equals(s, mSelectedVideoTrackId) ^ true))
                break MISSING_BLOCK_LABEL_60;
            mSelectedVideoTrackId = s;
            obj;
            JVM INSTR monitorexit ;
            return true;
            if(i != 2)
                break MISSING_BLOCK_LABEL_87;
            if(!(TextUtils.equals(s, mSelectedSubtitleTrackId) ^ true))
                break MISSING_BLOCK_LABEL_87;
            mSelectedSubtitleTrackId = s;
            return true;
            obj;
            JVM INSTR monitorexit ;
            return false;
            s;
            throw s;
        }

        boolean updateTracks(List list)
        {
            boolean flag = true;
            Object obj = mMetadataLock;
            obj;
            JVM INSTR monitorenter ;
            Iterator iterator;
            mAudioTracks.clear();
            mVideoTracks.clear();
            mSubtitleTracks.clear();
            iterator = list.iterator();
_L3:
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_135;
            list = (TvTrackInfo)iterator.next();
            if(list.getType() != 0) goto _L2; else goto _L1
_L1:
            mAudioTracks.add(list);
              goto _L3
            list;
            throw list;
_L2:
            if(list.getType() != 1) goto _L5; else goto _L4
_L4:
            mVideoTracks.add(list);
              goto _L3
_L5:
            if(list.getType() != 2) goto _L3; else goto _L6
_L6:
            mSubtitleTracks.add(list);
              goto _L3
            boolean flag1 = flag;
            if(!mAudioTracks.isEmpty())
                break MISSING_BLOCK_LABEL_184;
            flag1 = flag;
            if(mVideoTracks.isEmpty() ^ true)
                break MISSING_BLOCK_LABEL_184;
            flag1 = mSubtitleTracks.isEmpty();
            flag1 ^= true;
            obj;
            JVM INSTR monitorexit ;
            return flag1;
        }

        static final int DISPATCH_HANDLED = 1;
        static final int DISPATCH_IN_PROGRESS = -1;
        static final int DISPATCH_NOT_HANDLED = 0;
        private static final long INPUT_SESSION_NOT_RESPONDING_TIMEOUT = 2500L;
        private final List mAudioTracks;
        private InputChannel mChannel;
        private final InputEventHandler mHandler;
        private final Object mMetadataLock;
        private final android.util.Pools.Pool mPendingEventPool;
        private final SparseArray mPendingEvents;
        private String mSelectedAudioTrackId;
        private String mSelectedSubtitleTrackId;
        private String mSelectedVideoTrackId;
        private TvInputEventSender mSender;
        private final int mSeq;
        private final ITvInputManager mService;
        private final SparseArray mSessionCallbackRecordMap;
        private final List mSubtitleTracks;
        private IBinder mToken;
        private final int mUserId;
        private int mVideoHeight;
        private final List mVideoTracks;
        private int mVideoWidth;

        private Session(IBinder ibinder, InputChannel inputchannel, ITvInputManager itvinputmanager, int i, int j, SparseArray sparsearray)
        {
            mHandler = new InputEventHandler(Looper.getMainLooper());
            mPendingEventPool = new android.util.Pools.SimplePool(20);
            mPendingEvents = new SparseArray(20);
            mMetadataLock = new Object();
            mAudioTracks = new ArrayList();
            mVideoTracks = new ArrayList();
            mSubtitleTracks = new ArrayList();
            mToken = ibinder;
            mChannel = inputchannel;
            mService = itvinputmanager;
            mUserId = i;
            mSeq = j;
            mSessionCallbackRecordMap = sparsearray;
        }

        Session(IBinder ibinder, InputChannel inputchannel, ITvInputManager itvinputmanager, int i, int j, SparseArray sparsearray, Session session)
        {
            this(ibinder, inputchannel, itvinputmanager, i, j, sparsearray);
        }
    }

    public static interface Session.FinishedInputEventCallback
    {

        public abstract void onFinishedInputEvent(Object obj, boolean flag);
    }

    private final class Session.InputEventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                return;

            case 1: // '\001'
                Session._2D_wrap2(Session.this, (Session.PendingEvent)message.obj);
                return;

            case 2: // '\002'
                finishedInputEvent(message.arg1, false, true);
                return;

            case 3: // '\003'
                finishedInputEvent(message.arg1, false, false);
                break;
            }
        }

        public static final int MSG_FLUSH_INPUT_EVENT = 3;
        public static final int MSG_SEND_INPUT_EVENT = 1;
        public static final int MSG_TIMEOUT_INPUT_EVENT = 2;
        final Session this$1;

        Session.InputEventHandler(Looper looper)
        {
            this$1 = Session.this;
            super(looper, null, true);
        }
    }

    private final class Session.PendingEvent
        implements Runnable
    {

        public void recycle()
        {
            mEvent = null;
            mEventToken = null;
            mCallback = null;
            mEventHandler = null;
            mHandled = false;
        }

        public void run()
        {
            mCallback.onFinishedInputEvent(mEventToken, mHandled);
            Handler handler = mEventHandler;
            handler;
            JVM INSTR monitorenter ;
            Session._2D_wrap0(Session.this, this);
            handler;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public Session.FinishedInputEventCallback mCallback;
        public InputEvent mEvent;
        public Handler mEventHandler;
        public Object mEventToken;
        public boolean mHandled;
        final Session this$1;

        private Session.PendingEvent()
        {
            this$1 = Session.this;
            super();
        }

        Session.PendingEvent(Session.PendingEvent pendingevent)
        {
            this();
        }
    }

    private final class Session.TvInputEventSender extends InputEventSender
    {

        public void onInputEventFinished(int i, boolean flag)
        {
            finishedInputEvent(i, flag, false);
        }

        final Session this$1;

        public Session.TvInputEventSender(InputChannel inputchannel, Looper looper)
        {
            this$1 = Session.this;
            super(inputchannel, looper);
        }
    }

    public static abstract class SessionCallback
    {

        public void onChannelRetuned(Session session, Uri uri)
        {
        }

        public void onContentAllowed(Session session)
        {
        }

        public void onContentBlocked(Session session, TvContentRating tvcontentrating)
        {
        }

        void onError(Session session, int i)
        {
        }

        public void onLayoutSurface(Session session, int i, int j, int k, int l)
        {
        }

        void onRecordingStopped(Session session, Uri uri)
        {
        }

        public void onSessionCreated(Session session)
        {
        }

        public void onSessionEvent(Session session, String s, Bundle bundle)
        {
        }

        public void onSessionReleased(Session session)
        {
        }

        public void onTimeShiftCurrentPositionChanged(Session session, long l)
        {
        }

        public void onTimeShiftStartPositionChanged(Session session, long l)
        {
        }

        public void onTimeShiftStatusChanged(Session session, int i)
        {
        }

        public void onTrackSelected(Session session, int i, String s)
        {
        }

        public void onTracksChanged(Session session, List list)
        {
        }

        void onTuned(Session session, Uri uri)
        {
        }

        public void onVideoAvailable(Session session)
        {
        }

        public void onVideoSizeChanged(Session session, int i, int j)
        {
        }

        public void onVideoUnavailable(Session session, int i)
        {
        }

        public SessionCallback()
        {
        }
    }

    private static final class SessionCallbackRecord
    {

        static Session _2D_get0(SessionCallbackRecord sessioncallbackrecord)
        {
            return sessioncallbackrecord.mSession;
        }

        static SessionCallback _2D_get1(SessionCallbackRecord sessioncallbackrecord)
        {
            return sessioncallbackrecord.mSessionCallback;
        }

        void postChannelRetuned(Uri uri)
        {
            mHandler.post(uri. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onChannelRetuned(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), channelUri);
                }

                final SessionCallbackRecord this$1;
                final Uri val$channelUri;

            
            {
                this$1 = final_sessioncallbackrecord;
                channelUri = Uri.this;
                super();
            }
            }
);
        }

        void postContentAllowed()
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onContentAllowed(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this));
                }

                final SessionCallbackRecord this$1;

            
            {
                this$1 = SessionCallbackRecord.this;
                super();
            }
            }
);
        }

        void postContentBlocked(TvContentRating tvcontentrating)
        {
            mHandler.post(tvcontentrating. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onContentBlocked(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), rating);
                }

                final SessionCallbackRecord this$1;
                final TvContentRating val$rating;

            
            {
                this$1 = final_sessioncallbackrecord;
                rating = TvContentRating.this;
                super();
            }
            }
);
        }

        void postError(int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onError(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), error);
                }

                final SessionCallbackRecord this$1;
                final int val$error;

            
            {
                this$1 = final_sessioncallbackrecord;
                error = I.this;
                super();
            }
            }
);
        }

        void postLayoutSurface(final int left, final int top, final int right, int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onLayoutSurface(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), left, top, right, bottom);
                }

                final SessionCallbackRecord this$1;
                final int val$bottom;
                final int val$left;
                final int val$right;
                final int val$top;

            
            {
                this$1 = final_sessioncallbackrecord;
                left = i;
                top = j;
                right = k;
                bottom = I.this;
                super();
            }
            }
);
        }

        void postRecordingStopped(Uri uri)
        {
            mHandler.post(uri. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onRecordingStopped(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), recordedProgramUri);
                }

                final SessionCallbackRecord this$1;
                final Uri val$recordedProgramUri;

            
            {
                this$1 = final_sessioncallbackrecord;
                recordedProgramUri = Uri.this;
                super();
            }
            }
);
        }

        void postSessionCreated(Session session)
        {
            mSession = session;
            mHandler.post(session. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onSessionCreated(session);
                }

                final SessionCallbackRecord this$1;
                final Session val$session;

            
            {
                this$1 = final_sessioncallbackrecord;
                session = Session.this;
                super();
            }
            }
);
        }

        void postSessionEvent(final String eventType, Bundle bundle)
        {
            mHandler.post(bundle. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onSessionEvent(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), eventType, eventArgs);
                }

                final SessionCallbackRecord this$1;
                final Bundle val$eventArgs;
                final String val$eventType;

            
            {
                this$1 = final_sessioncallbackrecord;
                eventType = s;
                eventArgs = Bundle.this;
                super();
            }
            }
);
        }

        void postSessionReleased()
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onSessionReleased(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this));
                }

                final SessionCallbackRecord this$1;

            
            {
                this$1 = SessionCallbackRecord.this;
                super();
            }
            }
);
        }

        void postTimeShiftCurrentPositionChanged(long l)
        {
            mHandler.post(l. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onTimeShiftCurrentPositionChanged(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), timeMs);
                }

                final SessionCallbackRecord this$1;
                final long val$timeMs;

            
            {
                this$1 = final_sessioncallbackrecord;
                timeMs = J.this;
                super();
            }
            }
);
        }

        void postTimeShiftStartPositionChanged(long l)
        {
            mHandler.post(l. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onTimeShiftStartPositionChanged(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), timeMs);
                }

                final SessionCallbackRecord this$1;
                final long val$timeMs;

            
            {
                this$1 = final_sessioncallbackrecord;
                timeMs = J.this;
                super();
            }
            }
);
        }

        void postTimeShiftStatusChanged(int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onTimeShiftStatusChanged(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), status);
                }

                final SessionCallbackRecord this$1;
                final int val$status;

            
            {
                this$1 = final_sessioncallbackrecord;
                status = I.this;
                super();
            }
            }
);
        }

        void postTrackSelected(final int type, String s)
        {
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onTrackSelected(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), type, trackId);
                }

                final SessionCallbackRecord this$1;
                final String val$trackId;
                final int val$type;

            
            {
                this$1 = final_sessioncallbackrecord;
                type = i;
                trackId = String.this;
                super();
            }
            }
);
        }

        void postTracksChanged(List list)
        {
            mHandler.post(list. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onTracksChanged(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), tracks);
                }

                final SessionCallbackRecord this$1;
                final List val$tracks;

            
            {
                this$1 = final_sessioncallbackrecord;
                tracks = List.this;
                super();
            }
            }
);
        }

        void postTuned(Uri uri)
        {
            mHandler.post(uri. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onTuned(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), channelUri);
                }

                final SessionCallbackRecord this$1;
                final Uri val$channelUri;

            
            {
                this$1 = final_sessioncallbackrecord;
                channelUri = Uri.this;
                super();
            }
            }
);
        }

        void postVideoAvailable()
        {
            mHandler.post(new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onVideoAvailable(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this));
                }

                final SessionCallbackRecord this$1;

            
            {
                this$1 = SessionCallbackRecord.this;
                super();
            }
            }
);
        }

        void postVideoSizeChanged(final int width, int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onVideoSizeChanged(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), width, height);
                }

                final SessionCallbackRecord this$1;
                final int val$height;
                final int val$width;

            
            {
                this$1 = final_sessioncallbackrecord;
                width = i;
                height = I.this;
                super();
            }
            }
);
        }

        void postVideoUnavailable(int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    SessionCallbackRecord._2D_get1(SessionCallbackRecord.this).onVideoUnavailable(SessionCallbackRecord._2D_get0(SessionCallbackRecord.this), reason);
                }

                final SessionCallbackRecord this$1;
                final int val$reason;

            
            {
                this$1 = final_sessioncallbackrecord;
                reason = I.this;
                super();
            }
            }
);
        }

        private final Handler mHandler;
        private Session mSession;
        private final SessionCallback mSessionCallback;

        SessionCallbackRecord(SessionCallback sessioncallback, Handler handler)
        {
            mSessionCallback = sessioncallback;
            mHandler = handler;
        }
    }

    public static abstract class TvInputCallback
    {

        public void onInputAdded(String s)
        {
        }

        public void onInputRemoved(String s)
        {
        }

        public void onInputStateChanged(String s, int i)
        {
        }

        public void onInputUpdated(String s)
        {
        }

        public void onTvInputInfoUpdated(TvInputInfo tvinputinfo)
        {
        }

        public TvInputCallback()
        {
        }
    }

    private static final class TvInputCallbackRecord
    {

        static TvInputCallback _2D_get0(TvInputCallbackRecord tvinputcallbackrecord)
        {
            return tvinputcallbackrecord.mCallback;
        }

        public TvInputCallback getCallback()
        {
            return mCallback;
        }

        public void postInputAdded(String s)
        {
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    TvInputCallbackRecord._2D_get0(TvInputCallbackRecord.this).onInputAdded(inputId);
                }

                final TvInputCallbackRecord this$1;
                final String val$inputId;

            
            {
                this$1 = final_tvinputcallbackrecord;
                inputId = String.this;
                super();
            }
            }
);
        }

        public void postInputRemoved(String s)
        {
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    TvInputCallbackRecord._2D_get0(TvInputCallbackRecord.this).onInputRemoved(inputId);
                }

                final TvInputCallbackRecord this$1;
                final String val$inputId;

            
            {
                this$1 = final_tvinputcallbackrecord;
                inputId = String.this;
                super();
            }
            }
);
        }

        public void postInputStateChanged(final String inputId, int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    TvInputCallbackRecord._2D_get0(TvInputCallbackRecord.this).onInputStateChanged(inputId, state);
                }

                final TvInputCallbackRecord this$1;
                final String val$inputId;
                final int val$state;

            
            {
                this$1 = final_tvinputcallbackrecord;
                inputId = s;
                state = I.this;
                super();
            }
            }
);
        }

        public void postInputUpdated(String s)
        {
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    TvInputCallbackRecord._2D_get0(TvInputCallbackRecord.this).onInputUpdated(inputId);
                }

                final TvInputCallbackRecord this$1;
                final String val$inputId;

            
            {
                this$1 = final_tvinputcallbackrecord;
                inputId = String.this;
                super();
            }
            }
);
        }

        public void postTvInputInfoUpdated(TvInputInfo tvinputinfo)
        {
            mHandler.post(tvinputinfo. new Runnable() {

                public void run()
                {
                    TvInputCallbackRecord._2D_get0(TvInputCallbackRecord.this).onTvInputInfoUpdated(inputInfo);
                }

                final TvInputCallbackRecord this$1;
                final TvInputInfo val$inputInfo;

            
            {
                this$1 = final_tvinputcallbackrecord;
                inputInfo = TvInputInfo.this;
                super();
            }
            }
);
        }

        private final TvInputCallback mCallback;
        private final Handler mHandler;

        public TvInputCallbackRecord(TvInputCallback tvinputcallback, Handler handler)
        {
            mCallback = tvinputcallback;
            mHandler = handler;
        }
    }


    static List _2D_get0(TvInputManager tvinputmanager)
    {
        return tvinputmanager.mCallbackRecords;
    }

    static Object _2D_get1(TvInputManager tvinputmanager)
    {
        return tvinputmanager.mLock;
    }

    static ITvInputManager _2D_get2(TvInputManager tvinputmanager)
    {
        return tvinputmanager.mService;
    }

    static SparseArray _2D_get3(TvInputManager tvinputmanager)
    {
        return tvinputmanager.mSessionCallbackRecordMap;
    }

    static Map _2D_get4(TvInputManager tvinputmanager)
    {
        return tvinputmanager.mStateMap;
    }

    static int _2D_get5(TvInputManager tvinputmanager)
    {
        return tvinputmanager.mUserId;
    }

    public TvInputManager(ITvInputManager itvinputmanager, int i)
    {
        mLock = new Object();
        mCallbackRecords = new LinkedList();
        mStateMap = new ArrayMap();
        mSessionCallbackRecordMap = new SparseArray();
        mService = itvinputmanager;
        mUserId = i;
        mClient = new ITvInputClient.Stub() {

            private void postVideoSizeChangedIfNeededLocked(SessionCallbackRecord sessioncallbackrecord)
            {
                TvTrackInfo tvtrackinfo = SessionCallbackRecord._2D_get0(sessioncallbackrecord).getVideoTrackToNotify();
                if(tvtrackinfo != null)
                    sessioncallbackrecord.postVideoSizeChanged(tvtrackinfo.getVideoWidth(), tvtrackinfo.getVideoHeight());
            }

            public void onChannelRetuned(Uri uri, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_61;
                uri = JVM INSTR new #59  <Class StringBuilder>;
                uri.StringBuilder();
                Log.e("TvInputManager", uri.append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                sessioncallbackrecord.postChannelRetuned(uri);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                uri;
                throw uri;
            }

            public void onContentAllowed(int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_59;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postContentAllowed();
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onContentBlocked(String s1, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_61;
                s1 = JVM INSTR new #59  <Class StringBuilder>;
                s1.StringBuilder();
                Log.e("TvInputManager", s1.append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                sessioncallbackrecord.postContentBlocked(TvContentRating.unflattenFromString(s1));
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                s1;
                throw s1;
            }

            public void onError(int j, int k)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(k);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_64;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(k).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postError(j);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onLayoutSurface(int j, int k, int l, int i1, int j1)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j1);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_69;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(j1).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postLayoutSurface(j, k, l, i1);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onRecordingStopped(Uri uri, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_61;
                uri = JVM INSTR new #59  <Class StringBuilder>;
                uri.StringBuilder();
                Log.e("TvInputManager", uri.append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                sessioncallbackrecord.postRecordingStopped(uri);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                uri;
                throw uri;
            }

            public void onSessionCreated(String s1, IBinder ibinder, InputChannel inputchannel, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_65;
                s1 = JVM INSTR new #59  <Class StringBuilder>;
                s1.StringBuilder();
                Log.e("TvInputManager", s1.append("Callback not found for ").append(ibinder).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                s1 = null;
                if(ibinder == null)
                    break MISSING_BLOCK_LABEL_105;
                s1 = JVM INSTR new #28  <Class TvInputManager$Session>;
                s1.Session(ibinder, inputchannel, TvInputManager._2D_get2(TvInputManager.this), TvInputManager._2D_get5(TvInputManager.this), j, TvInputManager._2D_get3(TvInputManager.this), null);
                sessioncallbackrecord.postSessionCreated(s1);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                s1;
                throw s1;
            }

            public void onSessionEvent(String s1, Bundle bundle, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_64;
                s1 = JVM INSTR new #59  <Class StringBuilder>;
                s1.StringBuilder();
                Log.e("TvInputManager", s1.append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                sessioncallbackrecord.postSessionEvent(s1, bundle);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                s1;
                throw s1;
            }

            public void onSessionReleased(int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1;
                obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                TvInputManager._2D_get3(TvInputManager.this).delete(j);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_70;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq:").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Session._2D_wrap1(SessionCallbackRecord._2D_get0(((SessionCallbackRecord) (obj1))));
                ((SessionCallbackRecord) (obj1)).postSessionReleased();
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onTimeShiftCurrentPositionChanged(long l, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_67;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postTimeShiftCurrentPositionChanged(l);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onTimeShiftStartPositionChanged(long l, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_67;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postTimeShiftStartPositionChanged(l);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onTimeShiftStatusChanged(int j, int k)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(k);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_64;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(k).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postTimeShiftStatusChanged(j);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onTrackSelected(int j, String s1, int k)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(k);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_64;
                s1 = JVM INSTR new #59  <Class StringBuilder>;
                s1.StringBuilder();
                Log.e("TvInputManager", s1.append("Callback not found for seq ").append(k).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                if(SessionCallbackRecord._2D_get0(sessioncallbackrecord).updateTrackSelection(j, s1))
                {
                    sessioncallbackrecord.postTrackSelected(j, s1);
                    postVideoSizeChangedIfNeededLocked(sessioncallbackrecord);
                }
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                s1;
                throw s1;
            }

            public void onTracksChanged(List list, int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_61;
                list = JVM INSTR new #59  <Class StringBuilder>;
                list.StringBuilder();
                Log.e("TvInputManager", list.append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                if(SessionCallbackRecord._2D_get0(sessioncallbackrecord).updateTracks(list))
                {
                    sessioncallbackrecord.postTracksChanged(list);
                    postVideoSizeChangedIfNeededLocked(sessioncallbackrecord);
                }
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                list;
                throw list;
            }

            public void onTuned(int j, Uri uri)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                SessionCallbackRecord sessioncallbackrecord = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(sessioncallbackrecord != null)
                    break MISSING_BLOCK_LABEL_61;
                uri = JVM INSTR new #59  <Class StringBuilder>;
                uri.StringBuilder();
                Log.e("TvInputManager", uri.append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                sessioncallbackrecord.postTuned(uri);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                uri;
                throw uri;
            }

            public void onVideoAvailable(int j)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(j);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_59;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(j).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postVideoAvailable();
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            public void onVideoUnavailable(int j, int k)
            {
                SparseArray sparsearray = TvInputManager._2D_get3(TvInputManager.this);
                sparsearray;
                JVM INSTR monitorenter ;
                Object obj1 = (SessionCallbackRecord)TvInputManager._2D_get3(TvInputManager.this).get(k);
                if(obj1 != null)
                    break MISSING_BLOCK_LABEL_64;
                obj1 = JVM INSTR new #59  <Class StringBuilder>;
                ((StringBuilder) (obj1)).StringBuilder();
                Log.e("TvInputManager", ((StringBuilder) (obj1)).append("Callback not found for seq ").append(k).toString());
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                ((SessionCallbackRecord) (obj1)).postVideoUnavailable(j);
                sparsearray;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            final TvInputManager this$0;

            
            {
                this$0 = TvInputManager.this;
                super();
            }
        }
;
        itvinputmanager = new ITvInputManagerCallback.Stub() {

            public void onInputAdded(String s1)
            {
                Object obj1 = TvInputManager._2D_get1(TvInputManager.this);
                obj1;
                JVM INSTR monitorenter ;
                TvInputManager._2D_get4(TvInputManager.this).put(s1, Integer.valueOf(0));
                for(Iterator iterator = TvInputManager._2D_get0(TvInputManager.this).iterator(); iterator.hasNext(); ((TvInputCallbackRecord)iterator.next()).postInputAdded(s1));
                break MISSING_BLOCK_LABEL_71;
                s1;
                throw s1;
                obj1;
                JVM INSTR monitorexit ;
            }

            public void onInputRemoved(String s1)
            {
                Object obj1 = TvInputManager._2D_get1(TvInputManager.this);
                obj1;
                JVM INSTR monitorenter ;
                TvInputManager._2D_get4(TvInputManager.this).remove(s1);
                for(Iterator iterator = TvInputManager._2D_get0(TvInputManager.this).iterator(); iterator.hasNext(); ((TvInputCallbackRecord)iterator.next()).postInputRemoved(s1));
                break MISSING_BLOCK_LABEL_67;
                s1;
                throw s1;
                obj1;
                JVM INSTR monitorexit ;
            }

            public void onInputStateChanged(String s1, int j)
            {
                Object obj1 = TvInputManager._2D_get1(TvInputManager.this);
                obj1;
                JVM INSTR monitorenter ;
                TvInputManager._2D_get4(TvInputManager.this).put(s1, Integer.valueOf(j));
                for(Iterator iterator = TvInputManager._2D_get0(TvInputManager.this).iterator(); iterator.hasNext(); ((TvInputCallbackRecord)iterator.next()).postInputStateChanged(s1, j));
                break MISSING_BLOCK_LABEL_75;
                s1;
                throw s1;
                obj1;
                JVM INSTR monitorexit ;
            }

            public void onInputUpdated(String s1)
            {
                Object obj1 = TvInputManager._2D_get1(TvInputManager.this);
                obj1;
                JVM INSTR monitorenter ;
                for(Iterator iterator = TvInputManager._2D_get0(TvInputManager.this).iterator(); iterator.hasNext(); ((TvInputCallbackRecord)iterator.next()).postInputUpdated(s1));
                break MISSING_BLOCK_LABEL_53;
                s1;
                throw s1;
                obj1;
                JVM INSTR monitorexit ;
            }

            public void onTvInputInfoUpdated(TvInputInfo tvinputinfo)
            {
                Object obj1 = TvInputManager._2D_get1(TvInputManager.this);
                obj1;
                JVM INSTR monitorenter ;
                for(Iterator iterator = TvInputManager._2D_get0(TvInputManager.this).iterator(); iterator.hasNext(); ((TvInputCallbackRecord)iterator.next()).postTvInputInfoUpdated(tvinputinfo));
                break MISSING_BLOCK_LABEL_53;
                tvinputinfo;
                throw tvinputinfo;
                obj1;
                JVM INSTR monitorexit ;
            }

            final TvInputManager this$0;

            
            {
                this$0 = TvInputManager.this;
                super();
            }
        }
;
        if(mService == null) goto _L2; else goto _L1
_L1:
        Object obj;
        mService.registerCallback(itvinputmanager, mUserId);
        obj = mService.getTvInputList(mUserId);
        itvinputmanager = ((ITvInputManager) (mLock));
        itvinputmanager;
        JVM INSTR monitorenter ;
        String s;
        for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); mStateMap.put(s, Integer.valueOf(mService.getTvInputState(s, mUserId))))
            s = ((TvInputInfo)((Iterator) (obj)).next()).getId();

        break MISSING_BLOCK_LABEL_195;
        Exception exception;
        exception;
        itvinputmanager;
        JVM INSTR monitorexit ;
        throw exception;
        itvinputmanager;
        throw itvinputmanager.rethrowFromSystemServer();
        itvinputmanager;
        JVM INSTR monitorexit ;
_L2:
    }

    private void createSessionInternal(String s, boolean flag, SessionCallback sessioncallback, Handler handler)
    {
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(sessioncallback);
        Preconditions.checkNotNull(handler);
        handler = new SessionCallbackRecord(sessioncallback, handler);
        sessioncallback = mSessionCallbackRecordMap;
        sessioncallback;
        JVM INSTR monitorenter ;
        int i;
        i = mNextSeq;
        mNextSeq = i + 1;
        mSessionCallbackRecordMap.put(i, handler);
        mService.createSession(mClient, s, flag, i, mUserId);
        sessioncallback;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s.rethrowFromSystemServer();
        s;
        sessioncallback;
        JVM INSTR monitorexit ;
        throw s;
    }

    public Hardware acquireTvInputHardware(int i, TvInputInfo tvinputinfo, HardwareCallback hardwarecallback)
    {
        try
        {
            ITvInputManager itvinputmanager = mService;
            ITvInputHardwareCallback.Stub stub = JVM INSTR new #10  <Class TvInputManager$3>;
            stub.this. _cls3();
            tvinputinfo = new Hardware(itvinputmanager.acquireTvInputHardware(i, stub, tvinputinfo, mUserId), null);
        }
        // Misplaced declaration of an exception variable
        catch(TvInputInfo tvinputinfo)
        {
            throw tvinputinfo.rethrowFromSystemServer();
        }
        return tvinputinfo;
    }

    public Hardware acquireTvInputHardware(int i, HardwareCallback hardwarecallback, TvInputInfo tvinputinfo)
    {
        return acquireTvInputHardware(i, tvinputinfo, hardwarecallback);
    }

    public void addBlockedRating(TvContentRating tvcontentrating)
    {
        Preconditions.checkNotNull(tvcontentrating);
        try
        {
            mService.addBlockedRating(tvcontentrating.flattenToString(), mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(TvContentRating tvcontentrating)
        {
            throw tvcontentrating.rethrowFromSystemServer();
        }
    }

    public boolean captureFrame(String s, Surface surface, TvStreamConfig tvstreamconfig)
    {
        boolean flag;
        try
        {
            flag = mService.captureFrame(s, surface, tvstreamconfig, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void createRecordingSession(String s, SessionCallback sessioncallback, Handler handler)
    {
        createSessionInternal(s, true, sessioncallback, handler);
    }

    public void createSession(String s, SessionCallback sessioncallback, Handler handler)
    {
        createSessionInternal(s, false, sessioncallback, handler);
    }

    public List getAvailableTvStreamConfigList(String s)
    {
        try
        {
            s = mService.getAvailableTvStreamConfigList(s, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public List getBlockedRatings()
    {
        Object obj;
        try
        {
            obj = JVM INSTR new #325 <Class ArrayList>;
            ((ArrayList) (obj)).ArrayList();
            for(Iterator iterator = mService.getBlockedRatings(mUserId).iterator(); iterator.hasNext(); ((List) (obj)).add(TvContentRating.unflattenFromString((String)iterator.next())));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw ((RemoteException) (obj)).rethrowFromSystemServer();
        }
        return ((List) (obj));
    }

    public List getDvbDeviceList()
    {
        List list;
        try
        {
            list = mService.getDvbDeviceList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getHardwareList()
    {
        List list;
        try
        {
            list = mService.getHardwareList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public int getInputState(String s)
    {
        Preconditions.checkNotNull(s);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (Integer)mStateMap.get(s);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_62;
        obj1 = JVM INSTR new #356 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.w("TvInputManager", ((StringBuilder) (obj1)).append("Unrecognized input ID: ").append(s).toString());
        obj;
        JVM INSTR monitorexit ;
        return 2;
        int i = ((Integer) (obj1)).intValue();
        obj;
        JVM INSTR monitorexit ;
        return i;
        s;
        throw s;
    }

    public List getTvContentRatingSystemList()
    {
        List list;
        try
        {
            list = mService.getTvContentRatingSystemList(mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public TvInputInfo getTvInputInfo(String s)
    {
        Preconditions.checkNotNull(s);
        try
        {
            s = mService.getTvInputInfo(s, mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public List getTvInputList()
    {
        List list;
        try
        {
            list = mService.getTvInputList(mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public boolean isParentalControlsEnabled()
    {
        boolean flag;
        try
        {
            flag = mService.isParentalControlsEnabled(mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isRatingBlocked(TvContentRating tvcontentrating)
    {
        Preconditions.checkNotNull(tvcontentrating);
        boolean flag;
        try
        {
            flag = mService.isRatingBlocked(tvcontentrating.flattenToString(), mUserId);
        }
        // Misplaced declaration of an exception variable
        catch(TvContentRating tvcontentrating)
        {
            throw tvcontentrating.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isSingleSessionActive()
    {
        boolean flag;
        try
        {
            flag = mService.isSingleSessionActive(mUserId);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void notifyPreviewProgramAddedToWatchNext(String s, long l, long l1)
    {
        Intent intent = new Intent();
        intent.setAction("android.media.tv.action.PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT");
        intent.putExtra("android.media.tv.extra.PREVIEW_PROGRAM_ID", l);
        intent.putExtra("android.media.tv.extra.WATCH_NEXT_PROGRAM_ID", l1);
        intent.setPackage(s);
        try
        {
            mService.sendTvInputNotifyIntent(intent, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void notifyPreviewProgramBrowsableDisabled(String s, long l)
    {
        Intent intent = new Intent();
        intent.setAction("android.media.tv.action.PREVIEW_PROGRAM_BROWSABLE_DISABLED");
        intent.putExtra("android.media.tv.extra.PREVIEW_PROGRAM_ID", l);
        intent.setPackage(s);
        try
        {
            mService.sendTvInputNotifyIntent(intent, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void notifyWatchNextProgramBrowsableDisabled(String s, long l)
    {
        Intent intent = new Intent();
        intent.setAction("android.media.tv.action.WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED");
        intent.putExtra("android.media.tv.extra.WATCH_NEXT_PROGRAM_ID", l);
        intent.setPackage(s);
        try
        {
            mService.sendTvInputNotifyIntent(intent, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public ParcelFileDescriptor openDvbDevice(DvbDeviceInfo dvbdeviceinfo, int i)
    {
        if(i < 0 || 2 < i)
            try
            {
                dvbdeviceinfo = JVM INSTR new #435 <Class IllegalArgumentException>;
                StringBuilder stringbuilder = JVM INSTR new #356 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                dvbdeviceinfo.IllegalArgumentException(stringbuilder.append("Invalid DVB device: ").append(i).toString());
                throw dvbdeviceinfo;
            }
            // Misplaced declaration of an exception variable
            catch(DvbDeviceInfo dvbdeviceinfo)
            {
                throw dvbdeviceinfo.rethrowFromSystemServer();
            }
        dvbdeviceinfo = mService.openDvbDevice(dvbdeviceinfo, i);
        return dvbdeviceinfo;
    }

    public void registerCallback(TvInputCallback tvinputcallback, Handler handler)
    {
        Preconditions.checkNotNull(tvinputcallback);
        Preconditions.checkNotNull(handler);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        List list = mCallbackRecords;
        TvInputCallbackRecord tvinputcallbackrecord = JVM INSTR new #78  <Class TvInputManager$TvInputCallbackRecord>;
        tvinputcallbackrecord.TvInputCallbackRecord(tvinputcallback, handler);
        list.add(tvinputcallbackrecord);
        obj;
        JVM INSTR monitorexit ;
        return;
        tvinputcallback;
        throw tvinputcallback;
    }

    public void releaseTvInputHardware(int i, Hardware hardware)
    {
        try
        {
            mService.releaseTvInputHardware(i, Hardware._2D_wrap0(hardware), mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Hardware hardware)
        {
            throw hardware.rethrowFromSystemServer();
        }
    }

    public void removeBlockedRating(TvContentRating tvcontentrating)
    {
        Preconditions.checkNotNull(tvcontentrating);
        try
        {
            mService.removeBlockedRating(tvcontentrating.flattenToString(), mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(TvContentRating tvcontentrating)
        {
            throw tvcontentrating.rethrowFromSystemServer();
        }
    }

    public void requestChannelBrowsable(Uri uri)
    {
        try
        {
            mService.requestChannelBrowsable(uri, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Uri uri)
        {
            throw uri.rethrowFromSystemServer();
        }
    }

    public void setParentalControlsEnabled(boolean flag)
    {
        try
        {
            mService.setParentalControlsEnabled(flag, mUserId);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unregisterCallback(TvInputCallback tvinputcallback)
    {
        Preconditions.checkNotNull(tvinputcallback);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator = mCallbackRecords.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(((TvInputCallbackRecord)iterator.next()).getCallback() != tvinputcallback)
                continue;
            iterator.remove();
            break;
        } while(true);
        obj;
        JVM INSTR monitorexit ;
        return;
        tvinputcallback;
        throw tvinputcallback;
    }

    public void updateTvInputInfo(TvInputInfo tvinputinfo)
    {
        Preconditions.checkNotNull(tvinputinfo);
        try
        {
            mService.updateTvInputInfo(tvinputinfo, mUserId);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(TvInputInfo tvinputinfo)
        {
            throw tvinputinfo.rethrowFromSystemServer();
        }
    }

    public static final String ACTION_BLOCKED_RATINGS_CHANGED = "android.media.tv.action.BLOCKED_RATINGS_CHANGED";
    public static final String ACTION_PARENTAL_CONTROLS_ENABLED_CHANGED = "android.media.tv.action.PARENTAL_CONTROLS_ENABLED_CHANGED";
    public static final String ACTION_QUERY_CONTENT_RATING_SYSTEMS = "android.media.tv.action.QUERY_CONTENT_RATING_SYSTEMS";
    public static final String ACTION_SETUP_INPUTS = "android.media.tv.action.SETUP_INPUTS";
    public static final String ACTION_VIEW_RECORDING_SCHEDULES = "android.media.tv.action.VIEW_RECORDING_SCHEDULES";
    public static final int DVB_DEVICE_DEMUX = 0;
    public static final int DVB_DEVICE_DVR = 1;
    static final int DVB_DEVICE_END = 2;
    public static final int DVB_DEVICE_FRONTEND = 2;
    static final int DVB_DEVICE_START = 0;
    public static final int INPUT_STATE_CONNECTED = 0;
    public static final int INPUT_STATE_CONNECTED_STANDBY = 1;
    public static final int INPUT_STATE_DISCONNECTED = 2;
    public static final String META_DATA_CONTENT_RATING_SYSTEMS = "android.media.tv.metadata.CONTENT_RATING_SYSTEMS";
    static final int RECORDING_ERROR_END = 2;
    public static final int RECORDING_ERROR_INSUFFICIENT_SPACE = 1;
    public static final int RECORDING_ERROR_RESOURCE_BUSY = 2;
    static final int RECORDING_ERROR_START = 0;
    public static final int RECORDING_ERROR_UNKNOWN = 0;
    private static final String TAG = "TvInputManager";
    public static final long TIME_SHIFT_INVALID_TIME = 0x8000000000000000L;
    public static final int TIME_SHIFT_STATUS_AVAILABLE = 3;
    public static final int TIME_SHIFT_STATUS_UNAVAILABLE = 2;
    public static final int TIME_SHIFT_STATUS_UNKNOWN = 0;
    public static final int TIME_SHIFT_STATUS_UNSUPPORTED = 1;
    public static final int VIDEO_UNAVAILABLE_REASON_AUDIO_ONLY = 4;
    public static final int VIDEO_UNAVAILABLE_REASON_BUFFERING = 3;
    static final int VIDEO_UNAVAILABLE_REASON_END = 4;
    static final int VIDEO_UNAVAILABLE_REASON_START = 0;
    public static final int VIDEO_UNAVAILABLE_REASON_TUNING = 1;
    public static final int VIDEO_UNAVAILABLE_REASON_UNKNOWN = 0;
    public static final int VIDEO_UNAVAILABLE_REASON_WEAK_SIGNAL = 2;
    private final List mCallbackRecords;
    private final ITvInputClient mClient;
    private final Object mLock;
    private int mNextSeq;
    private final ITvInputManager mService;
    private final SparseArray mSessionCallbackRecordMap;
    private final Map mStateMap;
    private final int mUserId;

    // Unreferenced inner class android/media/tv/TvInputManager$3

/* anonymous class */
    class _cls3 extends ITvInputHardwareCallback.Stub
    {

        public void onReleased()
        {
            callback.onReleased();
        }

        public void onStreamConfigChanged(TvStreamConfig atvstreamconfig[])
        {
            callback.onStreamConfigChanged(atvstreamconfig);
        }

        final TvInputManager this$0;
        final HardwareCallback val$callback;

            
            {
                this$0 = TvInputManager.this;
                callback = hardwarecallback;
                super();
            }
    }

}
