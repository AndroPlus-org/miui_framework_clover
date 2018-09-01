// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.media.*;
import android.net.Uri;
import android.os.Looper;
import android.util.*;
import android.view.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

// Referenced classes of package android.widget:
//            MediaController

public class VideoView extends SurfaceView
    implements MediaController.MediaPlayerControl, android.media.SubtitleController.Anchor
{

    static int _2D_get0(VideoView videoview)
    {
        return videoview.mAudioFocusType;
    }

    static AudioManager _2D_get1(VideoView videoview)
    {
        return videoview.mAudioManager;
    }

    static int _2D_get10(VideoView videoview)
    {
        return videoview.mSurfaceHeight;
    }

    static int _2D_get11(VideoView videoview)
    {
        return videoview.mSurfaceWidth;
    }

    static int _2D_get12(VideoView videoview)
    {
        return videoview.mTargetState;
    }

    static int _2D_get13(VideoView videoview)
    {
        return videoview.mVideoHeight;
    }

    static int _2D_get14(VideoView videoview)
    {
        return videoview.mVideoWidth;
    }

    static Context _2D_get2(VideoView videoview)
    {
        return videoview.mContext;
    }

    static MediaController _2D_get3(VideoView videoview)
    {
        return videoview.mMediaController;
    }

    static MediaPlayer _2D_get4(VideoView videoview)
    {
        return videoview.mMediaPlayer;
    }

    static android.media.MediaPlayer.OnCompletionListener _2D_get5(VideoView videoview)
    {
        return videoview.mOnCompletionListener;
    }

    static android.media.MediaPlayer.OnErrorListener _2D_get6(VideoView videoview)
    {
        return videoview.mOnErrorListener;
    }

    static android.media.MediaPlayer.OnInfoListener _2D_get7(VideoView videoview)
    {
        return videoview.mOnInfoListener;
    }

    static android.media.MediaPlayer.OnPreparedListener _2D_get8(VideoView videoview)
    {
        return videoview.mOnPreparedListener;
    }

    static int _2D_get9(VideoView videoview)
    {
        return videoview.mSeekWhenPrepared;
    }

    static boolean _2D_set0(VideoView videoview, boolean flag)
    {
        videoview.mCanPause = flag;
        return flag;
    }

    static boolean _2D_set1(VideoView videoview, boolean flag)
    {
        videoview.mCanSeekBack = flag;
        return flag;
    }

    static int _2D_set10(VideoView videoview, int i)
    {
        videoview.mVideoWidth = i;
        return i;
    }

    static boolean _2D_set2(VideoView videoview, boolean flag)
    {
        videoview.mCanSeekForward = flag;
        return flag;
    }

    static int _2D_set3(VideoView videoview, int i)
    {
        videoview.mCurrentBufferPercentage = i;
        return i;
    }

    static int _2D_set4(VideoView videoview, int i)
    {
        videoview.mCurrentState = i;
        return i;
    }

    static int _2D_set5(VideoView videoview, int i)
    {
        videoview.mSurfaceHeight = i;
        return i;
    }

    static SurfaceHolder _2D_set6(VideoView videoview, SurfaceHolder surfaceholder)
    {
        videoview.mSurfaceHolder = surfaceholder;
        return surfaceholder;
    }

    static int _2D_set7(VideoView videoview, int i)
    {
        videoview.mSurfaceWidth = i;
        return i;
    }

    static int _2D_set8(VideoView videoview, int i)
    {
        videoview.mTargetState = i;
        return i;
    }

    static int _2D_set9(VideoView videoview, int i)
    {
        videoview.mVideoHeight = i;
        return i;
    }

    static void _2D_wrap0(VideoView videoview)
    {
        videoview.openVideo();
    }

    static void _2D_wrap1(VideoView videoview, boolean flag)
    {
        videoview.release(flag);
    }

    public VideoView(Context context)
    {
        this(context, null);
    }

    public VideoView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public VideoView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public VideoView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mPendingSubtitleTracks = new Vector();
        mCurrentState = 0;
        mTargetState = 0;
        mSurfaceHolder = null;
        mMediaPlayer = null;
        mAudioFocusType = 1;
        mVideoWidth = 0;
        mVideoHeight = 0;
        mAudioManager = (AudioManager)mContext.getSystemService("audio");
        mAudioAttributes = (new android.media.AudioAttributes.Builder()).setUsage(1).setContentType(3).build();
        getHolder().addCallback(mSHCallback);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        mCurrentState = 0;
        mTargetState = 0;
    }

    private void attachMediaController()
    {
        if(mMediaPlayer != null && mMediaController != null)
        {
            mMediaController.setMediaPlayer(this);
            Object obj;
            if(getParent() instanceof View)
                obj = (View)getParent();
            else
                obj = this;
            mMediaController.setAnchorView(((View) (obj)));
            mMediaController.setEnabled(isInPlaybackState());
        }
    }

    private boolean isInPlaybackState()
    {
        boolean flag = true;
        if(mMediaPlayer != null && mCurrentState != -1 && mCurrentState != 0)
        {
            if(mCurrentState == 1)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void measureAndLayoutSubtitleWidget()
    {
        int i = getWidth();
        int j = getPaddingLeft();
        int k = getPaddingRight();
        int l = getHeight();
        int i1 = getPaddingTop();
        int j1 = getPaddingBottom();
        mSubtitleWidget.setSize(i - j - k, l - i1 - j1);
    }

    private void openVideo()
    {
        if(mUri == null || mSurfaceHolder == null)
            return;
        release(false);
        if(mAudioFocusType != 0)
            mAudioManager.requestAudioFocus(null, mAudioAttributes, mAudioFocusType, 0);
        Object obj = JVM INSTR new #349 <Class MediaPlayer>;
        ((MediaPlayer) (obj)).MediaPlayer();
        mMediaPlayer = ((MediaPlayer) (obj));
        Context context = getContext();
        obj = JVM INSTR new #356 <Class SubtitleController>;
        ((SubtitleController) (obj)).SubtitleController(context, mMediaPlayer.getMediaTimeProvider(), mMediaPlayer);
        Object obj3 = JVM INSTR new #365 <Class WebVttRenderer>;
        ((WebVttRenderer) (obj3)).WebVttRenderer(context);
        ((SubtitleController) (obj)).registerRenderer(((android.media.SubtitleController.Renderer) (obj3)));
        obj3 = JVM INSTR new #373 <Class TtmlRenderer>;
        ((TtmlRenderer) (obj3)).TtmlRenderer(context);
        ((SubtitleController) (obj)).registerRenderer(((android.media.SubtitleController.Renderer) (obj3)));
        obj3 = JVM INSTR new #376 <Class Cea708CaptionRenderer>;
        ((Cea708CaptionRenderer) (obj3)).Cea708CaptionRenderer(context);
        ((SubtitleController) (obj)).registerRenderer(((android.media.SubtitleController.Renderer) (obj3)));
        obj3 = JVM INSTR new #379 <Class ClosedCaptionRenderer>;
        ((ClosedCaptionRenderer) (obj3)).ClosedCaptionRenderer(context);
        ((SubtitleController) (obj)).registerRenderer(((android.media.SubtitleController.Renderer) (obj3)));
        mMediaPlayer.setSubtitleAnchor(((SubtitleController) (obj)), this);
        if(mAudioSession == 0)
            break MISSING_BLOCK_LABEL_435;
        mMediaPlayer.setAudioSessionId(mAudioSession);
_L2:
        Iterator iterator;
        mMediaPlayer.setOnPreparedListener(mPreparedListener);
        mMediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
        mMediaPlayer.setOnErrorListener(mErrorListener);
        mMediaPlayer.setOnInfoListener(mInfoListener);
        mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
        mCurrentBufferPercentage = 0;
        mMediaPlayer.setDataSource(mContext, mUri, mHeaders);
        mMediaPlayer.setDisplay(mSurfaceHolder);
        mMediaPlayer.setAudioAttributes(mAudioAttributes);
        mMediaPlayer.setScreenOnWhilePlaying(true);
        mMediaPlayer.prepareAsync();
        iterator = mPendingSubtitleTracks.iterator();
_L1:
        Pair pair;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_516;
        pair = (Pair)iterator.next();
        mMediaPlayer.addSubtitleSource((InputStream)pair.first, (MediaFormat)pair.second);
          goto _L1
        Object obj2;
        obj2;
        mInfoListener.onInfo(mMediaPlayer, 901, 0);
          goto _L1
        obj2;
        StringBuilder stringbuilder = JVM INSTR new #473 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("VideoView", stringbuilder.append("Unable to open content: ").append(mUri).toString(), ((Throwable) (obj2)));
        mCurrentState = -1;
        mTargetState = -1;
        mErrorListener.onError(mMediaPlayer, 1, 0);
        mPendingSubtitleTracks.clear();
        return;
        mAudioSession = mMediaPlayer.getAudioSessionId();
          goto _L2
        Object obj1;
        obj1;
        StringBuilder stringbuilder1 = JVM INSTR new #473 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.w("VideoView", stringbuilder1.append("Unable to open content: ").append(mUri).toString(), ((Throwable) (obj1)));
        mCurrentState = -1;
        mTargetState = -1;
        mErrorListener.onError(mMediaPlayer, 1, 0);
        mPendingSubtitleTracks.clear();
        return;
        mCurrentState = 1;
        attachMediaController();
        mPendingSubtitleTracks.clear();
        return;
        obj1;
        mPendingSubtitleTracks.clear();
        throw obj1;
    }

    private void release(boolean flag)
    {
        if(mMediaPlayer != null)
        {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mPendingSubtitleTracks.clear();
            mCurrentState = 0;
            if(flag)
                mTargetState = 0;
            if(mAudioFocusType != 0)
                mAudioManager.abandonAudioFocus(null);
        }
    }

    private void toggleMediaControlsVisiblity()
    {
        if(mMediaController.isShowing())
            mMediaController.hide();
        else
            mMediaController.show();
    }

    public void addSubtitleSource(InputStream inputstream, MediaFormat mediaformat)
    {
        if(mMediaPlayer == null)
            mPendingSubtitleTracks.add(Pair.create(inputstream, mediaformat));
        else
            try
            {
                mMediaPlayer.addSubtitleSource(inputstream, mediaformat);
            }
            // Misplaced declaration of an exception variable
            catch(InputStream inputstream)
            {
                mInfoListener.onInfo(mMediaPlayer, 901, 0);
            }
    }

    public boolean canPause()
    {
        return mCanPause;
    }

    public boolean canSeekBackward()
    {
        return mCanSeekBack;
    }

    public boolean canSeekForward()
    {
        return mCanSeekForward;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(mSubtitleWidget != null)
        {
            int i = canvas.save();
            canvas.translate(getPaddingLeft(), getPaddingTop());
            mSubtitleWidget.draw(canvas);
            canvas.restoreToCount(i);
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/VideoView.getName();
    }

    public int getAudioSessionId()
    {
        if(mAudioSession == 0)
        {
            MediaPlayer mediaplayer = new MediaPlayer();
            mAudioSession = mediaplayer.getAudioSessionId();
            mediaplayer.release();
        }
        return mAudioSession;
    }

    public int getBufferPercentage()
    {
        if(mMediaPlayer != null)
            return mCurrentBufferPercentage;
        else
            return 0;
    }

    public int getCurrentPosition()
    {
        if(isInPlaybackState())
            return mMediaPlayer.getCurrentPosition();
        else
            return 0;
    }

    public int getDuration()
    {
        if(isInPlaybackState())
            return mMediaPlayer.getDuration();
        else
            return -1;
    }

    public Looper getSubtitleLooper()
    {
        return Looper.getMainLooper();
    }

    public boolean isPlaying()
    {
        boolean flag;
        if(isInPlaybackState())
            flag = mMediaPlayer.isPlaying();
        else
            flag = false;
        return flag;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mSubtitleWidget != null)
            mSubtitleWidget.onAttachedToWindow();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mSubtitleWidget != null)
            mSubtitleWidget.onDetachedFromWindow();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i != 4 && i != 24 && i != 25 && i != 164 && i != 82 && i != 5)
        {
            if(i != 6)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(isInPlaybackState() && flag && mMediaController != null)
        {
            if(i == 79 || i == 85)
            {
                if(mMediaPlayer.isPlaying())
                {
                    pause();
                    mMediaController.show();
                } else
                {
                    start();
                    mMediaController.hide();
                }
                return true;
            }
            if(i == 126)
            {
                if(!mMediaPlayer.isPlaying())
                {
                    start();
                    mMediaController.hide();
                }
                return true;
            }
            if(i == 86 || i == 127)
            {
                if(mMediaPlayer.isPlaying())
                {
                    pause();
                    mMediaController.show();
                }
                return true;
            }
            toggleMediaControlsVisiblity();
        }
        return super.onKeyDown(i, keyevent);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(mSubtitleWidget != null)
            measureAndLayoutSubtitleWidget();
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int i1;
        int i2;
        int j2;
        k = getDefaultSize(mVideoWidth, i);
        i1 = getDefaultSize(mVideoHeight, j);
        i2 = i1;
        j2 = k;
        if(mVideoWidth <= 0) goto _L2; else goto _L1
_L1:
        i2 = i1;
        j2 = k;
        if(mVideoHeight <= 0) goto _L2; else goto _L3
_L3:
        int k2;
        int l2;
        k2 = android.view.View.MeasureSpec.getMode(i);
        i = android.view.View.MeasureSpec.getSize(i);
        l2 = android.view.View.MeasureSpec.getMode(j);
        j = android.view.View.MeasureSpec.getSize(j);
        if(k2 != 0x40000000 || l2 != 0x40000000) goto _L5; else goto _L4
_L4:
        i1 = i;
        i2 = j;
        if(mVideoWidth * j >= mVideoHeight * i) goto _L7; else goto _L6
_L6:
        j2 = (mVideoWidth * j) / mVideoHeight;
_L2:
        setMeasuredDimension(j2, i2);
        return;
_L7:
        j2 = i1;
        if(mVideoWidth * j > mVideoHeight * i)
        {
            i2 = (mVideoHeight * i) / mVideoWidth;
            j2 = i1;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(k2 == 0x40000000)
        {
            int j1 = i;
            i = (mVideoHeight * i) / mVideoWidth;
            i2 = i;
            j2 = j1;
            if(l2 == 0x80000000)
            {
                i2 = i;
                j2 = j1;
                if(i > j)
                {
                    i2 = j;
                    j2 = j1;
                }
            }
        } else
        if(l2 == 0x40000000)
        {
            int k1 = j;
            j = (mVideoWidth * j) / mVideoHeight;
            i2 = k1;
            j2 = j;
            if(k2 == 0x80000000)
            {
                i2 = k1;
                j2 = j;
                if(j > i)
                {
                    i2 = k1;
                    j2 = i;
                }
            }
        } else
        {
            i2 = mVideoWidth;
            j2 = mVideoHeight;
            int l = j2;
            int l1 = i2;
            if(l2 == 0x80000000)
            {
                l = j2;
                l1 = i2;
                if(j2 > j)
                {
                    l = j;
                    l1 = (mVideoWidth * j) / mVideoHeight;
                }
            }
            i2 = l;
            j2 = l1;
            if(k2 == 0x80000000)
            {
                i2 = l;
                j2 = l1;
                if(l1 > i)
                {
                    j2 = i;
                    i2 = (mVideoHeight * i) / mVideoWidth;
                }
            }
        }
        if(true) goto _L2; else goto _L8
_L8:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(motionevent.getAction() == 0 && isInPlaybackState() && mMediaController != null)
            toggleMediaControlsVisiblity();
        return super.onTouchEvent(motionevent);
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        if(motionevent.getAction() == 0 && isInPlaybackState() && mMediaController != null)
            toggleMediaControlsVisiblity();
        return super.onTrackballEvent(motionevent);
    }

    public void pause()
    {
        if(isInPlaybackState() && mMediaPlayer.isPlaying())
        {
            mMediaPlayer.pause();
            mCurrentState = 4;
        }
        mTargetState = 4;
    }

    public int resolveAdjustedSize(int i, int j)
    {
        return getDefaultSize(i, j);
    }

    public void resume()
    {
        openVideo();
    }

    public void seekTo(int i)
    {
        if(isInPlaybackState())
        {
            mMediaPlayer.seekTo(i);
            mSeekWhenPrepared = 0;
        } else
        {
            mSeekWhenPrepared = i;
        }
    }

    public void setAudioAttributes(AudioAttributes audioattributes)
    {
        if(audioattributes == null)
        {
            throw new IllegalArgumentException("Illegal null AudioAttributes");
        } else
        {
            mAudioAttributes = audioattributes;
            return;
        }
    }

    public void setAudioFocusRequest(int i)
    {
        if(i != 0 && i != 1 && i != 2 && i != 3 && i != 4)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal audio focus type ").append(i).toString());
        } else
        {
            mAudioFocusType = i;
            return;
        }
    }

    public void setMediaController(MediaController mediacontroller)
    {
        if(mMediaController != null)
            mMediaController.hide();
        mMediaController = mediacontroller;
        attachMediaController();
    }

    public void setOnCompletionListener(android.media.MediaPlayer.OnCompletionListener oncompletionlistener)
    {
        mOnCompletionListener = oncompletionlistener;
    }

    public void setOnErrorListener(android.media.MediaPlayer.OnErrorListener onerrorlistener)
    {
        mOnErrorListener = onerrorlistener;
    }

    public void setOnInfoListener(android.media.MediaPlayer.OnInfoListener oninfolistener)
    {
        mOnInfoListener = oninfolistener;
    }

    public void setOnPreparedListener(android.media.MediaPlayer.OnPreparedListener onpreparedlistener)
    {
        mOnPreparedListener = onpreparedlistener;
    }

    public void setSubtitleWidget(android.media.SubtitleTrack.RenderingWidget renderingwidget)
    {
        if(mSubtitleWidget == renderingwidget)
            return;
        boolean flag = isAttachedToWindow();
        if(mSubtitleWidget != null)
        {
            if(flag)
                mSubtitleWidget.onDetachedFromWindow();
            mSubtitleWidget.setOnChangedListener(null);
        }
        mSubtitleWidget = renderingwidget;
        if(renderingwidget != null)
        {
            if(mSubtitlesChangedListener == null)
                mSubtitlesChangedListener = new android.media.SubtitleTrack.RenderingWidget.OnChangedListener() {

                    public void onChanged(android.media.SubtitleTrack.RenderingWidget renderingwidget1)
                    {
                        invalidate();
                    }

                    final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
                }
;
            setWillNotDraw(false);
            renderingwidget.setOnChangedListener(mSubtitlesChangedListener);
            if(flag)
            {
                renderingwidget.onAttachedToWindow();
                requestLayout();
            }
        } else
        {
            setWillNotDraw(true);
        }
        invalidate();
    }

    public void setVideoPath(String s)
    {
        setVideoURI(Uri.parse(s));
    }

    public void setVideoURI(Uri uri)
    {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map map)
    {
        mUri = uri;
        mHeaders = map;
        mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void start()
    {
        if(isInPlaybackState())
        {
            mMediaPlayer.start();
            mCurrentState = 3;
        }
        mTargetState = 3;
    }

    public void stopPlayback()
    {
        if(mMediaPlayer != null)
        {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mCurrentState = 0;
            mTargetState = 0;
            mAudioManager.abandonAudioFocus(null);
        }
    }

    public void suspend()
    {
        release(false);
    }

    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final String TAG = "VideoView";
    private AudioAttributes mAudioAttributes;
    private int mAudioFocusType;
    private AudioManager mAudioManager;
    private int mAudioSession;
    private android.media.MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new android.media.MediaPlayer.OnBufferingUpdateListener() {

        public void onBufferingUpdate(MediaPlayer mediaplayer, int k)
        {
            VideoView._2D_set3(VideoView.this, k);
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private android.media.MediaPlayer.OnCompletionListener mCompletionListener = new android.media.MediaPlayer.OnCompletionListener() {

        public void onCompletion(MediaPlayer mediaplayer)
        {
            VideoView._2D_set4(VideoView.this, 5);
            VideoView._2D_set8(VideoView.this, 5);
            if(VideoView._2D_get3(VideoView.this) != null)
                VideoView._2D_get3(VideoView.this).hide();
            if(VideoView._2D_get5(VideoView.this) != null)
                VideoView._2D_get5(VideoView.this).onCompletion(VideoView._2D_get4(VideoView.this));
            if(VideoView._2D_get0(VideoView.this) != 0)
                VideoView._2D_get1(VideoView.this).abandonAudioFocus(null);
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private android.media.MediaPlayer.OnErrorListener mErrorListener = new android.media.MediaPlayer.OnErrorListener() {

        public boolean onError(MediaPlayer mediaplayer, int k, int l)
        {
            Log.d("VideoView", (new StringBuilder()).append("Error: ").append(k).append(",").append(l).toString());
            VideoView._2D_set4(VideoView.this, -1);
            VideoView._2D_set8(VideoView.this, -1);
            if(VideoView._2D_get3(VideoView.this) != null)
                VideoView._2D_get3(VideoView.this).hide();
            if(VideoView._2D_get6(VideoView.this) != null && VideoView._2D_get6(VideoView.this).onError(VideoView._2D_get4(VideoView.this), k, l))
                return true;
            if(getWindowToken() != null)
            {
                VideoView._2D_get2(VideoView.this).getResources();
                if(k == 200)
                    k = 0x1040015;
                else
                    k = 0x1040011;
                (new android.app.AlertDialog.Builder(VideoView._2D_get2(VideoView.this))).setMessage(k).setPositiveButton(0x1040010, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if(VideoView._2D_get5(_fld0) != null)
                            VideoView._2D_get5(_fld0).onCompletion(VideoView._2D_get4(_fld0));
                    }

                    final _cls5 this$1;

            
            {
                this$1 = _cls5.this;
                super();
            }
                }
).setCancelable(false).show();
            }
            return true;
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    private Map mHeaders;
    private android.media.MediaPlayer.OnInfoListener mInfoListener = new android.media.MediaPlayer.OnInfoListener() {

        public boolean onInfo(MediaPlayer mediaplayer, int k, int l)
        {
            if(VideoView._2D_get7(VideoView.this) != null)
                VideoView._2D_get7(VideoView.this).onInfo(mediaplayer, k, l);
            return true;
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    private MediaController mMediaController;
    private MediaPlayer mMediaPlayer;
    private android.media.MediaPlayer.OnCompletionListener mOnCompletionListener;
    private android.media.MediaPlayer.OnErrorListener mOnErrorListener;
    private android.media.MediaPlayer.OnInfoListener mOnInfoListener;
    private android.media.MediaPlayer.OnPreparedListener mOnPreparedListener;
    private final Vector mPendingSubtitleTracks;
    android.media.MediaPlayer.OnPreparedListener mPreparedListener = new android.media.MediaPlayer.OnPreparedListener() {

        public void onPrepared(MediaPlayer mediaplayer)
        {
            int k;
            VideoView._2D_set4(VideoView.this, 2);
            Metadata metadata = mediaplayer.getMetadata(false, false);
            if(metadata != null)
            {
                VideoView videoview = VideoView.this;
                boolean flag;
                if(metadata.has(1))
                    flag = metadata.getBoolean(1);
                else
                    flag = true;
                VideoView._2D_set0(videoview, flag);
                videoview = VideoView.this;
                if(metadata.has(2))
                    flag = metadata.getBoolean(2);
                else
                    flag = true;
                VideoView._2D_set1(videoview, flag);
                videoview = VideoView.this;
                if(metadata.has(3))
                    flag = metadata.getBoolean(3);
                else
                    flag = true;
                VideoView._2D_set2(videoview, flag);
            } else
            {
                VideoView._2D_set0(VideoView.this, VideoView._2D_set1(VideoView.this, VideoView._2D_set2(VideoView.this, true)));
            }
            if(VideoView._2D_get8(VideoView.this) != null)
                VideoView._2D_get8(VideoView.this).onPrepared(VideoView._2D_get4(VideoView.this));
            if(VideoView._2D_get3(VideoView.this) != null)
                VideoView._2D_get3(VideoView.this).setEnabled(true);
            VideoView._2D_set10(VideoView.this, mediaplayer.getVideoWidth());
            VideoView._2D_set9(VideoView.this, mediaplayer.getVideoHeight());
            k = VideoView._2D_get9(VideoView.this);
            if(k != 0)
                seekTo(k);
            if(VideoView._2D_get14(VideoView.this) == 0 || VideoView._2D_get13(VideoView.this) == 0) goto _L2; else goto _L1
_L1:
            getHolder().setFixedSize(VideoView._2D_get14(VideoView.this), VideoView._2D_get13(VideoView.this));
            if(VideoView._2D_get11(VideoView.this) != VideoView._2D_get14(VideoView.this) || VideoView._2D_get10(VideoView.this) != VideoView._2D_get13(VideoView.this)) goto _L4; else goto _L3
_L3:
            if(VideoView._2D_get12(VideoView.this) != 3) goto _L6; else goto _L5
_L5:
            start();
            if(VideoView._2D_get3(VideoView.this) != null)
                VideoView._2D_get3(VideoView.this).show();
_L4:
            return;
_L6:
            if(!isPlaying() && (k != 0 || getCurrentPosition() > 0) && VideoView._2D_get3(VideoView.this) != null)
                VideoView._2D_get3(VideoView.this).show(0);
            continue; /* Loop/switch isn't completed */
_L2:
            if(VideoView._2D_get12(VideoView.this) == 3)
                start();
            if(true) goto _L4; else goto _L7
_L7:
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    android.view.SurfaceHolder.Callback mSHCallback = new android.view.SurfaceHolder.Callback() {

        public void surfaceChanged(SurfaceHolder surfaceholder, int k, int l, int i1)
        {
            VideoView._2D_set7(VideoView.this, l);
            VideoView._2D_set5(VideoView.this, i1);
            if(VideoView._2D_get12(VideoView.this) == 3)
                k = 1;
            else
                k = 0;
            if(VideoView._2D_get14(VideoView.this) == l && VideoView._2D_get13(VideoView.this) == i1)
                l = 1;
            else
                l = 0;
            if(VideoView._2D_get4(VideoView.this) != null && k != 0 && l != 0)
            {
                if(VideoView._2D_get9(VideoView.this) != 0)
                    seekTo(VideoView._2D_get9(VideoView.this));
                start();
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceholder)
        {
            VideoView._2D_set6(VideoView.this, surfaceholder);
            VideoView._2D_wrap0(VideoView.this);
        }

        public void surfaceDestroyed(SurfaceHolder surfaceholder)
        {
            VideoView._2D_set6(VideoView.this, null);
            if(VideoView._2D_get3(VideoView.this) != null)
                VideoView._2D_get3(VideoView.this).hide();
            VideoView._2D_wrap1(VideoView.this, true);
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    private int mSeekWhenPrepared;
    android.media.MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new android.media.MediaPlayer.OnVideoSizeChangedListener() {

        public void onVideoSizeChanged(MediaPlayer mediaplayer, int k, int l)
        {
            VideoView._2D_set10(VideoView.this, mediaplayer.getVideoWidth());
            VideoView._2D_set9(VideoView.this, mediaplayer.getVideoHeight());
            if(VideoView._2D_get14(VideoView.this) != 0 && VideoView._2D_get13(VideoView.this) != 0)
            {
                getHolder().setFixedSize(VideoView._2D_get14(VideoView.this), VideoView._2D_get13(VideoView.this));
                requestLayout();
            }
        }

        final VideoView this$0;

            
            {
                this$0 = VideoView.this;
                super();
            }
    }
;
    private android.media.SubtitleTrack.RenderingWidget mSubtitleWidget;
    private android.media.SubtitleTrack.RenderingWidget.OnChangedListener mSubtitlesChangedListener;
    private int mSurfaceHeight;
    private SurfaceHolder mSurfaceHolder;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoWidth;
}
