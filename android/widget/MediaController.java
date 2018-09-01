// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.policy.MiuiPhoneWindow;
import java.util.Formatter;
import java.util.Locale;

// Referenced classes of package android.widget:
//            FrameLayout, ImageButton, ProgressBar, SeekBar, 
//            TextView

public class MediaController extends FrameLayout
{
    public static interface MediaPlayerControl
    {

        public abstract boolean canPause();

        public abstract boolean canSeekBackward();

        public abstract boolean canSeekForward();

        public abstract int getAudioSessionId();

        public abstract int getBufferPercentage();

        public abstract int getCurrentPosition();

        public abstract int getDuration();

        public abstract boolean isPlaying();

        public abstract void pause();

        public abstract void seekTo(int i);

        public abstract void start();
    }


    static TextView _2D_get0(MediaController mediacontroller)
    {
        return mediacontroller.mCurrentTime;
    }

    static View _2D_get1(MediaController mediacontroller)
    {
        return mediacontroller.mDecor;
    }

    static android.view.WindowManager.LayoutParams _2D_get2(MediaController mediacontroller)
    {
        return mediacontroller.mDecorLayoutParams;
    }

    static boolean _2D_get3(MediaController mediacontroller)
    {
        return mediacontroller.mDragging;
    }

    static MediaPlayerControl _2D_get4(MediaController mediacontroller)
    {
        return mediacontroller.mPlayer;
    }

    static Runnable _2D_get5(MediaController mediacontroller)
    {
        return mediacontroller.mShowProgress;
    }

    static boolean _2D_get6(MediaController mediacontroller)
    {
        return mediacontroller.mShowing;
    }

    static WindowManager _2D_get7(MediaController mediacontroller)
    {
        return mediacontroller.mWindowManager;
    }

    static boolean _2D_set0(MediaController mediacontroller, boolean flag)
    {
        mediacontroller.mDragging = flag;
        return flag;
    }

    static int _2D_wrap0(MediaController mediacontroller)
    {
        return mediacontroller.setProgress();
    }

    static String _2D_wrap1(MediaController mediacontroller, int i)
    {
        return mediacontroller.stringForTime(i);
    }

    static void _2D_wrap2(MediaController mediacontroller)
    {
        mediacontroller.doPauseResume();
    }

    static void _2D_wrap3(MediaController mediacontroller)
    {
        mediacontroller.updateFloatingWindowLayout();
    }

    static void _2D_wrap4(MediaController mediacontroller)
    {
        mediacontroller.updatePausePlay();
    }

    public MediaController(Context context)
    {
        this(context, true);
    }

