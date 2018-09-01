// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

// Referenced classes of package android.speech.tts:
//            TtsEngines, AudioPlaybackHandler, Voice, SynthesisRequest, 
//            SynthesisCallback, AudioPlaybackQueueItem, ITextToSpeechCallback, SilencePlaybackQueueItem, 
//            EventLogger, PlaybackSynthesisCallback, TextToSpeech, AbstractSynthesisCallback, 
//            FileSynthesisCallback

public abstract class TextToSpeechService extends Service
{
    static class AudioOutputParams
    {

        static AudioOutputParams createFromParamsBundle(Bundle bundle, boolean flag)
        {
            if(bundle == null)
                return new AudioOutputParams();
            AudioAttributes audioattributes = (AudioAttributes)bundle.getParcelable("audioAttributes");
            Object obj = audioattributes;
            if(audioattributes == null)
            {
                int i = bundle.getInt("streamType", 3);
                obj = (new android.media.AudioAttributes.Builder()).setLegacyStreamType(i);
                if(flag)
                    i = 1;
                else
                    i = 4;
                obj = ((android.media.AudioAttributes.Builder) (obj)).setContentType(i).build();
            }
            return new AudioOutputParams(bundle.getInt("sessionId", 0), bundle.getFloat("volume", 1.0F), bundle.getFloat("pan", 0.0F), ((AudioAttributes) (obj)));
        }

        public final AudioAttributes mAudioAttributes;
        public final float mPan;
        public final int mSessionId;
        public final float mVolume;

        AudioOutputParams()
        {
            mSessionId = 0;
            mVolume = 1.0F;
            mPan = 0.0F;
            mAudioAttributes = null;
        }

        AudioOutputParams(int i, float f, float f1, AudioAttributes audioattributes)
        {
            mSessionId = i;
            mVolume = f;
            mPan = f1;
            mAudioAttributes = audioattributes;
        }
    }

    private class AudioSpeechItem extends UtteranceSpeechItemWithParams
    {

        AudioOutputParams getAudioParams()
        {
            return AudioOutputParams.createFromParamsBundle(mParams, false);
        }

        public String getUtteranceId()
        {
            return getStringParam(mParams, "utteranceId", null);
        }

        public boolean isValid()
        {
            return true;
        }

        protected void playImpl()
        {
            TextToSpeechService._2D_get0(TextToSpeechService.this).enqueue(mItem);
        }

        protected void stopImpl()
        {
        }

        private final AudioPlaybackQueueItem mItem;
        final TextToSpeechService this$0;

