// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.view;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.SeekBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miui.util.AudioManagerHelper;
import miui.util.CustomizeUtil;

// Referenced classes of package miui.view:
//            RingerModeLayout

public class VolumeDialog
{
    private final class CustomDialog extends Dialog
    {

        public boolean dispatchTouchEvent(MotionEvent motionevent)
        {
            Rect rect = new Rect();
            if(VolumeDialog._2D_get6(VolumeDialog.this) != null)
                VolumeDialog._2D_get6(VolumeDialog.this).getHitRect(rect);
            if(motionevent.getAction() == 0 && rect.contains((int)motionevent.getX(), (int)motionevent.getY()) ^ true)
                VolumeDialog._2D_wrap1(VolumeDialog.this);
            else
                VolumeDialog._2D_wrap3(VolumeDialog.this);
            return super.dispatchTouchEvent(motionevent);
        }

        protected void onStart()
        {
            super.onStart();
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            intentfilter.addAction("android.intent.action.SCREEN_OFF");
            intentfilter.addAction("android.media.STREAM_DEVICES_CHANGED_ACTION");
            intentfilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
            intentfilter.addAction("miui.intent.TAKE_SCREENSHOT");
            VolumeDialog._2D_get2(VolumeDialog.this).registerReceiverAsUser(VolumeDialog._2D_get1(VolumeDialog.this), UserHandle.ALL, intentfilter, null, null);
        }

        protected void onStop()
        {
            super.onStop();
            VolumeDialog._2D_get2(VolumeDialog.this).unregisterReceiver(VolumeDialog._2D_get1(VolumeDialog.this));
            VolumeDialog._2D_get8(VolumeDialog.this).sendEmptyMessage(4);
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            if(isShowing())
            {
                if(motionevent.getAction() == 4)
                {
                    VolumeDialog._2D_wrap1(VolumeDialog.this);
                    return true;
                }
                if(motionevent.getActionMasked() == 0)
                {
                    mDownX = motionevent.getX();
                    mDownY = motionevent.getY();
                } else
                if(motionevent.getActionMasked() == 2)
                {
                    float f = Math.abs(mDownX - motionevent.getX());
                    float f1 = mDownY - motionevent.getY();
                    if(f < f1 && f1 > (float)ViewConfiguration.get(VolumeDialog._2D_get2(VolumeDialog.this)).getScaledTouchSlop())
                    {
                        VolumeDialog._2D_wrap1(VolumeDialog.this);
                        return true;
                    }
                }
            }
            return false;
        }

        private float mDownX;
        private float mDownY;
        final VolumeDialog this$0;

        public CustomDialog(Context context)
        {
            this$0 = VolumeDialog.this;
            super(context);
        }
    }

    private final class H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 11: default 64
        //                       1 65
        //                       2 87
        //                       3 105
        //                       4 130
        //                       5 149
        //                       6 185
        //                       7 167
        //                       8 211
        //                       9 233
        //                       10 255
        //                       11 273;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            return;
_L2:
            Log.d("VolumeDialog", "SHOW");
            VolumeDialog._2D_wrap4(VolumeDialog.this, message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            Log.d("VolumeDialog", "DISMISS");
            VolumeDialog._2D_wrap1(VolumeDialog.this);
            continue; /* Loop/switch isn't completed */
_L4:
            Log.d("VolumeDialog", "RECHECK");
            VolumeDialog._2D_wrap2(VolumeDialog.this, (VolumeRow)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            Log.d("VolumeDialog", "RECHECK_ALL");
            VolumeDialog._2D_wrap2(VolumeDialog.this, null);
            continue; /* Loop/switch isn't completed */
_L6:
            Log.d("VolumeDialog", "RESCHEDULE_TIMEOUT");
            VolumeDialog._2D_wrap3(VolumeDialog.this);
            continue; /* Loop/switch isn't completed */
_L8:
            Log.d("VolumeDialog", "UPDATE_BOTTOM_MARGIN");
            VolumeDialog._2D_wrap8(VolumeDialog.this);
            continue; /* Loop/switch isn't completed */
_L7:
            Log.d("VolumeDialog", "STATE_CHANGED");
            VolumeDialog._2D_wrap6(VolumeDialog.this, message.arg1, message.arg2);
            continue; /* Loop/switch isn't completed */
_L9:
            Log.d("VolumeDialog", "UPDATE_LAYOUT_DIRECTION");
            VolumeDialog._2D_wrap10(VolumeDialog.this, message.arg1);
            continue; /* Loop/switch isn't completed */
_L10:
            Log.d("VolumeDialog", "SHOW_SAFE_WARNING");
            VolumeDialog._2D_wrap5(VolumeDialog.this, message.arg1);
            continue; /* Loop/switch isn't completed */
_L11:
            Log.d("VolumeDialog", "VIBRATE");
            VolumeDialog._2D_wrap12(VolumeDialog.this);
            continue; /* Loop/switch isn't completed */
_L12:
            Log.d("VolumeDialog", "RESET_SCREENSHOT");
            VolumeDialog._2D_set0(VolumeDialog.this, false);
            if(true) goto _L1; else goto _L13
_L13:
        }

        private static final int DISMISS = 2;
        private static final int RECHECK = 3;
        private static final int RECHECK_ALL = 4;
        private static final int RESCHEDULE_TIMEOUT = 5;
        private static final int RESET_SCREENSHOT = 11;
        private static final int SHOW = 1;
        private static final int SHOW_SAFE_WARNING = 9;
        private static final int STATE_CHANGED = 6;
        private static final int UPDATE_BOTTOM_MARGIN = 7;
        private static final int UPDATE_LAYOUT_DIRECTION = 8;
        private static final int VIBRATE = 10;
        final VolumeDialog this$0;

        public H()
        {
            this$0 = VolumeDialog.this;
            super(Looper.getMainLooper());
        }
    }

    private final class SafetyWarningDialog extends AlertDialog
    {

        public boolean onKeyDown(int i, KeyEvent keyevent)
        {
            if(24 == i || 25 == i)
                return true;
            else
                return super.onKeyDown(i, keyevent);
        }

        public boolean onKeyUp(int i, KeyEvent keyevent)
        {
            if(24 == i || 25 == i)
                return true;
            else
                return super.onKeyUp(i, keyevent);
        }

        final VolumeDialog this$0;

        public SafetyWarningDialog(Context context)
        {
            this$0 = VolumeDialog.this;
            super(context);
            getWindow().setType(2010);
            volumedialog = getWindow().getAttributes();
            privateFlags = privateFlags | 0x10;
            setMessage(context.getString(0x11080054));
            setButton(-1, context.getString(0x1040013), new _cls1());
            setButton(-2, context.getString(0x1040009), (android.content.DialogInterface.OnClickListener)null);
            setIconAttribute(0x1010355);
            setOnDismissListener(new _cls2());
        }
    }

    private static final class StreamState
    {

        static int _2D_get0(StreamState streamstate)
        {
            return streamstate.level;
        }

        static int _2D_get1(StreamState streamstate)
        {
            return streamstate.levelMax;
        }

        static int _2D_get2(StreamState streamstate)
        {
            return streamstate.levelMin;
        }

        static boolean _2D_get3(StreamState streamstate)
        {
            return streamstate.muteSupported;
        }

        static boolean _2D_get4(StreamState streamstate)
        {
            return streamstate.muted;
        }