    public MediaController(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLayoutChangeListener = new android.view.View.OnLayoutChangeListener() {

            public void onLayoutChange(View view, int i, int j, int k, int l, int i1, int j1, 
                    int k1, int l1)
            {
                MediaController._2D_wrap3(MediaController.this);
                if(MediaController._2D_get6(MediaController.this))
                    MediaController._2D_get7(MediaController.this).updateViewLayout(MediaController._2D_get1(MediaController.this), MediaController._2D_get2(MediaController.this));
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mTouchListener = new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                if(motionevent.getAction() == 0 && MediaController._2D_get6(MediaController.this))
                    hide();
                return false;
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mFadeOut = new Runnable() {

            public void run()
            {
                hide();
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mShowProgress = new Runnable() {

            public void run()
            {
                int i = MediaController._2D_wrap0(MediaController.this);
                if(!MediaController._2D_get3(MediaController.this) && MediaController._2D_get6(MediaController.this) && MediaController._2D_get4(MediaController.this).isPlaying())
                    postDelayed(MediaController._2D_get5(MediaController.this), 1000 - i % 1000);
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mPauseListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                MediaController._2D_wrap2(MediaController.this);
                show(3000);
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mSeekListener = new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                if(!flag)
                    return;
                long l = MediaController._2D_get4(MediaController.this).getDuration();
                l = ((long)i * l) / 1000L;
                MediaController._2D_get4(MediaController.this).seekTo((int)l);
                if(MediaController._2D_get0(MediaController.this) != null)
                    MediaController._2D_get0(MediaController.this).setText(MediaController._2D_wrap1(MediaController.this, (int)l));
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
                show(0x36ee80);
                MediaController._2D_set0(MediaController.this, true);
                removeCallbacks(MediaController._2D_get5(MediaController.this));
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
                MediaController._2D_set0(MediaController.this, false);
                MediaController._2D_wrap0(MediaController.this);
                MediaController._2D_wrap4(MediaController.this);
                show(3000);
                post(MediaController._2D_get5(MediaController.this));
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mRewListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                int i = MediaController._2D_get4(MediaController.this).getCurrentPosition();
                MediaController._2D_get4(MediaController.this).seekTo(i - 5000);
                MediaController._2D_wrap0(MediaController.this);
                show(3000);
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mFfwdListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                int i = MediaController._2D_get4(MediaController.this).getCurrentPosition();
                MediaController._2D_get4(MediaController.this).seekTo(i + 15000);
                MediaController._2D_wrap0(MediaController.this);
                show(3000);
            }

            final MediaController this$0;

            
            {
                this$0 = MediaController.this;
                super();
            }
        }
;
        mRoot = this;
        mContext = context;
        mUseFastForward = true;
        mFromXml = true;
        mAccessibilityManager = AccessibilityManager.getInstance(context);
    }

    public MediaController(Context context, boolean flag)
    {
        super(context);
        mLayoutChangeListener = new _cls1();
        mTouchListener = new _cls2();
        mFadeOut = new _cls3();
        mShowProgress = new _cls4();
        mPauseListener = new _cls5();
        mSeekListener = new _cls6();
        mRewListener = new _cls7();
        mFfwdListener = new _cls8();
        mContext = context;
        mUseFastForward = flag;
        initFloatingWindowLayout();
        initFloatingWindow();
        mAccessibilityManager = AccessibilityManager.getInstance(context);
    }

    private void disableUnsupportedButtons()
    {
        if(mPauseButton != null && mPlayer.canPause() ^ true)
            mPauseButton.setEnabled(false);
        if(mRewButton != null && mPlayer.canSeekBackward() ^ true)
            mRewButton.setEnabled(false);
        if(mFfwdButton != null && mPlayer.canSeekForward() ^ true)
            mFfwdButton.setEnabled(false);
        if(mProgress != null && mPlayer.canSeekBackward() ^ true && mPlayer.canSeekForward() ^ true)
            mProgress.setEnabled(false);
_L2:
        return;
        IncompatibleClassChangeError incompatibleclasschangeerror;
        incompatibleclasschangeerror;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void doPauseResume()
    {
        if(mPlayer.isPlaying())
            mPlayer.pause();
        else
            mPlayer.start();
        updatePausePlay();
    }

    private void initControllerView(View view)
    {
        boolean flag = false;
        Resources resources = mContext.getResources();
        mPlayDescription = resources.getText(0x1040336);
        mPauseDescription = resources.getText(0x1040335);
        mPauseButton = (ImageButton)view.findViewById(0x1020365);
        if(mPauseButton != null)
        {
            mPauseButton.requestFocus();
            mPauseButton.setOnClickListener(mPauseListener);
        }
        mFfwdButton = (ImageButton)view.findViewById(0x1020245);
        int i;
        if(mFfwdButton != null)
        {
            mFfwdButton.setOnClickListener(mFfwdListener);
            if(!mFromXml)
            {
                ImageButton imagebutton = mFfwdButton;
                if(mUseFastForward)
                    i = 0;
                else
                    i = 8;
                imagebutton.setVisibility(i);
            }
        }
        mRewButton = (ImageButton)view.findViewById(0x10203ad);
        if(mRewButton != null)
        {
            mRewButton.setOnClickListener(mRewListener);
            if(!mFromXml)
            {
                imagebutton = mRewButton;
                if(mUseFastForward)
                    i = ((flag) ? 1 : 0);
                else
                    i = 8;
                imagebutton.setVisibility(i);
            }
        }
        mNextButton = (ImageButton)view.findViewById(0x1020322);
        if(mNextButton != null && mFromXml ^ true && mListenersSet ^ true)
            mNextButton.setVisibility(8);
        mPrevButton = (ImageButton)view.findViewById(0x102038b);
        if(mPrevButton != null && mFromXml ^ true && mListenersSet ^ true)
            mPrevButton.setVisibility(8);
        mProgress = (ProgressBar)view.findViewById(0x10202fd);
        if(mProgress != null)
        {
            if(mProgress instanceof SeekBar)
                ((SeekBar)mProgress).setOnSeekBarChangeListener(mSeekListener);
            mProgress.setMax(1000);
        }
        mEndTime = (TextView)view.findViewById(0x102044d);
        mCurrentTime = (TextView)view.findViewById(0x1020450);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        installPrevNextListeners();
    }

    private void initFloatingWindow()
    {
        mWindowManager = (WindowManager)mContext.getSystemService("window");
        mWindow = new MiuiPhoneWindow(mContext);
        mWindow.setWindowManager(mWindowManager, null, null);
        mWindow.requestFeature(1);
        mDecor = mWindow.getDecorView();
        mDecor.setOnTouchListener(mTouchListener);
        mWindow.setContentView(this);
        mWindow.setBackgroundDrawableResource(0x106000d);
        mWindow.setVolumeControlStream(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDescendantFocusability(0x40000);
        requestFocus();
    }

    private void initFloatingWindowLayout()
    {
        mDecorLayoutParams = new android.view.WindowManager.LayoutParams();
        android.view.WindowManager.LayoutParams layoutparams = mDecorLayoutParams;
        layoutparams.gravity = 51;
        layoutparams.height = -2;
        layoutparams.x = 0;
        layoutparams.format = -3;
        layoutparams.type = 1000;
        layoutparams.flags = layoutparams.flags | 0x820020;
        layoutparams.token = null;
        layoutparams.windowAnimations = 0;
    }

    private void installPrevNextListeners()
    {
        boolean flag = true;
        boolean flag1;
        if(mNextButton != null)
        {
            mNextButton.setOnClickListener(mNextListener);
            ImageButton imagebutton = mNextButton;
            if(mNextListener != null)
                flag1 = true;
            else
                flag1 = false;
            imagebutton.setEnabled(flag1);
        }
        if(mPrevButton != null)
        {
            mPrevButton.setOnClickListener(mPrevListener);
            imagebutton = mPrevButton;
            if(mPrevListener != null)
                flag1 = flag;
            else
                flag1 = false;
            imagebutton.setEnabled(flag1);
        }
    }

    private int setProgress()
    {
        if(mPlayer == null || mDragging)
            return 0;
        int i = mPlayer.getCurrentPosition();
        int j = mPlayer.getDuration();
        if(mProgress != null)
        {
            if(j > 0)
            {
                long l = ((long)i * 1000L) / (long)j;
                mProgress.setProgress((int)l);
            }
            int k = mPlayer.getBufferPercentage();
            mProgress.setSecondaryProgress(k * 10);
        }
        if(mEndTime != null)
            mEndTime.setText(stringForTime(j));
        if(mCurrentTime != null)
            mCurrentTime.setText(stringForTime(i));
        return i;
    }

    private String stringForTime(int i)
    {
        int j = i / 1000;
        i = j % 60;
        int k = (j / 60) % 60;
        j /= 3600;
        mFormatBuilder.setLength(0);
        if(j > 0)
            return mFormatter.format("%d:%02d:%02d", new Object[] {
                Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(i)
            }).toString();
        else
            return mFormatter.format("%02d:%02d", new Object[] {
                Integer.valueOf(k), Integer.valueOf(i)
            }).toString();
    }

    private void updateFloatingWindowLayout()
    {
        int ai[] = new int[2];
        mAnchor.getLocationOnScreen(ai);
        mDecor.measure(android.view.View.MeasureSpec.makeMeasureSpec(mAnchor.getWidth(), 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(mAnchor.getHeight(), 0x80000000));
        android.view.WindowManager.LayoutParams layoutparams = mDecorLayoutParams;
        layoutparams.width = mAnchor.getWidth();
        layoutparams.x = ai[0] + (mAnchor.getWidth() - layoutparams.width) / 2;
        layoutparams.y = (ai[1] + mAnchor.getHeight()) - mDecor.getMeasuredHeight();
    }

    private void updatePausePlay()
    {
        if(mRoot == null || mPauseButton == null)
            return;
        if(mPlayer.isPlaying())
        {
            mPauseButton.setImageResource(0x1080023);
            mPauseButton.setContentDescription(mPauseDescription);
        } else
        {
            mPauseButton.setImageResource(0x1080024);
            mPauseButton.setContentDescription(mPlayDescription);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        int i = keyevent.getKeyCode();
        boolean flag;
        if(keyevent.getRepeatCount() == 0)
        {
            if(keyevent.getAction() == 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        while(i == 79 || i == 85 || i == 62) 
        {
            if(flag)
            {
                doPauseResume();
                show(3000);
                if(mPauseButton != null)
                    mPauseButton.requestFocus();
            }
            return true;
        }
        if(i == 126)
        {
            if(flag && mPlayer.isPlaying() ^ true)
            {
                mPlayer.start();
                updatePausePlay();
                show(3000);
            }
            return true;
        }
        if(i == 86 || i == 127)
        {
            if(flag && mPlayer.isPlaying())
            {
                mPlayer.pause();
                updatePausePlay();
                show(3000);
            }
            return true;
        }
        while(i == 25 || i == 24 || i == 164 || i == 27) 
            return super.dispatchKeyEvent(keyevent);
        if(i == 4 || i == 82)
        {
            if(flag)
                hide();
            return true;
        } else
        {
            show(3000);
            return super.dispatchKeyEvent(keyevent);
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/MediaController.getName();
    }

    public void hide()
    {
        if(mAnchor == null)
            return;
        if(mShowing)
        {
            try
            {
                removeCallbacks(mShowProgress);
                mWindowManager.removeView(mDecor);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.w("MediaController", "already removed");
            }
            mShowing = false;
        }
    }

    public boolean isShowing()
    {
        return mShowing;
    }

    protected View makeControllerView()
    {
        mRoot = ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(0x109007f, null);
        initControllerView(mRoot);
        return mRoot;
    }

    public void onFinishInflate()
    {
        if(mRoot != null)
            initControllerView(mRoot);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 38
    //                   1 46
    //                   2 36
    //                   3 56;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return true;
_L2:
        show(0);
        continue; /* Loop/switch isn't completed */
_L3:
        show(3000);
        continue; /* Loop/switch isn't completed */
_L4:
        hide();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onTrackballEvent(MotionEvent motionevent)
    {
        show(3000);
        return false;
    }

    public void setAnchorView(View view)
    {
        if(mAnchor != null)
            mAnchor.removeOnLayoutChangeListener(mLayoutChangeListener);
        mAnchor = view;
        if(mAnchor != null)
            mAnchor.addOnLayoutChangeListener(mLayoutChangeListener);
        view = new FrameLayout.LayoutParams(-1, -1);
        removeAllViews();
        addView(makeControllerView(), view);
    }

    public void setEnabled(boolean flag)
    {
        boolean flag1 = false;
        if(mPauseButton != null)
            mPauseButton.setEnabled(flag);
        if(mFfwdButton != null)
            mFfwdButton.setEnabled(flag);
        if(mRewButton != null)
            mRewButton.setEnabled(flag);
        if(mNextButton != null)
        {
            ImageButton imagebutton = mNextButton;
            boolean flag2;
            if(flag && mNextListener != null)
                flag2 = true;
            else
                flag2 = false;
            imagebutton.setEnabled(flag2);
        }
        if(mPrevButton != null)
        {
            imagebutton = mPrevButton;
            flag2 = flag1;
            if(flag)
            {
                flag2 = flag1;
                if(mPrevListener != null)
                    flag2 = true;
            }
            imagebutton.setEnabled(flag2);
        }
        if(mProgress != null)
            mProgress.setEnabled(flag);
        disableUnsupportedButtons();
        super.setEnabled(flag);
    }

    public void setMediaPlayer(MediaPlayerControl mediaplayercontrol)
    {
        mPlayer = mediaplayercontrol;
        updatePausePlay();
    }

    public void setPrevNextListeners(android.view.View.OnClickListener onclicklistener, android.view.View.OnClickListener onclicklistener1)
    {
        mNextListener = onclicklistener;
        mPrevListener = onclicklistener1;
        mListenersSet = true;
        if(mRoot != null)
        {
            installPrevNextListeners();
            if(mNextButton != null && mFromXml ^ true)
                mNextButton.setVisibility(0);
            if(mPrevButton != null && mFromXml ^ true)
                mPrevButton.setVisibility(0);
        }
    }

    public void show()
    {
        show(3000);
    }

    public void show(int i)
    {
        if(!mShowing && mAnchor != null)
        {
            setProgress();
            if(mPauseButton != null)
                mPauseButton.requestFocus();
            disableUnsupportedButtons();
            updateFloatingWindowLayout();
            mWindowManager.addView(mDecor, mDecorLayoutParams);
            mShowing = true;
        }
        updatePausePlay();
        post(mShowProgress);
        if(i != 0 && mAccessibilityManager.isTouchExplorationEnabled() ^ true)
        {
            removeCallbacks(mFadeOut);
            postDelayed(mFadeOut, i);
        }
    }

    private static final int sDefaultTimeout = 3000;
    private final AccessibilityManager mAccessibilityManager;
    private View mAnchor;
    private final Context mContext;
    private TextView mCurrentTime;
    private View mDecor;
    private android.view.WindowManager.LayoutParams mDecorLayoutParams;
    private boolean mDragging;
    private TextView mEndTime;
    private final Runnable mFadeOut;
    private ImageButton mFfwdButton;
    private final android.view.View.OnClickListener mFfwdListener;
    StringBuilder mFormatBuilder;
    Formatter mFormatter;
    private boolean mFromXml;
    private final android.view.View.OnLayoutChangeListener mLayoutChangeListener;
    private boolean mListenersSet;
    private ImageButton mNextButton;
    private android.view.View.OnClickListener mNextListener;
    private ImageButton mPauseButton;
    private CharSequence mPauseDescription;
    private final android.view.View.OnClickListener mPauseListener;
    private CharSequence mPlayDescription;
    private MediaPlayerControl mPlayer;
    private ImageButton mPrevButton;
    private android.view.View.OnClickListener mPrevListener;
    private ProgressBar mProgress;
    private ImageButton mRewButton;
    private final android.view.View.OnClickListener mRewListener;
    private View mRoot;
    private final SeekBar.OnSeekBarChangeListener mSeekListener;
    private final Runnable mShowProgress;
    private boolean mShowing;
    private final android.view.View.OnTouchListener mTouchListener;
    private final boolean mUseFastForward;
    private Window mWindow;
    private WindowManager mWindowManager;
}