        public AudioSpeechItem(Object obj, int i, int j, Bundle bundle, String s, Uri uri)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j, bundle, s);
            mItem = new AudioPlaybackQueueItem(this, getCallerIdentity(), TextToSpeechService.this, uri, getAudioParams());
        }
    }

    private class CallbackMap extends RemoteCallbackList
    {

        private ITextToSpeechCallback getCallbackFor(Object obj)
        {
            Object obj1 = (IBinder)obj;
            obj = mCallerToCallback;
            obj;
            JVM INSTR monitorenter ;
            obj1 = (ITextToSpeechCallback)mCallerToCallback.get(obj1);
            obj;
            JVM INSTR monitorexit ;
            return ((ITextToSpeechCallback) (obj1));
            Exception exception;
            exception;
            throw exception;
        }

        public void dispatchOnAudioAvailable(Object obj, String s, byte abyte0[])
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onAudioAvailable(s, abyte0);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback dispatchOnAudioAvailable(String, byte[]) failed: ").append(obj).toString());
              goto _L1
        }

        public void dispatchOnBeginSynthesis(Object obj, String s, int i, int j, int k)
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onBeginSynthesis(s, i, j, k);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback dispatchOnBeginSynthesis(String, int, int, int) failed: ").append(obj).toString());
              goto _L1
        }

        public void dispatchOnError(Object obj, String s, int i)
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onError(s, i);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback onError failed: ").append(obj).toString());
              goto _L1
        }

        public void dispatchOnRangeStart(Object obj, String s, int i, int j, int k)
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onRangeStart(s, i, j, k);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback dispatchOnRangeStart(String, int, int, int) failed: ").append(obj).toString());
              goto _L1
        }

        public void dispatchOnStart(Object obj, String s)
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onStart(s);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback onStart failed: ").append(obj).toString());
              goto _L1
        }

        public void dispatchOnStop(Object obj, String s, boolean flag)
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onStop(s, flag);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback onStop failed: ").append(obj).toString());
              goto _L1
        }

        public void dispatchOnSuccess(Object obj, String s)
        {
            obj = getCallbackFor(obj);
            if(obj == null)
                return;
            ((ITextToSpeechCallback) (obj)).onSuccess(s);
_L1:
            return;
            obj;
            Log.e("TextToSpeechService", (new StringBuilder()).append("Callback onDone failed: ").append(obj).toString());
              goto _L1
        }

        public void kill()
        {
            HashMap hashmap = mCallerToCallback;
            hashmap;
            JVM INSTR monitorenter ;
            mCallerToCallback.clear();
            super.kill();
            hashmap;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public volatile void onCallbackDied(IInterface iinterface, Object obj)
        {
            onCallbackDied((ITextToSpeechCallback)iinterface, obj);
        }

        public void onCallbackDied(ITextToSpeechCallback itexttospeechcallback, Object obj)
        {
            obj = (IBinder)obj;
            itexttospeechcallback = mCallerToCallback;
            itexttospeechcallback;
            JVM INSTR monitorenter ;
            mCallerToCallback.remove(obj);
            itexttospeechcallback;
            JVM INSTR monitorexit ;
            return;
            obj;
            throw obj;
        }

        public void setCallback(IBinder ibinder, ITextToSpeechCallback itexttospeechcallback)
        {
            HashMap hashmap = mCallerToCallback;
            hashmap;
            JVM INSTR monitorenter ;
            if(itexttospeechcallback == null) goto _L2; else goto _L1
_L1:
            register(itexttospeechcallback, ibinder);
            ibinder = (ITextToSpeechCallback)mCallerToCallback.put(ibinder, itexttospeechcallback);
_L4:
            if(ibinder == null || ibinder == itexttospeechcallback)
                break MISSING_BLOCK_LABEL_46;
            unregister(ibinder);
            hashmap;
            JVM INSTR monitorexit ;
            return;
_L2:
            ibinder = (ITextToSpeechCallback)mCallerToCallback.remove(ibinder);
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            throw ibinder;
        }

        private final HashMap mCallerToCallback;
        final TextToSpeechService this$0;

        private CallbackMap()
        {
            this$0 = TextToSpeechService.this;
            super();
            mCallerToCallback = new HashMap();
        }

        CallbackMap(CallbackMap callbackmap)
        {
            this();
        }
    }

    private class LoadLanguageItem extends SpeechItem
    {

        public boolean isValid()
        {
            return true;
        }

        protected void playImpl()
        {
            onLoadLanguage(mLanguage, mCountry, mVariant);
        }

        protected void stopImpl()
        {
        }

        private final String mCountry;
        private final String mLanguage;
        private final String mVariant;
        final TextToSpeechService this$0;

        public LoadLanguageItem(Object obj, int i, int j, String s, String s1, String s2)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j);
            mLanguage = s;
            mCountry = s1;
            mVariant = s2;
        }
    }

    private class LoadVoiceItem extends SpeechItem
    {

        public boolean isValid()
        {
            return true;
        }

        protected void playImpl()
        {
            onLoadVoice(mVoiceName);
        }

        protected void stopImpl()
        {
        }

        private final String mVoiceName;
        final TextToSpeechService this$0;

        public LoadVoiceItem(Object obj, int i, int j, String s)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j);
            mVoiceName = s;
        }
    }

    private class SilenceSpeechItem extends UtteranceSpeechItem
    {

        public String getUtteranceId()
        {
            return mUtteranceId;
        }

        public boolean isValid()
        {
            return true;
        }

        protected void playImpl()
        {
            TextToSpeechService._2D_get0(TextToSpeechService.this).enqueue(new SilencePlaybackQueueItem(this, getCallerIdentity(), mDuration));
        }

        protected void stopImpl()
        {
        }

        private final long mDuration;
        private final String mUtteranceId;
        final TextToSpeechService this$0;

        public SilenceSpeechItem(Object obj, int i, int j, String s, long l)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j);
            mUtteranceId = s;
            mDuration = l;
        }
    }

    private abstract class SpeechItem
    {

        public Object getCallerIdentity()
        {
            return mCallerIdentity;
        }

        public int getCallerPid()
        {
            return mCallerPid;
        }

        public int getCallerUid()
        {
            return mCallerUid;
        }

        protected boolean isStarted()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mStarted;
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        protected boolean isStopped()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mStopped;
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public abstract boolean isValid();

        public void play()
        {
            this;
            JVM INSTR monitorenter ;
            if(mStarted)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #47  <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("play() called twice");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            mStarted = true;
            this;
            JVM INSTR monitorexit ;
            playImpl();
            return;
        }

        protected abstract void playImpl();

        public void stop()
        {
            this;
            JVM INSTR monitorenter ;
            if(mStopped)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #47  <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("stop() called twice");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            mStopped = true;
            this;
            JVM INSTR monitorexit ;
            stopImpl();
            return;
        }

        protected abstract void stopImpl();

        private final Object mCallerIdentity;
        private final int mCallerPid;
        private final int mCallerUid;
        private boolean mStarted;
        private boolean mStopped;
        final TextToSpeechService this$0;

        public SpeechItem(Object obj, int i, int j)
        {
            this$0 = TextToSpeechService.this;
            super();
            mStarted = false;
            mStopped = false;
            mCallerIdentity = obj;
            mCallerUid = i;
            mCallerPid = j;
        }
    }

    private class SynthHandler extends Handler
    {

        static SpeechItem _2D_wrap0(SynthHandler synthhandler, SpeechItem speechitem)
        {
            return synthhandler.setCurrentSpeechItem(speechitem);
        }

        static boolean _2D_wrap1(SynthHandler synthhandler, SpeechItem speechitem)
        {
            return synthhandler.isFlushed(speechitem);
        }

        static void _2D_wrap2(SynthHandler synthhandler, Object obj)
        {
            synthhandler.endFlushingSpeechItems(obj);
        }

        private void endFlushingSpeechItems(Object obj)
        {
            List list = mFlushedObjects;
            list;
            JVM INSTR monitorenter ;
            if(obj != null)
                break MISSING_BLOCK_LABEL_24;
            mFlushAll = mFlushAll - 1;
_L1:
            list;
            JVM INSTR monitorexit ;
            return;
            mFlushedObjects.remove(obj);
              goto _L1
            obj;
            throw obj;
        }

        private SpeechItem getCurrentSpeechItem()
        {
            this;
            JVM INSTR monitorenter ;
            SpeechItem speechitem = mCurrentSpeechItem;
            this;
            JVM INSTR monitorexit ;
            return speechitem;
            Exception exception;
            exception;
            throw exception;
        }

        private boolean isFlushed(SpeechItem speechitem)
        {
            List list = mFlushedObjects;
            list;
            JVM INSTR monitorenter ;
            boolean flag;
            if(mFlushAll > 0)
                break MISSING_BLOCK_LABEL_32;
            flag = mFlushedObjects.contains(speechitem.getCallerIdentity());
_L1:
            list;
            JVM INSTR monitorexit ;
            return flag;
            flag = true;
              goto _L1
            speechitem;
            throw speechitem;
        }

        private SpeechItem maybeRemoveCurrentSpeechItem(Object obj)
        {
            this;
            JVM INSTR monitorenter ;
            if(mCurrentSpeechItem == null || mCurrentSpeechItem.getCallerIdentity() != obj)
                break MISSING_BLOCK_LABEL_34;
            obj = mCurrentSpeechItem;
            mCurrentSpeechItem = null;
            return ((SpeechItem) (obj));
            this;
            JVM INSTR monitorexit ;
            return null;
            obj;
            throw obj;
        }

        private SpeechItem setCurrentSpeechItem(SpeechItem speechitem)
        {
            this;
            JVM INSTR monitorenter ;
            SpeechItem speechitem1;
            speechitem1 = mCurrentSpeechItem;
            mCurrentSpeechItem = speechitem;
            this;
            JVM INSTR monitorexit ;
            return speechitem1;
            speechitem;
            throw speechitem;
        }

        private void startFlushingSpeechItems(Object obj)
        {
            List list = mFlushedObjects;
            list;
            JVM INSTR monitorenter ;
            if(obj != null)
                break MISSING_BLOCK_LABEL_24;
            mFlushAll = mFlushAll + 1;
_L1:
            list;
            JVM INSTR monitorexit ;
            return;
            mFlushedObjects.add(obj);
              goto _L1
            obj;
            throw obj;
        }

        public int enqueueSpeechItem(int i, SpeechItem speechitem)
        {
            UtteranceProgressDispatcher utteranceprogressdispatcher;
            utteranceprogressdispatcher = null;
            if(speechitem instanceof UtteranceProgressDispatcher)
                utteranceprogressdispatcher = (UtteranceProgressDispatcher)speechitem;
            if(!speechitem.isValid())
            {
                if(utteranceprogressdispatcher != null)
                    utteranceprogressdispatcher.dispatchOnError(-8);
                return -1;
            }
            if(i != 0) goto _L2; else goto _L1
_L1:
            stopForApp(speechitem.getCallerIdentity());
_L4:
            Message message = Message.obtain(this, speechitem. new Runnable() {

                public void run()
                {
                    if(SynthHandler._2D_wrap1(SynthHandler.this, speechItem))
                    {
                        speechItem.stop();
                    } else
                    {
                        SynthHandler._2D_wrap0(SynthHandler.this, speechItem);
                        speechItem.play();
                        SynthHandler._2D_wrap0(SynthHandler.this, null);
                    }
                }

                final SynthHandler this$1;
                final SpeechItem val$speechItem;

            
            {
                this$1 = final_synthhandler;
                speechItem = SpeechItem.this;
                super();
            }
            }
);
            message.obj = speechitem.getCallerIdentity();
            if(sendMessage(message))
                return 0;
            break; /* Loop/switch isn't completed */
_L2:
            if(i == 2)
                stopAll();
            if(true) goto _L4; else goto _L3
_L3:
            Log.w("TextToSpeechService", "SynthThread has quit");
            if(utteranceprogressdispatcher != null)
                utteranceprogressdispatcher.dispatchOnError(-4);
            return -1;
        }

        public boolean isSpeaking()
        {
            boolean flag;
            if(getCurrentSpeechItem() != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void quit()
        {
            getLooper().quit();
            SpeechItem speechitem = setCurrentSpeechItem(null);
            if(speechitem != null)
                speechitem.stop();
        }

        public int stopAll()
        {
            startFlushingSpeechItems(null);
            SpeechItem speechitem = setCurrentSpeechItem(null);
            if(speechitem != null)
                speechitem.stop();
            TextToSpeechService._2D_get0(TextToSpeechService.this).stop();
            sendMessage(Message.obtain(this, new Runnable() {

                public void run()
                {
                    SynthHandler._2D_wrap2(SynthHandler.this, null);
                }

                final SynthHandler this$1;

            
            {
                this$1 = SynthHandler.this;
                super();
            }
            }
));
            return 0;
        }

        public int stopForApp(Object obj)
        {
            if(obj == null)
                return -1;
            startFlushingSpeechItems(obj);
            SpeechItem speechitem = maybeRemoveCurrentSpeechItem(obj);
            if(speechitem != null)
                speechitem.stop();
            TextToSpeechService._2D_get0(TextToSpeechService.this).stopForApp(obj);
            sendMessage(Message.obtain(this, ((_cls2) (obj)). new Runnable() {

                public void run()
                {
                    SynthHandler._2D_wrap2(SynthHandler.this, callerIdentity);
                }

                final SynthHandler this$1;
                final Object val$callerIdentity;

            
            {
                this$1 = final_synthhandler;
                callerIdentity = Object.this;
                super();
            }
            }
));
            return 0;
        }

        private SpeechItem mCurrentSpeechItem;
        private int mFlushAll;
        private List mFlushedObjects;
        final TextToSpeechService this$0;

        public SynthHandler(Looper looper)
        {
            this$0 = TextToSpeechService.this;
            super(looper);
            mCurrentSpeechItem = null;
            mFlushedObjects = new ArrayList();
            mFlushAll = 0;
        }
    }

    private class SynthThread extends HandlerThread
        implements android.os.MessageQueue.IdleHandler
    {

        private void broadcastTtsQueueProcessingCompleted()
        {
            Intent intent = new Intent("android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED");
            sendBroadcast(intent);
        }

        protected void onLooperPrepared()
        {
            getLooper().getQueue().addIdleHandler(this);
        }

        public boolean queueIdle()
        {
            if(mFirstIdle)
                mFirstIdle = false;
            else
                broadcastTtsQueueProcessingCompleted();
            return true;
        }

        private boolean mFirstIdle;
        final TextToSpeechService this$0;

        public SynthThread()
        {
            this$0 = TextToSpeechService.this;
            super("SynthThread", 0);
            mFirstIdle = true;
        }
    }

    class SynthesisSpeechItem extends UtteranceSpeechItemWithParams
    {

        private String getCountry()
        {
            if(!hasLanguage())
                return mDefaultLocale[1];
            else
                return getStringParam(mParams, "country", "");
        }

        private String getVariant()
        {
            if(!hasLanguage())
                return mDefaultLocale[2];
            else
                return getStringParam(mParams, "variant", "");
        }

        private void setRequestParams(SynthesisRequest synthesisrequest)
        {
            String s = getVoiceName();
            synthesisrequest.setLanguage(getLanguage(), getCountry(), getVariant());
            if(!TextUtils.isEmpty(s))
                synthesisrequest.setVoiceName(getVoiceName());
            synthesisrequest.setSpeechRate(getSpeechRate());
            synthesisrequest.setCallerUid(mCallerUid);
            synthesisrequest.setPitch(getPitch());
        }

        protected AbstractSynthesisCallback createSynthesisCallback()
        {
            return new PlaybackSynthesisCallback(getAudioParams(), TextToSpeechService._2D_get0(TextToSpeechService.this), this, getCallerIdentity(), mEventLogger, false);
        }

        public String getLanguage()
        {
            return getStringParam(mParams, "language", mDefaultLocale[0]);
        }

        public CharSequence getText()
        {
            return mText;
        }

        public String getVoiceName()
        {
            return getStringParam(mParams, "voiceName", "");
        }

        public boolean isValid()
        {
            if(mText == null)
            {
                Log.e("TextToSpeechService", "null synthesis text");
                return false;
            }
            if(mText.length() >= TextToSpeech.getMaxSpeechInputLength())
            {
                Log.w("TextToSpeechService", (new StringBuilder()).append("Text too long: ").append(mText.length()).append(" chars").toString());
                return false;
            } else
            {
                return true;
            }
        }

        protected void playImpl()
        {
            mEventLogger.onRequestProcessingStart();
            this;
            JVM INSTR monitorenter ;
            boolean flag = isStopped();
            if(!flag)
                break MISSING_BLOCK_LABEL_21;
            this;
            JVM INSTR monitorexit ;
            return;
            AbstractSynthesisCallback abstractsynthesiscallback;
            mSynthesisCallback = createSynthesisCallback();
            abstractsynthesiscallback = mSynthesisCallback;
            this;
            JVM INSTR monitorexit ;
            onSynthesizeText(mSynthesisRequest, abstractsynthesiscallback);
            if(abstractsynthesiscallback.hasStarted() && abstractsynthesiscallback.hasFinished() ^ true)
                abstractsynthesiscallback.done();
            return;
            Exception exception;
            exception;
            throw exception;
        }

        protected void stopImpl()
        {
            this;
            JVM INSTR monitorenter ;
            AbstractSynthesisCallback abstractsynthesiscallback = mSynthesisCallback;
            this;
            JVM INSTR monitorexit ;
            Exception exception;
            if(abstractsynthesiscallback != null)
            {
                abstractsynthesiscallback.stop();
                onStop();
            } else
            {
                dispatchOnStop();
            }
            return;
            exception;
            throw exception;
        }

        private final int mCallerUid;
        private final String mDefaultLocale[];
        private final EventLogger mEventLogger;
        private AbstractSynthesisCallback mSynthesisCallback;
        private final SynthesisRequest mSynthesisRequest;
        private final CharSequence mText;
        final TextToSpeechService this$0;

        public SynthesisSpeechItem(Object obj, int i, int j, Bundle bundle, String s, CharSequence charsequence)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j, bundle, s);
            mText = charsequence;
            mCallerUid = i;
            mSynthesisRequest = new SynthesisRequest(mText, mParams);
            mDefaultLocale = TextToSpeechService._2D_wrap2(TextToSpeechService.this);
            setRequestParams(mSynthesisRequest);
            mEventLogger = new EventLogger(mSynthesisRequest, i, j, TextToSpeechService._2D_get2(TextToSpeechService.this));
        }
    }

    private class SynthesisToFileOutputStreamSpeechItem extends SynthesisSpeechItem
    {

        protected AbstractSynthesisCallback createSynthesisCallback()
        {
            return new FileSynthesisCallback(mFileOutputStream.getChannel(), this, false);
        }

        protected void playImpl()
        {
            dispatchOnStart();
            super.playImpl();
            mFileOutputStream.close();
_L1:
            return;
            IOException ioexception;
            ioexception;
            Log.w("TextToSpeechService", "Failed to close output file", ioexception);
              goto _L1
        }

        private final FileOutputStream mFileOutputStream;
        final TextToSpeechService this$0;

        public SynthesisToFileOutputStreamSpeechItem(Object obj, int i, int j, Bundle bundle, String s, CharSequence charsequence, 
                FileOutputStream fileoutputstream)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j, bundle, s, charsequence);
            mFileOutputStream = fileoutputstream;
        }
    }

    static interface UtteranceProgressDispatcher
    {

        public abstract void dispatchOnAudioAvailable(byte abyte0[]);

        public abstract void dispatchOnBeginSynthesis(int i, int j, int k);

        public abstract void dispatchOnError(int i);

        public abstract void dispatchOnRangeStart(int i, int j, int k);

        public abstract void dispatchOnStart();

        public abstract void dispatchOnStop();

        public abstract void dispatchOnSuccess();
    }

    private abstract class UtteranceSpeechItem extends SpeechItem
        implements UtteranceProgressDispatcher
    {

        public void dispatchOnAudioAvailable(byte abyte0[])
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnAudioAvailable(getCallerIdentity(), s, abyte0);
        }

        public void dispatchOnBeginSynthesis(int i, int j, int k)
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnBeginSynthesis(getCallerIdentity(), s, i, j, k);
        }

        public void dispatchOnError(int i)
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnError(getCallerIdentity(), s, i);
        }

        public void dispatchOnRangeStart(int i, int j, int k)
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnRangeStart(getCallerIdentity(), s, i, j, k);
        }

        public void dispatchOnStart()
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnStart(getCallerIdentity(), s);
        }

        public void dispatchOnStop()
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnStop(getCallerIdentity(), s, isStarted());
        }

        public void dispatchOnSuccess()
        {
            String s = getUtteranceId();
            if(s != null)
                TextToSpeechService._2D_get1(TextToSpeechService.this).dispatchOnSuccess(getCallerIdentity(), s);
        }

        float getFloatParam(Bundle bundle, String s, float f)
        {
            if(bundle != null)
                f = bundle.getFloat(s, f);
            return f;
        }

        int getIntParam(Bundle bundle, String s, int i)
        {
            if(bundle != null)
                i = bundle.getInt(s, i);
            return i;
        }

        String getStringParam(Bundle bundle, String s, String s1)
        {
            if(bundle != null)
                s1 = bundle.getString(s, s1);
            return s1;
        }

        public abstract String getUtteranceId();

        final TextToSpeechService this$0;

        public UtteranceSpeechItem(Object obj, int i, int j)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j);
        }
    }

    private abstract class UtteranceSpeechItemWithParams extends UtteranceSpeechItem
    {

        AudioOutputParams getAudioParams()
        {
            return AudioOutputParams.createFromParamsBundle(mParams, true);
        }

        int getPitch()
        {
            return getIntParam(mParams, "pitch", TextToSpeechService._2D_wrap0(TextToSpeechService.this));
        }

        int getSpeechRate()
        {
            return getIntParam(mParams, "rate", TextToSpeechService._2D_wrap1(TextToSpeechService.this));
        }

        public String getUtteranceId()
        {
            return mUtteranceId;
        }

        boolean hasLanguage()
        {
            return TextUtils.isEmpty(getStringParam(mParams, "language", null)) ^ true;
        }

        protected final Bundle mParams;
        protected final String mUtteranceId;
        final TextToSpeechService this$0;

        UtteranceSpeechItemWithParams(Object obj, int i, int j, Bundle bundle, String s)
        {
            this$0 = TextToSpeechService.this;
            super(obj, i, j);
            mParams = bundle;
            mUtteranceId = s;
        }
    }


    static AudioPlaybackHandler _2D_get0(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.mAudioPlaybackHandler;
    }

    static CallbackMap _2D_get1(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.mCallbacks;
    }

    static String _2D_get2(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.mPackageName;
    }

    static SynthHandler _2D_get3(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.mSynthHandler;
    }

    static int _2D_wrap0(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.getDefaultPitch();
    }

    static int _2D_wrap1(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.getDefaultSpeechRate();
    }

    static String[] _2D_wrap2(TextToSpeechService texttospeechservice)
    {
        return texttospeechservice.getSettingsLocale();
    }

    public TextToSpeechService()
    {
    }

    private int getDefaultPitch()
    {
        return getSecureSettingInt("tts_default_pitch", 100);
    }

    private int getDefaultSpeechRate()
    {
        return getSecureSettingInt("tts_default_rate", 100);
    }

    private int getExpectedLanguageAvailableStatus(Locale locale)
    {
        byte byte0 = 2;
        if(locale.getVariant().isEmpty())
            if(locale.getCountry().isEmpty())
                byte0 = 0;
            else
                byte0 = 1;
        return byte0;
    }

    private int getSecureSettingInt(String s, int i)
    {
        return android.provider.Settings.Secure.getInt(getContentResolver(), s, i);
    }

    private String[] getSettingsLocale()
    {
        return TtsEngines.toOldLocaleStringFormat(mEngineHelper.getLocalePrefForEngine(mPackageName));
    }

    public IBinder onBind(Intent intent)
    {
        if("android.intent.action.TTS_SERVICE".equals(intent.getAction()))
            return mBinder;
        else
            return null;
    }

    public void onCreate()
    {
        super.onCreate();
        SynthThread synththread = new SynthThread();
        synththread.start();
        mSynthHandler = new SynthHandler(synththread.getLooper());
        mAudioPlaybackHandler = new AudioPlaybackHandler();
        mAudioPlaybackHandler.start();
        mEngineHelper = new TtsEngines(this);
        mCallbacks = new CallbackMap(null);
        mPackageName = getApplicationInfo().packageName;
        String as[] = getSettingsLocale();
        onLoadLanguage(as[0], as[1], as[2]);
    }

    public void onDestroy()
    {
        mSynthHandler.quit();
        mAudioPlaybackHandler.quit();
        mCallbacks.kill();
        super.onDestroy();
    }

    public String onGetDefaultVoiceNameFor(String s, String s1, String s2)
    {
        onIsLanguageAvailable(s, s1, s2);
        JVM INSTR tableswitch 0 2: default 32
    //                   0 34
    //                   1 61
    //                   2 74;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_74;
_L1:
        return null;
_L2:
        s = new Locale(s);
_L5:
        s = TtsEngines.normalizeTTSLocale(s).toLanguageTag();
        if(onIsValidVoiceName(s) == 0)
            return s;
        else
            return null;
_L3:
        s = new Locale(s, s1);
          goto _L5
        s = new Locale(s, s1, s2);
          goto _L5
    }

    protected Set onGetFeaturesForLanguage(String s, String s1, String s2)
    {
        return new HashSet();
    }

    protected abstract String[] onGetLanguage();

    public List onGetVoices()
    {
        ArrayList arraylist;
        Locale alocale[];
        int i;
        int j;
        arraylist = new ArrayList();
        alocale = Locale.getAvailableLocales();
        i = alocale.length;
        j = 0;
_L2:
        Locale locale;
        int k;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        locale = alocale[j];
        k = getExpectedLanguageAvailableStatus(locale);
        int l = onIsLanguageAvailable(locale.getISO3Language(), locale.getISO3Country(), locale.getVariant());
        if(l == k)
        {
            Set set = onGetFeaturesForLanguage(locale.getISO3Language(), locale.getISO3Country(), locale.getVariant());
            arraylist.add(new Voice(onGetDefaultVoiceNameFor(locale.getISO3Language(), locale.getISO3Country(), locale.getVariant()), locale, 300, 300, false, set));
        }
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return arraylist;
        MissingResourceException missingresourceexception;
        missingresourceexception;
          goto _L3
    }

    protected abstract int onIsLanguageAvailable(String s, String s1, String s2);

    public int onIsValidVoiceName(String s)
    {
        s = Locale.forLanguageTag(s);
        if(s == null)
            return -1;
        int i = getExpectedLanguageAvailableStatus(s);
        int j;
        try
        {
            j = onIsLanguageAvailable(s.getISO3Language(), s.getISO3Country(), s.getVariant());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return -1;
        }
        return j == i ? 0 : -1;
    }

    protected abstract int onLoadLanguage(String s, String s1, String s2);

    public int onLoadVoice(String s)
    {
        int i;
        s = Locale.forLanguageTag(s);
        if(s == null)
            return -1;
        i = getExpectedLanguageAvailableStatus(s);
        if(onIsLanguageAvailable(s.getISO3Language(), s.getISO3Country(), s.getVariant()) != i)
            return -1;
        try
        {
            onLoadLanguage(s.getISO3Language(), s.getISO3Country(), s.getVariant());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return -1;
        }
        return 0;
    }

    protected abstract void onStop();

    protected abstract void onSynthesizeText(SynthesisRequest synthesisrequest, SynthesisCallback synthesiscallback);

    private static final boolean DBG = false;
    private static final String SYNTH_THREAD_NAME = "SynthThread";
    private static final String TAG = "TextToSpeechService";
    private AudioPlaybackHandler mAudioPlaybackHandler;
    private final ITextToSpeechService.Stub mBinder = new ITextToSpeechService.Stub() {

        private transient boolean checkNonNull(Object aobj[])
        {
            int i = aobj.length;
            for(int j = 0; j < i; j++)
                if(aobj[j] == null)
                    return false;

            return true;
        }

        private String intern(String s)
        {
            return s.intern();
        }

        public String[] getClientDefaultLanguage()
        {
            return TextToSpeechService._2D_wrap2(TextToSpeechService.this);
        }

        public String getDefaultVoiceNameFor(String s, String s1, String s2)
        {
            if(!checkNonNull(new Object[] {
        s
    }))
                return null;
            for(int i = onIsLanguageAvailable(s, s1, s2); i == 0 || i == 1 || i == 2;)
                return onGetDefaultVoiceNameFor(s, s1, s2);

            return null;
        }

        public String[] getFeaturesForLanguage(String s, String s1, String s2)
        {
            s1 = onGetFeaturesForLanguage(s, s1, s2);
            if(s1 != null)
            {
                s = new String[s1.size()];
                s1.toArray(s);
            } else
            {
                s = new String[0];
            }
            return s;
        }

        public String[] getLanguage()
        {
            return onGetLanguage();
        }

        public List getVoices()
        {
            return onGetVoices();
        }

        public int isLanguageAvailable(String s, String s1, String s2)
        {
            if(!checkNonNull(new Object[] {
        s
    }))
                return -1;
            else
                return onIsLanguageAvailable(s, s1, s2);
        }

        public boolean isSpeaking()
        {
            boolean flag;
            if(!TextToSpeechService._2D_get3(TextToSpeechService.this).isSpeaking())
                flag = TextToSpeechService._2D_get0(TextToSpeechService.this).isSpeaking();
            else
                flag = true;
            return flag;
        }

        public int loadLanguage(IBinder ibinder, String s, String s1, String s2)
        {
            if(!checkNonNull(new Object[] {
        s
    }))
                return -1;
            int i = onIsLanguageAvailable(s, s1, s2);
            if(i == 0 || i == 1 || i == 2)
            {
                ibinder = new LoadLanguageItem(ibinder, Binder.getCallingUid(), Binder.getCallingPid(), s, s1, s2);
                if(TextToSpeechService._2D_get3(TextToSpeechService.this).enqueueSpeechItem(1, ibinder) != 0)
                    return -1;
            }
            return i;
        }

        public int loadVoice(IBinder ibinder, String s)
        {
            if(!checkNonNull(new Object[] {
        s
    }))
                return -1;
            int i = onIsValidVoiceName(s);
            if(i == 0)
            {
                ibinder = new LoadVoiceItem(ibinder, Binder.getCallingUid(), Binder.getCallingPid(), s);
                if(TextToSpeechService._2D_get3(TextToSpeechService.this).enqueueSpeechItem(1, ibinder) != 0)
                    return -1;
            }
            return i;
        }

        public int playAudio(IBinder ibinder, Uri uri, int i, Bundle bundle, String s)
        {
            if(!checkNonNull(new Object[] {
        ibinder, uri, bundle
    }))
            {
                return -1;
            } else
            {
                ibinder = new AudioSpeechItem(ibinder, Binder.getCallingUid(), Binder.getCallingPid(), bundle, s, uri);
                return TextToSpeechService._2D_get3(TextToSpeechService.this).enqueueSpeechItem(i, ibinder);
            }
        }

        public int playSilence(IBinder ibinder, long l, int i, String s)
        {
            if(!checkNonNull(new Object[] {
        ibinder
    }))
            {
                return -1;
            } else
            {
                ibinder = new SilenceSpeechItem(ibinder, Binder.getCallingUid(), Binder.getCallingPid(), s, l);
                return TextToSpeechService._2D_get3(TextToSpeechService.this).enqueueSpeechItem(i, ibinder);
            }
        }

        public void setCallback(IBinder ibinder, ITextToSpeechCallback itexttospeechcallback)
        {
            if(!checkNonNull(new Object[] {
        ibinder
    }))
            {
                return;
            } else
            {
                TextToSpeechService._2D_get1(TextToSpeechService.this).setCallback(ibinder, itexttospeechcallback);
                return;
            }
        }

        public int speak(IBinder ibinder, CharSequence charsequence, int i, Bundle bundle, String s)
        {
            if(!checkNonNull(new Object[] {
        ibinder, charsequence, bundle
    }))
            {
                return -1;
            } else
            {
                ibinder = new SynthesisSpeechItem(ibinder, Binder.getCallingUid(), Binder.getCallingPid(), bundle, s, charsequence);
                return TextToSpeechService._2D_get3(TextToSpeechService.this).enqueueSpeechItem(i, ibinder);
            }
        }

        public int stop(IBinder ibinder)
        {
            if(!checkNonNull(new Object[] {
        ibinder
    }))
                return -1;
            else
                return TextToSpeechService._2D_get3(TextToSpeechService.this).stopForApp(ibinder);
        }

        public int synthesizeToFileDescriptor(IBinder ibinder, CharSequence charsequence, ParcelFileDescriptor parcelfiledescriptor, Bundle bundle, String s)
        {
            if(!checkNonNull(new Object[] {
        ibinder, charsequence, parcelfiledescriptor, bundle
    }))
            {
                return -1;
            } else
            {
                parcelfiledescriptor = ParcelFileDescriptor.adoptFd(parcelfiledescriptor.detachFd());
                ibinder = new SynthesisToFileOutputStreamSpeechItem(ibinder, Binder.getCallingUid(), Binder.getCallingPid(), bundle, s, charsequence, new android.os.ParcelFileDescriptor.AutoCloseOutputStream(parcelfiledescriptor));
                return TextToSpeechService._2D_get3(TextToSpeechService.this).enqueueSpeechItem(1, ibinder);
            }
        }

        final TextToSpeechService this$0;

            
            {
                this$0 = TextToSpeechService.this;
                super();
            }
    }
;
    private CallbackMap mCallbacks;
    private TtsEngines mEngineHelper;
    private String mPackageName;
    private SynthHandler mSynthHandler;
    private final Object mVoicesInfoLock = new Object();
}