        public static StreamState getStreamStateByStreamType(Context context, int i, VolumePanelDelegate volumepaneldelegate)
        {
            StreamState streamstate = new StreamState();
            context = (AudioManager)context.getSystemService("audio");
            streamstate.level = context.getLastAudibleStreamVolume(i);
            streamstate.levelMax = context.getStreamMaxVolume(i);
            streamstate.levelMin = volumepaneldelegate.getStreamMinVolume(i);
            streamstate.muted = context.isStreamMute(i);
            streamstate.muteSupported = volumepaneldelegate.isStreamAffectedByMute(i);
            if(android.os.Build.VERSION.SDK_INT < 23 && (i == 6 || i == 0))
            {
                streamstate.level = streamstate.level + 1;
                streamstate.levelMax = streamstate.levelMax + 1;
            }
            return streamstate;
        }

        public StreamState copy()
        {
            StreamState streamstate = new StreamState();
            streamstate.level = level;
            streamstate.levelMin = levelMin;
            streamstate.levelMax = levelMax;
            streamstate.muted = muted;
            streamstate.muteSupported = muteSupported;
            return streamstate;
        }

        private int level;
        private int levelMax;
        private int levelMin;
        private boolean muteSupported;
        private boolean muted;

        private StreamState()
        {
        }
    }

    private static class VolumeIconRes
    {

        int mutedIconRes;
        int normalIconRes;
        int selectedIconRes;

        private VolumeIconRes(int i, int j, int k)
        {
            normalIconRes = i;
            selectedIconRes = j;
            mutedIconRes = k;
        }

        VolumeIconRes(int i, int j, int k, VolumeIconRes volumeiconres)
        {
            this(i, j, k);
        }
    }

    public static interface VolumePanelDelegate
    {

        public abstract void disableSafeMediaVolume();

        public abstract int getMasterStreamType();

        public abstract int getRingerMode();

        public abstract int getStreamMinVolume(int i);

        public abstract boolean isStreamAffectedByMute(int i);

        public abstract void notifyVolumeControllerVisible(boolean flag);

        public abstract void setRingerMode(int i);

        public abstract boolean showSafeVolumeDialogByFlags(int i);
    }

    private static class VolumeRow
    {

        static ObjectAnimator _2D_get0(VolumeRow volumerow)
        {
            return volumerow.anim;
        }

        static int _2D_get1(VolumeRow volumerow)
        {
            return volumerow.animTargetProgress;
        }

        static StreamState _2D_get10(VolumeRow volumerow)
        {
            return volumerow.ss;
        }

        static int _2D_get11(VolumeRow volumerow)
        {
            return volumerow.stream;
        }

        static boolean _2D_get12(VolumeRow volumerow)
        {
            return volumerow.tracking;
        }

        static long _2D_get13(VolumeRow volumerow)
        {
            return volumerow.userAttempt;
        }

        static View _2D_get14(VolumeRow volumerow)
        {
            return volumerow.view;
        }

        static int _2D_get2(VolumeRow volumerow)
        {
            return volumerow.cachedIconRes;
        }

        static int _2D_get3(VolumeRow volumerow)
        {
            return volumerow.cachedProgressRes;
        }

        static ImageButton _2D_get4(VolumeRow volumerow)
        {
            return volumerow.icon;
        }

        static int _2D_get5(VolumeRow volumerow)
        {
            return volumerow.iconsMapKey;
        }

        static boolean _2D_get6(VolumeRow volumerow)
        {
            return volumerow.important;
        }

        static int _2D_get7(VolumeRow volumerow)
        {
            return volumerow.initIconsMapKey;
        }

        static miui.widget.SeekBar _2D_get8(VolumeRow volumerow)
        {
            return volumerow.slider;
        }

        static View _2D_get9(VolumeRow volumerow)
        {
            return volumerow.space;
        }

        static ObjectAnimator _2D_set0(VolumeRow volumerow, ObjectAnimator objectanimator)
        {
            volumerow.anim = objectanimator;
            return objectanimator;
        }

        static int _2D_set1(VolumeRow volumerow, int i)
        {
            volumerow.animTargetProgress = i;
            return i;
        }

        static View _2D_set10(VolumeRow volumerow, View view1)
        {
            volumerow.space = view1;
            return view1;
        }

        static StreamState _2D_set11(VolumeRow volumerow, StreamState streamstate)
        {
            volumerow.ss = streamstate;
            return streamstate;
        }

        static int _2D_set12(VolumeRow volumerow, int i)
        {
            volumerow.stream = i;
            return i;
        }

        static boolean _2D_set13(VolumeRow volumerow, boolean flag)
        {
            volumerow.tracking = flag;
            return flag;
        }

        static long _2D_set14(VolumeRow volumerow, long l)
        {
            volumerow.userAttempt = l;
            return l;
        }

        static View _2D_set15(VolumeRow volumerow, View view1)
        {
            volumerow.view = view1;
            return view1;
        }

        static int _2D_set2(VolumeRow volumerow, int i)
        {
            volumerow.cachedIconRes = i;
            return i;
        }

        static int _2D_set3(VolumeRow volumerow, int i)
        {
            volumerow.cachedProgressRes = i;
            return i;
        }

        static ImageButton _2D_set4(VolumeRow volumerow, ImageButton imagebutton)
        {
            volumerow.icon = imagebutton;
            return imagebutton;
        }

        static int _2D_set5(VolumeRow volumerow, int i)
        {
            volumerow.iconsMapKey = i;
            return i;
        }

        static boolean _2D_set6(VolumeRow volumerow, boolean flag)
        {
            volumerow.important = flag;
            return flag;
        }

        static int _2D_set7(VolumeRow volumerow, int i)
        {
            volumerow.initIconsMapKey = i;
            return i;
        }

        static int _2D_set8(VolumeRow volumerow, int i)
        {
            volumerow.lastLevel = i;
            return i;
        }

        static miui.widget.SeekBar _2D_set9(VolumeRow volumerow, miui.widget.SeekBar seekbar)
        {
            volumerow.slider = seekbar;
            return seekbar;
        }

        private ObjectAnimator anim;
        private int animTargetProgress;
        private int cachedIconRes;
        private int cachedProgressRes;
        private ImageButton icon;
        private int iconsMapKey;
        private boolean important;
        private int initIconsMapKey;
        private int lastLevel;
        private miui.widget.SeekBar slider;
        private View space;
        private StreamState ss;
        private int stream;
        private boolean tracking;
        private long userAttempt;
        private View view;

        private VolumeRow()
        {
            lastLevel = 1;
        }

        VolumeRow(VolumeRow volumerow)
        {
            this();
        }
    }

    private final class VolumeSeekBarChangeListener
        implements android.widget.SeekBar.OnSeekBarChangeListener
    {

        private VolumeRow getVolumeRow(SeekBar seekbar)
        {
            if(seekbar.getTag() != null && (seekbar.getTag() instanceof VolumeRow))
                return (VolumeRow)seekbar.getTag();
            else
                return null;
        }

        public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
        {
            VolumeRow volumerow = getVolumeRow(seekbar);
            if(volumerow == null)
                return;
            if(!flag)
                return;
            if(StreamState._2D_get2(VolumeRow._2D_get10(volumerow)) > 0)
            {
                int j = StreamState._2D_get2(VolumeRow._2D_get10(volumerow)) * 100;
                if(i < j)
                    seekbar.setProgress(j);
            }
            i = VolumeDialog._2D_wrap0(seekbar, i);
            VolumeRow._2D_set14(volumerow, SystemClock.uptimeMillis());
            Log.d("VolumeDialog", (new StringBuilder()).append("VolumeBar:onProgressChanged ").append(VolumeRow._2D_get11(volumerow)).append(" ").append(i).toString());
            VolumeDialog._2D_get0(VolumeDialog.this).setStreamVolume(VolumeRow._2D_get11(volumerow), i, 0x100000);
        }

        public void onStartTrackingTouch(SeekBar seekbar)
        {
            seekbar = getVolumeRow(seekbar);
            if(seekbar == null)
            {
                return;
            } else
            {
                VolumeRow._2D_set2(seekbar, ((VolumeIconRes)VolumeDialog._2D_get14().get(Integer.valueOf(VolumeRow._2D_get5(seekbar)))).selectedIconRes);
                VolumeRow._2D_get4(seekbar).setImageResource(VolumeRow._2D_get2(seekbar));
                VolumeRow._2D_set13(seekbar, true);
                return;
            }
        }

        public void onStopTrackingTouch(SeekBar seekbar)
        {
            VolumeRow volumerow = getVolumeRow(seekbar);
            if(volumerow == null)
            {
                return;
            } else
            {
                VolumeRow._2D_set13(volumerow, false);
                VolumeRow._2D_set14(volumerow, SystemClock.uptimeMillis());
                VolumeDialog._2D_wrap0(seekbar, seekbar.getProgress());
                VolumeDialog._2D_get8(VolumeDialog.this).sendMessageDelayed(VolumeDialog._2D_get8(VolumeDialog.this).obtainMessage(3, volumerow), 1000L);
                return;
            }
        }

        final VolumeDialog this$0;

        private VolumeSeekBarChangeListener()
        {
            this$0 = VolumeDialog.this;
            super();
        }

        VolumeSeekBarChangeListener(VolumeSeekBarChangeListener volumeseekbarchangelistener)
        {
            this();
        }
    }

    private static class VolumeSeekbarProgress
    {

        int normalProgress;
        int silentProgress;

        private VolumeSeekbarProgress(int i, int j)
        {
            normalProgress = i;
            silentProgress = j;
        }

        VolumeSeekbarProgress(int i, int j, VolumeSeekbarProgress volumeseekbarprogress)
        {
            this(i, j);
        }
    }


    static AudioManager _2D_get0(VolumeDialog volumedialog)
    {
        return volumedialog.mAm;
    }

    static BroadcastReceiver _2D_get1(VolumeDialog volumedialog)
    {
        return volumedialog.mBroadcastReceiver;
    }

    static int _2D_get10(VolumeDialog volumedialog)
    {
        return volumedialog.mRingerMode;
    }

    static RingerModeLayout _2D_get11(VolumeDialog volumedialog)
    {
        return volumedialog.mRingerModeLayout;
    }

    static List _2D_get12(VolumeDialog volumedialog)
    {
        return volumedialog.mRows;
    }

    static Object _2D_get13(VolumeDialog volumedialog)
    {
        return volumedialog.mSafetyWarningLock;
    }

    static Map _2D_get14()
    {
        return sVolumeIconTypeMap;
    }

    static Context _2D_get2(VolumeDialog volumedialog)
    {
        return volumedialog.mContext;
    }

    static VolumePanelDelegate _2D_get3(VolumeDialog volumedialog)
    {
        return volumedialog.mDelegate;
    }

    static ViewGroup _2D_get4(VolumeDialog volumedialog)
    {
        return volumedialog.mDialogContentView;
    }

    static long _2D_get5(VolumeDialog volumedialog)
    {
        return volumedialog.mDialogShowTime;
    }

    static ViewGroup _2D_get6(VolumeDialog volumedialog)
    {
        return volumedialog.mDialogView;
    }

    static ValueAnimator _2D_get7(VolumeDialog volumedialog)
    {
        return volumedialog.mExpandAnimator;
    }

    static H _2D_get8(VolumeDialog volumedialog)
    {
        return volumedialog.mHandler;
    }

    static boolean _2D_get9(VolumeDialog volumedialog)
    {
        return volumedialog.mLastStatus;
    }

    static boolean _2D_set0(VolumeDialog volumedialog, boolean flag)
    {
        volumedialog.mInScreenshot = flag;
        return flag;
    }

    static boolean _2D_set1(VolumeDialog volumedialog, boolean flag)
    {
        volumedialog.mLastStatus = flag;
        return flag;
    }

    static int _2D_set2(VolumeDialog volumedialog, int i)
    {
        volumedialog.mRingerMode = i;
        return i;
    }

    static AlertDialog _2D_set3(VolumeDialog volumedialog, AlertDialog alertdialog)
    {
        volumedialog.mSafetyWarning = alertdialog;
        return alertdialog;
    }

    static int _2D_wrap0(SeekBar seekbar, int i)
    {
        return getImpliedLevel(seekbar, i);
    }

    static void _2D_wrap1(VolumeDialog volumedialog)
    {
        volumedialog.dismissH();
    }

    static void _2D_wrap10(VolumeDialog volumedialog, int i)
    {
        volumedialog.updateLayoutDirectionH(i);
    }

    static void _2D_wrap11(VolumeDialog volumedialog)
    {
        volumedialog.updateRowsVisibilityByExpandH();
    }

    static void _2D_wrap12(VolumeDialog volumedialog)
    {
        volumedialog.vibrateH();
    }

    static void _2D_wrap2(VolumeDialog volumedialog, VolumeRow volumerow)
    {
        volumedialog.recheckH(volumerow);
    }

    static void _2D_wrap3(VolumeDialog volumedialog)
    {
        volumedialog.rescheduleTimeoutH();
    }

    static void _2D_wrap4(VolumeDialog volumedialog, int i)
    {
        volumedialog.showH(i);
    }

    static void _2D_wrap5(VolumeDialog volumedialog, int i)
    {
        volumedialog.showSafetyWarningH(i);
    }

    static void _2D_wrap6(VolumeDialog volumedialog, int i, int j)
    {
        volumedialog.stateChangedH(i, j);
    }

    static void _2D_wrap7(VolumeDialog volumedialog, int i)
    {
        volumedialog.streamDeviceChanged(i);
    }

    static void _2D_wrap8(VolumeDialog volumedialog)
    {
        volumedialog.updateDialogBottomMarginH();
    }

    static void _2D_wrap9(VolumeDialog volumedialog)
    {
        volumedialog.updateExpandButtonH();
    }

    public VolumeDialog(Context context, VolumePanelDelegate volumepaneldelegate)
    {
        mRingerMode = -1;
        mStatusBarHeight = -1;
        mBootBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                if("android.media.RINGER_MODE_CHANGED".equals(intent.getAction()))
                {
                    int i = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", -1);
                    if(VolumeDialog._2D_get10(VolumeDialog.this) != i)
                    {
                        if(VolumeDialog._2D_get10(VolumeDialog.this) != -1 && i == 1)
                            VolumeDialog._2D_get8(VolumeDialog.this).sendMessageDelayed(VolumeDialog._2D_get8(VolumeDialog.this).obtainMessage(10), 300L);
                        VolumeDialog._2D_set2(VolumeDialog.this, i);
                    }
                }
            }

            final VolumeDialog this$0;

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }
;
        mDialogShowTime = -1L;
        mInScreenshot = false;
        mBroadcastReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                context1 = intent.getAction();
                if(!"android.intent.action.SCREEN_OFF".equals(context1) && !"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(context1)) goto _L2; else goto _L1
_L1:
                dismiss();
_L4:
                return;
_L2:
                if("miui.intent.TAKE_SCREENSHOT".equals(context1))
                {
                    if(!intent.getBooleanExtra("IsFinished", true))
                    {
                        VolumeDialog._2D_set0(VolumeDialog.this, true);
                        VolumeDialog._2D_get8(VolumeDialog.this).sendEmptyMessageDelayed(11, 500L);
                        if(SystemClock.uptimeMillis() - VolumeDialog._2D_get5(VolumeDialog.this) < 500L)
                            dismiss();
                    } else
                    {
                        VolumeDialog._2D_get8(VolumeDialog.this).removeMessages(11);
                        VolumeDialog._2D_set0(VolumeDialog.this, false);
                    }
                } else
                if("android.media.STREAM_DEVICES_CHANGED_ACTION".equals(context1))
                {
                    int i = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                    VolumeDialog._2D_wrap7(VolumeDialog.this, i);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final VolumeDialog this$0;

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }
;
        mContext = context;
        mDelegate = volumepaneldelegate;
        mVibrator = (Vibrator)mContext.getSystemService("vibrator");
        mAm = (AudioManager)mContext.getSystemService("audio");
        context = new IntentFilter();
        context.addAction("android.media.RINGER_MODE_CHANGED");
        mContext.registerReceiverAsUser(mBootBroadcastReceiver, UserHandle.ALL, context, null, null);
    }

    private void addRow(int i, int j, boolean flag)
    {
        VolumeRow volumerow = initRow(i, j, flag);
        if(!mRows.isEmpty())
        {
            View view = new View(mContext);
            view.setId(0x1020000);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, mContext.getResources().getDimensionPixelSize(0x110b0039));
            mDialogContentView.addView(view, layoutparams);
            mVolumeRowSpaces.add(view);
            VolumeRow._2D_set10(volumerow, view);
        }
        mDialogContentView.addView(VolumeRow._2D_get14(volumerow));
        mVolumeRowViews.add(VolumeRow._2D_get14(volumerow));
        mRows.add(volumerow);
    }

    private void adjustDialogPosition()
    {
        if(CustomizeUtil.HAS_NOTCH)
        {
            Window window = mDialog.getWindow();
            android.view.WindowManager.LayoutParams layoutparams = window.getAttributes();
            int i;
            if(mContext.getResources().getConfiguration().orientation == 1)
                i = getStatusBarHeight();
            else
                i = 0;
            layoutparams.y = i;
            window.setAttributes(layoutparams);
        }
    }

    private int computeTimeoutH()
    {
        return !mExpanded && !mExpandAnimating && (mRingerModeLayout == null || !mRingerModeLayout.mSilenceModeExpanded) ? 3000 : 6000;
    }

    private void dismissH()
    {
        mHandler.removeMessages(2);
        mHandler.removeMessages(1);
        if(!mShowing)
            return;
        mShowing = false;
        Object obj = mSafetyWarningLock;
        obj;
        JVM INSTR monitorenter ;
        if(mSafetyWarning != null)
            mSafetyWarning.dismiss();
        obj;
        JVM INSTR monitorexit ;
        mDialog.dismiss();
        mExpandAnimator.cancel();
        mDelegate.notifyVolumeControllerVisible(false);
        mRingerModeLayout.cleanUp();
        mRingerModeLayout = null;
        mExpandAnimator = null;
        mDialog = null;
        mDialogContentView = null;
        mDialogView = null;
        mExpandButton = null;
        mRows.clear();
        mVolumeRowViews.clear();
        mVolumeRowSpaces.clear();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void expandVolumeBar(final boolean isExpanded)
    {
        mExpandAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
                VolumeDialog._2D_get4(VolumeDialog.this).getLayoutParams().height = i;
                VolumeDialog._2D_get4(VolumeDialog.this).requestLayout();
            }

            final VolumeDialog this$0;

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }
);
        mExpandAnimator.addListener(new android.animation.Animator.AnimatorListener() {

            public void onAnimationCancel(Animator animator)
            {
            }

            public void onAnimationEnd(Animator animator)
            {
                if(!isExpanded)
                    VolumeDialog._2D_wrap11(VolumeDialog.this);
                VolumeDialog._2D_get4(VolumeDialog.this).getLayoutParams().height = -2;
                VolumeDialog._2D_get4(VolumeDialog.this).requestLayout();
                animator = (ViewGroup)VolumeDialog._2D_get6(VolumeDialog.this).getParent();
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)animator.getLayoutParams();
                marginlayoutparams.height = -2;
                animator.setLayoutParams(marginlayoutparams);
            }

            public void onAnimationRepeat(Animator animator)
            {
            }

            public void onAnimationStart(Animator animator)
            {
                if(isExpanded)
                    VolumeDialog._2D_wrap11(VolumeDialog.this);
                animator = (ViewGroup)VolumeDialog._2D_get6(VolumeDialog.this).getParent();
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)animator.getLayoutParams();
                marginlayoutparams.height = 1000;
                animator.setLayoutParams(marginlayoutparams);
                VolumeDialog._2D_get4(VolumeDialog.this).getLayoutParams().height = -2;
                VolumeDialog._2D_get4(VolumeDialog.this).measure(0, 0);
                VolumeRow._2D_get14((VolumeRow)VolumeDialog._2D_get12(VolumeDialog.this).get(0)).measure(0, 0);
                int i;
                if(isExpanded)
                    i = VolumeDialog._2D_get4(VolumeDialog.this).getMeasuredHeight();
                else
                    i = VolumeRow._2D_get14((VolumeRow)VolumeDialog._2D_get12(VolumeDialog.this).get(0)).getMeasuredHeight();
                VolumeDialog._2D_get7(VolumeDialog.this).setIntValues(new int[] {
                    VolumeDialog._2D_get4(VolumeDialog.this).getHeight(), i
                });
            }

            final VolumeDialog this$0;
            final boolean val$isExpanded;

            
            {
                this$0 = VolumeDialog.this;
                isExpanded = flag;
                super();
            }
        }
);
        mExpandAnimator.setInterpolator(new DecelerateInterpolator());
        mExpandAnimator.setDuration(300L);
        mExpandAnimator.start();
    }

    private VolumeRow findRow(int i)
    {
        for(Iterator iterator = mRows.iterator(); iterator.hasNext();)
        {
            VolumeRow volumerow = (VolumeRow)iterator.next();
            if(VolumeRow._2D_get11(volumerow) == i)
                return volumerow;
        }

        return null;
    }

    private int getConservativeCollapseDuration()
    {
        return 600;
    }

    private static int getImpliedLevel(SeekBar seekbar, int i)
    {
        int j = seekbar.getMax();
        int k = j / 100;
        if(i == 0)
            i = 0;
        else
        if(i == j)
            i = j / 100;
        else
            i = (int)(((float)i / (float)j) * (float)(k - 1)) + 1;
        return i;
    }

    private int getMappedStream(int i)
    {
        if(i >= STREAM_VOLUME_ALIAS_DEFAULT.length)
            return 3;
        else
            return STREAM_VOLUME_ALIAS_DEFAULT[i];
    }

    private int getStatusBarHeight()
    {
        if(mStatusBarHeight < 0)
            mStatusBarHeight = mContext.getResources().getDimensionPixelSize(0x1050177);
        return mStatusBarHeight;
    }

    private VolumeRow initRow(int i, int j, boolean flag)
    {
        final VolumeRow row = new VolumeRow(null);
        VolumeRow._2D_set12(row, i);
        VolumeRow._2D_set5(row, j);
        VolumeRow._2D_set7(row, j);
        VolumeRow._2D_set6(row, flag);
        VolumeRow._2D_set15(row, mDialog.getLayoutInflater().inflate(0x11030020, null));
        VolumeRow._2D_get14(row).setTag(row);
        VolumeRow._2D_set9(row, (miui.widget.SeekBar)VolumeRow._2D_get14(row).findViewById(0x110c0062));
        VolumeRow._2D_get8(row).setTag(row);
        VolumeRow._2D_get8(row).setOnSeekBarChangeListener(new VolumeSeekBarChangeListener(null));
        VolumeRow._2D_get14(row).setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                VolumeRow._2D_get8(row).getHitRect(sliderHitRect);
                if(!dragging && motionevent.getActionMasked() == 0 && motionevent.getY() < (float)sliderHitRect.top)
                    dragging = true;
                if(dragging)
                {
                    motionevent.offsetLocation(-sliderHitRect.left, -sliderHitRect.top);
                    VolumeRow._2D_get8(row).dispatchTouchEvent(motionevent);
                    if(motionevent.getActionMasked() == 1 || motionevent.getActionMasked() == 3)
                        dragging = false;
                    return true;
                } else
                {
                    return false;
                }
            }

            private boolean dragging;
            private final Rect sliderHitRect = new Rect();
            final VolumeDialog this$0;
            final VolumeRow val$row;

            
            {
                this$0 = VolumeDialog.this;
                row = volumerow;
                super();
            }
        }
);
        VolumeRow._2D_set4(row, (ImageButton)VolumeRow._2D_get14(row).findViewById(0x110c0061));
        VolumeRow._2D_get4(row).setImageResource(((VolumeIconRes)sVolumeIconTypeMap.get(Integer.valueOf(j))).normalIconRes);
        return row;
    }

    private boolean isAttached()
    {
        boolean flag;
        if(mDialogContentView != null)
            flag = mDialogContentView.isAttachedToWindow();
        else
            flag = false;
        return flag;
    }

    private void orderVolumeRowsH()
    {
        int i = 0;
        int j = 0;
        while(j < mVolumeRowViews.size()) 
        {
            View view = (View)mVolumeRowViews.get(j);
            VolumeRow volumerow = null;
            boolean flag = true;
            if(j == 0)
            {
                VolumeRow volumerow1 = findRow(mActiveStream);
                volumerow = volumerow1;
                if(volumerow1 == null)
                {
                    Log.d("VolumeDialog", "terrible thing happens in orderVolumeRowsH");
                    volumerow = volumerow1;
                }
            }
            Object obj1 = volumerow;
            boolean flag1 = flag;
            int k = i;
            if(volumerow == null)
            {
                Object obj = mRows;
                k = i + 1;
                obj = (VolumeRow)((List) (obj)).get(i);
                if(VolumeRow._2D_get11(((VolumeRow) (obj))) == mActiveStream)
                {
                    obj = mRows;
                    i = k + 1;
                    obj = (VolumeRow)((List) (obj)).get(k);
                } else
                {
                    i = k;
                }
                obj1 = obj;
                flag1 = flag;
                k = i;
                if(!VolumeRow._2D_get6(((VolumeRow) (obj))))
                {
                    flag1 = false;
                    k = i;
                    obj1 = obj;
                }
            }
            if(flag1)
                view.setVisibility(0);
            else
                view.setVisibility(8);
            VolumeRow._2D_set15(((VolumeRow) (obj1)), view);
            VolumeRow._2D_set2(((VolumeRow) (obj1)), 0);
            VolumeRow._2D_set4(((VolumeRow) (obj1)), (ImageButton)view.findViewById(0x110c0061));
            VolumeRow._2D_get4(((VolumeRow) (obj1))).setImageResource(((VolumeIconRes)sVolumeIconTypeMap.get(Integer.valueOf(VolumeRow._2D_get7(((VolumeRow) (obj1)))))).normalIconRes);
            VolumeRow._2D_set9(((VolumeRow) (obj1)), (miui.widget.SeekBar)view.findViewById(0x110c0062));
            VolumeRow._2D_get14(((VolumeRow) (obj1))).setTag(obj1);
            VolumeRow._2D_get8(((VolumeRow) (obj1))).setTag(obj1);
            if(j > 0)
            {
                VolumeRow._2D_set10(((VolumeRow) (obj1)), (View)mVolumeRowSpaces.get(j - 1));
                obj = (View)mVolumeRowSpaces.get(j - 1);
                if(flag1)
                    i = 0;
                else
                    i = 8;
                ((View) (obj)).setVisibility(i);
            } else
            {
                VolumeRow._2D_set10(((VolumeRow) (obj1)), null);
            }
            j++;
            i = k;
        }
    }

    private void prepareForCollapse()
    {
        mHandler.removeMessages(7);
        mCollapseTime = System.currentTimeMillis();
        updateDialogBottomMarginH();
        mHandler.sendEmptyMessageDelayed(7, getConservativeCollapseDuration());
    }

    private void recheckH(VolumeRow volumerow)
    {
        if(volumerow == null)
            for(volumerow = mRows.iterator(); volumerow.hasNext(); updateVolumeRowH((VolumeRow)volumerow.next()));
        else
            updateVolumeRowH(volumerow);
    }

    private void rescheduleTimeoutH()
    {
        mHandler.removeMessages(2);
        int i = computeTimeoutH();
        mHandler.sendMessageDelayed(mHandler.obtainMessage(2), i);
    }

    private void showH(int i)
    {
        if(mRows.size() == 0)
        {
            mDialog = new CustomDialog(mContext);
            Window window = mDialog.getWindow();
            window.requestFeature(1);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.clearFlags(2);
            window.addFlags(0x1040128);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.setContentView(0x1103001f);
            android.view.WindowManager.LayoutParams layoutparams1 = window.getAttributes();
            layoutparams1.height = -2;
            layoutparams1.width = -1;
            layoutparams1.type = 2020;
            layoutparams1.format = -3;
            layoutparams1.setTitle("Volume Control");
            layoutparams1.gravity = 48;
            layoutparams1.windowAnimations = 0x110d0003;
            window.setAttributes(layoutparams1);
            window.setSoftInputMode(48);
            mDialogView = (ViewGroup)mDialog.findViewById(0x110c005d);
            mDialogContentView = (ViewGroup)mDialog.findViewById(0x110c005f);
            mExpandButton = (ImageButton)mDialogView.findViewById(0x110c005e);
            mExpandButton.setOnClickListener(mClickExpand);
            mRingerModeLayout = (RingerModeLayout)mDialog.findViewById(0x110c0036);
            mExpandAnimator = ValueAnimator.ofInt(new int[] {
                0, 0
            });
            mLayoutTransition = new LayoutTransition();
            mLayoutTransition.setDuration(200L);
            addRow(2, 5, true);
            addRow(3, 3, true);
            addRow(4, 0, true);
            addRow(0, 4, false);
            addRow(6, 4, false);
            if(AudioSystem.getNumStreamTypes() > 10)
                addRow(10, 8, false);
        }
        mDialogView.setBackgroundResource(0);
        mDialogView.setBackgroundResource(0x110200f0);
        android.view.ViewGroup.LayoutParams layoutparams = mDialogView.getLayoutParams();
        layoutparams.width = mContext.getResources().getDimensionPixelSize(0x110b003f);
        if(layoutparams.width == 0)
            layoutparams.width = -1;
        mDialogView.setLayoutParams(layoutparams);
        mLastStatus = false;
        if(!mShowing)
        {
            if(mAm.getMode() == 2 || android.provider.MiuiSettings.SilenceMode.isSupported ^ true)
                mRingerModeLayout.setVisibility(8);
            mRingerModeLayout.setVolumeDialog(this);
            mRingerModeLayout.setLooper(Looper.getMainLooper());
            mRingerModeLayout.setParentDialog(mDialogView);
            mRingerModeLayout.init();
        }
        adjustDialogPosition();
        Log.d("VolumeDialog", (new StringBuilder()).append("showH ").append(i).append(" ").append(mActiveStream).append(" ").append(mShowing).toString());
        i = getMappedStream(i);
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        if(!mShowing || mActiveStream != i)
        {
            mActiveStream = i;
            orderVolumeRowsH();
        }
        rescheduleTimeoutH();
        updateVolumeRowsH();
        if(mInScreenshot || mShowing)
        {
            if(mShowing)
            {
                updateExpandButtonH();
                updateRowsVisibilityByExpandH();
            }
            return;
        } else
        {
            mExpanded = false;
            mExpandAnimating = false;
            updateExpandButtonH();
            updateRowsVisibilityByExpandH();
            mShowing = true;
            mDialogShowTime = SystemClock.uptimeMillis();
            mDialog.show();
            mDelegate.notifyVolumeControllerVisible(true);
            return;
        }
    }

    private void showSafetyWarningH(int i)
    {
        if(!mDelegate.showSafeVolumeDialogByFlags(i) && !mShowing) goto _L2; else goto _L1
_L1:
        Object obj = mSafetyWarningLock;
        obj;
        JVM INSTR monitorenter ;
        AlertDialog alertdialog = mSafetyWarning;
        if(alertdialog == null)
            break MISSING_BLOCK_LABEL_39;
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1 = JVM INSTR new #28  <Class VolumeDialog$SafetyWarningDialog>;
        ((SafetyWarningDialog) (obj1)).this. SafetyWarningDialog(mContext);
        mSafetyWarning = ((AlertDialog) (obj1));
        mSafetyWarning.show();
        AlertDialog alertdialog1 = mSafetyWarning;
        obj1 = JVM INSTR new #20  <Class VolumeDialog$8>;
        ((_cls8) (obj1)).this. _cls8();
        alertdialog1.setOnDismissListener(((android.content.DialogInterface.OnDismissListener) (obj1)));
        obj;
        JVM INSTR monitorexit ;
_L2:
        rescheduleTimeoutH();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void stateChangedH(int i, int j)
    {
        VolumeRow volumerow = findRow(getMappedStream(i));
        if(volumerow != null)
            updateVolumeRowH(volumerow);
        else
            Log.e("VolumeDialog", (new StringBuilder()).append("stateChangedH can not find volume row for stream ").append(i).toString());
    }

    private void streamDeviceChanged(int i)
    {
        mHandler.obtainMessage(3, findRow(i)).sendToTarget();
    }

    private void updateDialogBottomMarginH()
    {
        if(mDialogView == null)
            return;
        long l = System.currentTimeMillis();
        long l1 = mCollapseTime;
        int i;
        ViewGroup viewgroup;
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams;
        if(mCollapseTime != 0L && l - l1 < (long)getConservativeCollapseDuration())
            i = 1;
        else
            i = 0;
        viewgroup = (ViewGroup)mDialogView.getParent();
        marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)viewgroup.getLayoutParams();
        if(i != 0)
            i = viewgroup.getHeight();
        else
            i = -2;
        marginlayoutparams.height = i;
        viewgroup.setLayoutParams(marginlayoutparams);
    }

    private void updateExpandButtonH()
    {
        int i = 0;
        if(mExpandButton == null)
            return;
        ImageButton imagebutton = mExpandButton;
        if(mActiveStream == 0)
            i = 4;
        imagebutton.setVisibility(i);
        imagebutton = mExpandButton;
        if(mExpanded)
            i = 0x110200ef;
        else
            i = 0x110200f1;
        imagebutton.setImageResource(i);
        mExpandButton.setClickable(mExpandAnimating ^ true);
    }

    private void updateLayoutDirectionH(int i)
    {
        if(mDialogView != null)
            mDialogView.setLayoutDirection(i);
    }

    private void updateRowsVisibilityByExpandH()
    {
        Iterator iterator = mRows.iterator();
_L7:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        VolumeRow volumerow;
        byte byte0;
        volumerow = (VolumeRow)iterator.next();
        byte0 = 8;
        if(VolumeRow._2D_get11(volumerow) != mActiveStream) goto _L4; else goto _L3
_L3:
        byte0 = 0;
_L5:
        VolumeRow._2D_get14(volumerow).setVisibility(byte0);
        Log.d("VolumeDialog", (new StringBuilder()).append("updateRowsVisibilityByExpandH ").append(VolumeRow._2D_get11(volumerow)).append(" ").append(byte0).append(" ").append(Integer.toHexString(System.identityHashCode(VolumeRow._2D_get14(volumerow)))).toString());
        if(VolumeRow._2D_get9(volumerow) != null)
            VolumeRow._2D_get9(volumerow).setVisibility(byte0);
        continue; /* Loop/switch isn't completed */
_L4:
        if(VolumeRow._2D_get6(volumerow))
            if(mExpanded)
                byte0 = 0;
            else
                byte0 = 8;
        if(true) goto _L5; else goto _L2
_L2:
        return;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void updateVolumeRowH(VolumeRow volumerow)
    {
        Object obj;
        int i;
label0:
        {
            obj = StreamState.getStreamStateByStreamType(mContext, VolumeRow._2D_get11(volumerow), mDelegate);
            if(obj == null)
                return;
            VolumeRow._2D_set11(volumerow, ((StreamState) (obj)));
            if(StreamState._2D_get0(((StreamState) (obj))) > 0)
                VolumeRow._2D_set8(volumerow, StreamState._2D_get0(((StreamState) (obj))));
            int j;
            int k;
            boolean flag;
            boolean flag1;
            if(VolumeRow._2D_get11(volumerow) == 2)
                i = 1;
            else
                i = 0;
            if(i != 0)
                if(mDelegate.getRingerMode() != 1);
            if(i != 0)
                if(mDelegate.getRingerMode() != 0);
            i = StreamState._2D_get1(((StreamState) (obj))) * 100;
            if(i != VolumeRow._2D_get8(volumerow).getMax())
                VolumeRow._2D_get8(volumerow).setMax(i);
            j = VolumeRow._2D_get7(volumerow);
            k = j;
            if(VolumeRow._2D_get11(volumerow) == mActiveStream)
            {
                k = mAm.getDevicesForStream(VolumeRow._2D_get11(volumerow));
                i = j;
                if(VolumeRow._2D_get11(volumerow) == 0)
                {
                    i = j;
                    if(mAm.isSpeakerphoneOn())
                        i = 6;
                }
                if((k & 4) != 0 || (k & 8) != 0)
                    i = 2;
                k = i;
                if(VolumeRow._2D_get11(volumerow) == 3)
                {
                    k = i;
                    if(AudioManagerHelper.isHiFiMode(mContext))
                        k = 7;
                }
            }
            VolumeRow._2D_set5(volumerow, k);
            if(((AudioManager)mContext.getSystemService("audio")).getStreamVolume(VolumeRow._2D_get11(volumerow)) == 0)
                flag = true;
            else
                flag = false;
            flag1 = flag;
            if(android.os.Build.VERSION.SDK_INT >= 23)
                break label0;
            if(VolumeRow._2D_get11(volumerow) != 6)
            {
                flag1 = flag;
                if(VolumeRow._2D_get11(volumerow) != 0)
                    break label0;
            }
            if(StreamState._2D_get4(((StreamState) (obj))))
                flag1 = StreamState._2D_get3(((StreamState) (obj)));
            else
                flag1 = false;
        }
        obj = (VolumeIconRes)sVolumeIconTypeMap.get(Integer.valueOf(k));
        if(flag1)
            i = ((VolumeIconRes) (obj)).mutedIconRes;
        else
            i = ((VolumeIconRes) (obj)).normalIconRes;
        if(i != VolumeRow._2D_get2(volumerow))
        {
            VolumeRow._2D_set2(volumerow, i);
            VolumeRow._2D_get4(volumerow).setImageResource(i);
        }
        if(flag1)
            i = sVolumeSeekbarProgress.silentProgress;
        else
            i = sVolumeSeekbarProgress.normalProgress;
        if(i != VolumeRow._2D_get3(volumerow))
        {
            VolumeRow._2D_set3(volumerow, i);
            VolumeRow._2D_get8(volumerow).setProgressDrawable(VolumeRow._2D_get8(volumerow).getResources().getDrawable(i));
        }
        if(flag1)
            i = mAm.getLastAudibleStreamVolume(VolumeRow._2D_get11(volumerow));
        else
            i = StreamState._2D_get0(VolumeRow._2D_get10(volumerow));
        updateVolumeRowSliderH(volumerow, i);
    }

    private void updateVolumeRowSliderH(VolumeRow volumerow, int i)
    {
        if(VolumeRow._2D_get12(volumerow))
            return;
        Log.d("VolumeDialog", (new StringBuilder()).append("updateVolumeRowSliderH start ").append(VolumeRow._2D_get11(volumerow)).append(" ").append(i).toString());
        int j = VolumeRow._2D_get8(volumerow).getProgress();
        int k = getImpliedLevel(VolumeRow._2D_get8(volumerow), j);
        boolean flag;
        int i1;
        if(VolumeRow._2D_get14(volumerow).getVisibility() == 0)
            flag = true;
        else
            flag = false;
        if(SystemClock.uptimeMillis() - VolumeRow._2D_get13(volumerow) < 1000L)
            i1 = 1;
        else
            i1 = 0;
        mHandler.removeMessages(3, volumerow);
        if(mShowing && flag && i1)
        {
            mHandler.sendMessageAtTime(mHandler.obtainMessage(3, volumerow), VolumeRow._2D_get13(volumerow) + 1000L);
            return;
        }
        if(i == k && mShowing && flag)
            return;
        i1 = i * 100;
        if(j != i1)
            if(mShowing && flag)
            {
                if(VolumeRow._2D_get0(volumerow) != null && VolumeRow._2D_get0(volumerow).isRunning() && VolumeRow._2D_get1(volumerow) == i1)
                    return;
                i = j;
                int l = i;
                if(VolumeRow._2D_get0(volumerow) != null)
                {
                    l = i;
                    if(VolumeRow._2D_get0(volumerow).isRunning())
                    {
                        Log.d("VolumeDialog", (new StringBuilder()).append("updateVolumeRowSliderH cancel animation: ").append(VolumeRow._2D_get11(volumerow)).toString());
                        VolumeRow._2D_get0(volumerow).cancel();
                        l = VolumeRow._2D_get1(volumerow);
                    }
                }
                Log.d("VolumeDialog", (new StringBuilder()).append("updateVolumeRowSliderH animation: ").append(l).append(" ").append(i1).toString());
                VolumeRow._2D_set0(volumerow, ObjectAnimator.ofInt(VolumeRow._2D_get8(volumerow), "progress", new int[] {
                    l, i1
                }));
                VolumeRow._2D_get0(volumerow).setInterpolator(null);
                VolumeRow._2D_set1(volumerow, i1);
                VolumeRow._2D_get0(volumerow).setDuration(80L);
                VolumeRow._2D_get0(volumerow).start();
            } else
            {
                if(VolumeRow._2D_get0(volumerow) != null)
                    VolumeRow._2D_get0(volumerow).cancel();
                VolumeRow._2D_get8(volumerow).setProgress(i1);
            }
    }

    private void updateVolumeRowsH()
    {
        for(Iterator iterator = mRows.iterator(); iterator.hasNext(); updateVolumeRowH((VolumeRow)iterator.next()));
    }

    private void vibrateH()
    {
        ((Vibrator)mContext.getSystemService("vibrator")).vibrate(300L);
    }

    public void dismiss()
    {
        mHandler.obtainMessage(2).sendToTarget();
    }

    public void dismiss(long l)
    {
        mHandler.sendEmptyMessageDelayed(2, l);
    }

    public boolean isShowing()
    {
        return mShowing;
    }

    public void masterMuteChanged(int i)
    {
    }

    public void masterVolumeChanged(int i)
    {
    }

    public void recheckAll()
    {
        mHandler.sendEmptyMessage(4);
    }

    public void rescheduleTimeout(boolean flag)
    {
        mHandler.removeMessages(2);
        mHandler.removeMessages(5);
        if(flag)
            mHandler.sendEmptyMessage(5);
    }

    public void setExpandedH(boolean flag)
    {
        if(mExpanded == flag)
            return;
        mExpanded = flag;
        mExpandAnimating = isAttached();
        if(mExpandAnimating)
        {
            updateExpandButtonH();
            expandVolumeBar(flag);
        }
        mHandler.postDelayed(new Runnable() {

            public void run()
            {
                mExpandAnimating = false;
                VolumeDialog._2D_wrap9(VolumeDialog.this);
            }

            final VolumeDialog this$0;

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
        }
, getConservativeCollapseDuration());
        rescheduleTimeoutH();
    }

    public void show(int i, int j)
    {
        Log.d("VolumeDialog", (new StringBuilder()).append("show ").append(i).append(" ").append(j).toString());
        mHandler.obtainMessage(1, i, j).sendToTarget();
    }

    public void showSafeWarningDialog(int i)
    {
        mHandler.obtainMessage(9, i, 0).sendToTarget();
    }

    public void stateChanged(int i)
    {
        stateChanged(i, mAm.getStreamVolume(i));
    }

    public void stateChanged(int i, int j)
    {
        Log.d("VolumeDialog", (new StringBuilder()).append("stateChanged ").append(i).append(" ").append(j).toString());
        mHandler.obtainMessage(6, i, j).sendToTarget();
    }

    public void updateLayoutDirection(int i)
    {
        mHandler.obtainMessage(8, i, 0).sendToTarget();
    }

    private static final int LAYOUT_TRANSITION_ANIMATION_DURATION = 200;
    private static final String STREAM_DEVICES_CHANGED_ACTION = "android.media.STREAM_DEVICES_CHANGED_ACTION";
    private static final String STREAM_MUTE_CHANGED_ACTION = "android.media.STREAM_MUTE_CHANGED_ACTION";
    private static final String TAG = "VolumeDialog";
    private static final int TYPE_FM = 10;
    private static final int UPDATE_ANIMATION_DURATION = 80;
    private static final long USER_ATTEMPT_GRACE_PERIOD = 1000L;
    private static final int VIBRATE_DELAY = 300;
    private static final int VOLUME_ICON_TYPE_ALARM = 0;
    private static final int VOLUME_ICON_TYPE_BLUETOOTH = 1;
    private static final int VOLUME_ICON_TYPE_FM = 8;
    private static final int VOLUME_ICON_TYPE_HEADSET = 2;
    private static final int VOLUME_ICON_TYPE_HIFI = 7;
    private static final int VOLUME_ICON_TYPE_MEDIA = 3;
    private static final int VOLUME_ICON_TYPE_PHONE = 4;
    private static final int VOLUME_ICON_TYPE_RING = 5;
    private static final int VOLUME_ICON_TYPE_SPEAKER = 6;
    private static final Map sVolumeIconTypeMap;
    static VolumeSeekbarProgress sVolumeSeekbarProgress = new VolumeSeekbarProgress(0x110200f2, 0x110200f4, null);
    private final int STREAM_VOLUME_ALIAS_DEFAULT[] = {
        0, 2, 2, 3, 4, 2, 6, 2, 2, 3, 
        10
    };
    private int mActiveStream;
    private final AudioManager mAm;
    private BroadcastReceiver mBootBroadcastReceiver;
    private BroadcastReceiver mBroadcastReceiver;
    private final android.view.View.OnClickListener mClickExpand = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            if(mExpandAnimating)
                return;
            setExpandedH(mExpanded ^ true);
            if(mExpanded)
                VolumeDialog._2D_set1(VolumeDialog.this, VolumeDialog._2D_get11(VolumeDialog.this).mSilenceModeExpanded);
            view = VolumeDialog._2D_get11(VolumeDialog.this);
            boolean flag;
            if(!mExpanded)
                flag = VolumeDialog._2D_get9(VolumeDialog.this);
            else
                flag = false;
            view.expandSilenceModeContent(flag);
        }

        final VolumeDialog this$0;

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
    }
;
    private long mCollapseTime;
    private final Context mContext;
    private VolumePanelDelegate mDelegate;
    private CustomDialog mDialog;
    private ViewGroup mDialogContentView;
    private long mDialogShowTime;
    private ViewGroup mDialogView;
    public boolean mExpandAnimating;
    private ValueAnimator mExpandAnimator;
    private ImageButton mExpandButton;
    public boolean mExpanded;
    private final H mHandler = new H();
    private boolean mInScreenshot;
    private boolean mLastStatus;
    private LayoutTransition mLayoutTransition;
    private int mRingerMode;
    private RingerModeLayout mRingerModeLayout;
    private final List mRows = new ArrayList();
    private AlertDialog mSafetyWarning;
    private final Object mSafetyWarningLock = new Object();
    private boolean mShowing;
    private int mStatusBarHeight;
    private final Vibrator mVibrator;
    private final List mVolumeRowSpaces = new ArrayList();
    private final List mVolumeRowViews = new ArrayList();

    static 
    {
        sVolumeIconTypeMap = new HashMap();
        sVolumeIconTypeMap.put(Integer.valueOf(0), new VolumeIconRes(0x11020036, 0x11020037, 0x11020035, null));
        sVolumeIconTypeMap.put(Integer.valueOf(1), new VolumeIconRes(0x1102003b, 0x1102003c, 0x1102003a, null));
        sVolumeIconTypeMap.put(Integer.valueOf(2), new VolumeIconRes(0x11020043, 0x11020044, 0x11020042, null));
        sVolumeIconTypeMap.put(Integer.valueOf(3), new VolumeIconRes(0x1102004b, 0x1102004c, 0x1102004a, null));
        sVolumeIconTypeMap.put(Integer.valueOf(4), new VolumeIconRes(0x11020051, 0x11020052, 0x11020050, null));
        sVolumeIconTypeMap.put(Integer.valueOf(5), new VolumeIconRes(0x11020055, 0x11020059, 0x11020054, null));
        sVolumeIconTypeMap.put(Integer.valueOf(6), new VolumeIconRes(0x1102005c, 0x1102005d, 0x1102005b, null));
        Map map = sVolumeIconTypeMap;
        VolumeIconRes volumeiconres;
        if("scorpio".equals(Build.DEVICE) || "lithium".equals(Build.DEVICE))
            volumeiconres = new VolumeIconRes(0x1102004b, 0x1102004c, 0x1102004a, null);
        else
            volumeiconres = new VolumeIconRes(0x11020047, 0x11020048, 0x11020046, null);
        map.put(Integer.valueOf(7), volumeiconres);
        sVolumeIconTypeMap.put(Integer.valueOf(8), new VolumeIconRes(0x1102004b, 0x1102004c, 0x1102004a, null));
    }

    // Unreferenced inner class miui/view/VolumeDialog$8

/* anonymous class */
    class _cls8
        implements android.content.DialogInterface.OnDismissListener
    {

        public void onDismiss(DialogInterface dialoginterface)
        {
            dialoginterface = ((DialogInterface) (VolumeDialog._2D_get13(VolumeDialog.this)));
            dialoginterface;
            JVM INSTR monitorenter ;
            VolumeDialog._2D_set3(VolumeDialog.this, null);
            dialoginterface;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final VolumeDialog this$0;

            
            {
                this$0 = VolumeDialog.this;
                super();
            }
    }


    // Unreferenced inner class miui/view/VolumeDialog$SafetyWarningDialog$1

/* anonymous class */
    class SafetyWarningDialog._cls1
        implements android.content.DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            VolumeDialog._2D_get3(_fld0).disableSafeMediaVolume();
        }

        final SafetyWarningDialog this$1;

            
            {
                this$1 = SafetyWarningDialog.this;
                super();
            }
    }


    // Unreferenced inner class miui/view/VolumeDialog$SafetyWarningDialog$2

/* anonymous class */
    class SafetyWarningDialog._cls2
        implements android.content.DialogInterface.OnDismissListener
    {

        public void onDismiss(DialogInterface dialoginterface)
        {
            Object obj = VolumeDialog._2D_get13(_fld0);
            obj;
            JVM INSTR monitorenter ;
            VolumeDialog._2D_set3(_fld0, null);
            obj;
            JVM INSTR monitorexit ;
            return;
            dialoginterface;
            throw dialoginterface;
        }

        final SafetyWarningDialog this$1;

            
            {
                this$1 = SafetyWarningDialog.this;
                super();
            }
    }

}
