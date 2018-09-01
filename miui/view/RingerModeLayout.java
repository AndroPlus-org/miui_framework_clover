// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ExtraNotificationManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.widget.SeekBar;
import miui.widget.SlidingButton;

// Referenced classes of package miui.view:
//            VolumeDialog

public class RingerModeLayout extends LinearLayout
{
    private final class H extends Handler
    {

        public void handleMessage(Message message)
        {
            if(!RingerModeLayout._2D_get10(RingerModeLayout.this))
                return;
            message.what;
            JVM INSTR tableswitch 1 2: default 36
        //                       1 37
        //                       2 47;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            RingerModeLayout._2D_wrap4(RingerModeLayout.this);
            continue; /* Loop/switch isn't completed */
_L3:
            RingerModeLayout._2D_wrap3(RingerModeLayout.this);
            RingerModeLayout._2D_get12(RingerModeLayout.this).setChecked(RingerModeLayout._2D_wrap0(RingerModeLayout.this));
            RingerModeLayout._2D_get16(RingerModeLayout.this).recheckAll();
            if(true) goto _L1; else goto _L4
_L4:
        }

        private static final int UPDATE_EXPAND_CONTENT = 2;
        private static final int UPDATE_TIME = 1;
        final RingerModeLayout this$0;

        public H(Looper looper)
        {
            this$0 = RingerModeLayout.this;
            super(looper);
        }
    }

    private final class SilenceModeObserver extends ContentObserver
    {

        public void onChange(boolean flag, Uri uri)
        {
            super.onChange(flag, uri);
            int i = android.provider.MiuiSettings.SilenceMode.getZenMode(RingerModeLayout._2D_get1(RingerModeLayout.this));
            Log.i("RingerModeLayout", (new StringBuilder()).append("Zenmode changeded ").append(RingerModeLayout._2D_get2(RingerModeLayout.this)).append(" -> ").append(i).toString());
            RingerModeLayout._2D_set1(RingerModeLayout.this, i);
            RingerModeLayout._2D_get4(RingerModeLayout.this).sendEmptyMessage(2);
        }

        public void register()
        {
            RingerModeLayout._2D_get1(RingerModeLayout.this).getContentResolver().registerContentObserver(silence_mode, false, this, -1);
        }

        public void unregister()
        {
            RingerModeLayout._2D_get1(RingerModeLayout.this).getContentResolver().unregisterContentObserver(this);
        }

        private final Uri silence_mode = android.provider.Settings.Global.getUriFor("zen_mode");
        final RingerModeLayout this$0;

        public SilenceModeObserver()
        {
            this$0 = RingerModeLayout.this;
            super(RingerModeLayout._2D_get4(RingerModeLayout.this));
        }
    }


    static boolean _2D_get0(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mAnimating;
    }

    static Context _2D_get1(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mContext;
    }

    static boolean _2D_get10(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mShowing;
    }

    static LinearLayout _2D_get11(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mSilenceModeExpandContent;
    }

    static SlidingButton _2D_get12(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mSlidingButton;
    }

    static RelativeLayout _2D_get13(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mTimeLabel;
    }

    static List _2D_get14(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mTimeList;
    }

    static SeekBar _2D_get15(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mTimeSeekbar;
    }

    static VolumeDialog _2D_get16(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mVolumeDialog;
    }

    static int _2D_get2(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mCurrentMode;
    }

    static ViewGroup _2D_get3(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mDialogView;
    }

    static H _2D_get4(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mHandler;
    }

    static ImageView _2D_get5(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mHelpBtn;
    }

    static RadioGroup _2D_get6(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mRadioGroup;
    }

    static TextView _2D_get7(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mRemainTime_1;
    }

    static TextView _2D_get8(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mRemainTime_2;
    }

    static TextView _2D_get9(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.mSelectedText;
    }

    static boolean _2D_set0(RingerModeLayout ringermodelayout, boolean flag)
    {
        ringermodelayout.mAnimating = flag;
        return flag;
    }

    static int _2D_set1(RingerModeLayout ringermodelayout, int i)
    {
        ringermodelayout.mCurrentMode = i;
        return i;
    }

    static TextView _2D_set2(RingerModeLayout ringermodelayout, TextView textview)
    {
        ringermodelayout.mSelectedText = textview;
        return textview;
    }

    static boolean _2D_wrap0(RingerModeLayout ringermodelayout)
    {
        return ringermodelayout.isSilenceModeEnabled();
    }

    static int _2D_wrap1(RingerModeLayout ringermodelayout, int i)
    {
        return ringermodelayout.getProgressLevel(i);
    }

    static int _2D_wrap2(RingerModeLayout ringermodelayout, int i)
    {
        return ringermodelayout.progressToMinute(i);
    }

    static void _2D_wrap3(RingerModeLayout ringermodelayout)
    {
        ringermodelayout.updateRadioGroup();
    }

    static void _2D_wrap4(RingerModeLayout ringermodelayout)
    {
        ringermodelayout.updateRemainTimeSeekbar();
    }

    public RingerModeLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mSilenceButtonChangedListener = new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if(!RingerModeLayout._2D_get10(RingerModeLayout.this))
                    return;
                if(flag)
                {
                    Log.d("RingerModeLayout", "sliding to true");
                    RingerModeLayout._2D_get16(RingerModeLayout.this).setExpandedH(false);
                    expandSilenceModeContent(true);
                    if(RingerModeLayout._2D_get2(RingerModeLayout.this) == 0)
                    {
                        int i = android.provider.MiuiSettings.SilenceMode.getLastestQuietMode(RingerModeLayout._2D_get1(RingerModeLayout.this));
                        android.provider.MiuiSettings.SilenceMode.setSilenceMode(RingerModeLayout._2D_get1(RingerModeLayout.this), i, null);
                    }
                    RingerModeLayout._2D_wrap3(RingerModeLayout.this);
                } else
                {
                    Log.d("RingerModeLayout", "sliding to false");
                    RingerModeLayout._2D_get6(RingerModeLayout.this).clearCheck();
                    RingerModeLayout._2D_set1(RingerModeLayout.this, 0);
                    expandSilenceModeContent(false);
                    android.provider.MiuiSettings.SilenceMode.setSilenceMode(RingerModeLayout._2D_get1(RingerModeLayout.this), 0, null);
                    RingerModeLayout._2D_get16(RingerModeLayout.this).recheckAll();
                    RingerModeLayout._2D_get4(RingerModeLayout.this).removeMessages(1);
                    RingerModeLayout._2D_get4(RingerModeLayout.this).sendEmptyMessageDelayed(1, 50L);
                }
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
;
        mTimeSeekBarChangedListener = new android.widget.SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(android.widget.SeekBar seekbar, int i, boolean flag)
            {
                if(RingerModeLayout._2D_get13(RingerModeLayout.this).getVisibility() == 0)
                {
                    i = RingerModeLayout._2D_wrap1(RingerModeLayout.this, i);
                    if(!((TextView)RingerModeLayout._2D_get14(RingerModeLayout.this).get(i)).equals(RingerModeLayout._2D_get9(RingerModeLayout.this)))
                    {
                        RingerModeLayout._2D_get9(RingerModeLayout.this).setTextSize(1, 10F);
                        RingerModeLayout._2D_get9(RingerModeLayout.this).setTextColor(getResources().getColor(0x11060021));
                        RingerModeLayout._2D_set2(RingerModeLayout.this, (TextView)RingerModeLayout._2D_get14(RingerModeLayout.this).get(i));
                        RingerModeLayout._2D_get9(RingerModeLayout.this).setTextSize(1, 12F);
                        RingerModeLayout._2D_get9(RingerModeLayout.this).setTextColor(getResources().getColor(0x11060020));
                    }
                }
            }

            public void onStartTrackingTouch(android.widget.SeekBar seekbar)
            {
                RingerModeLayout._2D_get13(RingerModeLayout.this).setVisibility(0);
                RingerModeLayout._2D_get8(RingerModeLayout.this).setVisibility(8);
                RingerModeLayout._2D_get4(RingerModeLayout.this).removeMessages(1);
            }

            public void onStopTrackingTouch(android.widget.SeekBar seekbar)
            {
                int i = RingerModeLayout._2D_wrap1(RingerModeLayout.this, seekbar.getProgress());
                seekbar.setProgress(i * 25);
                int j = RingerModeLayout._2D_wrap2(RingerModeLayout.this, i * 25);
                i = android.provider.MiuiSettings.SilenceMode.getZenMode(RingerModeLayout._2D_get1(RingerModeLayout.this));
                RingerModeLayout._2D_get4(RingerModeLayout.this).removeMessages(1);
                ExtraNotificationManager.startCountDownSilenceMode(RingerModeLayout._2D_get1(RingerModeLayout.this), i, j);
                if(seekbar.getProgress() > 0)
                    RingerModeLayout._2D_get4(RingerModeLayout.this).sendEmptyMessageDelayed(1, 50L);
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
;
        mRadioButtonListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Uri uri;
                int i;
                uri = ExtraNotificationManager.getConditionId(RingerModeLayout._2D_get1(RingerModeLayout.this));
                i = 0;
                if(view.getId() != 0x110c003f) goto _L2; else goto _L1
_L1:
                Log.d("RingerModeLayout", "set total mode");
                i = 1;
_L4:
                android.provider.MiuiSettings.SilenceMode.setSilenceMode(RingerModeLayout._2D_get1(RingerModeLayout.this), i, uri);
                return;
_L2:
                if(view.getId() == 0x110c003e)
                {
                    Log.d("RingerModeLayout", "set standard mode");
                    i = 4;
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
;
        mHelpButtonListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                RingerModeLayout._2D_get5(RingerModeLayout.this).setImageDrawable(getResources().getDrawable(0x11020024));
                view = ComponentName.unflattenFromString("com.android.settings/com.android.settings.Settings$MiuiSilentModeAcivity");
                if(view == null)
                    return;
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setComponent(view);
                intent.setFlags(0x14000000);
                try
                {
                    RingerModeLayout._2D_get1(RingerModeLayout.this).startActivityAsUser(intent, UserHandle.CURRENT);
                }
                catch(Exception exception)
                {
                    Log.e("RingerModeLayout", (new StringBuilder()).append("start activity exception, component = ").append(view).toString());
                }
                RingerModeLayout._2D_get16(RingerModeLayout.this).dismiss();
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
;
        mTimeLabelListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                int i = Integer.parseInt((String)view.getTag());
                RingerModeLayout._2D_get15(RingerModeLayout.this).setProgress(i * 25);
                int j = RingerModeLayout._2D_wrap2(RingerModeLayout.this, i * 25);
                int k = android.provider.MiuiSettings.SilenceMode.getZenMode(RingerModeLayout._2D_get1(RingerModeLayout.this));
                RingerModeLayout._2D_get4(RingerModeLayout.this).removeMessages(1);
                ExtraNotificationManager.startCountDownSilenceMode(RingerModeLayout._2D_get1(RingerModeLayout.this), k, j);
                if(i > 0)
                    RingerModeLayout._2D_get4(RingerModeLayout.this).sendEmptyMessageDelayed(1, 50L);
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
;
        mContext = context;
        mOrignalRemain = ExtraNotificationManager.getRemainTime(mContext);
        mOrignalMode = ExtraNotificationManager.getZenMode(mContext);
    }

    private void changeSilenceModeTitle(boolean flag)
    {
        if(flag)
        {
            int i;
            if(mCurrentMode == 4)
                i = 0x110800db;
            else
                i = 0x110800dc;
            mSilenceModeTitle.setText(i);
        } else
        {
            mSilenceModeTitle.setText(0x110800d8);
        }
    }

    private int getProgressLevel(int i)
    {
        if(i <= 12)
            i = 0;
        else
        if(Math.abs(i - 25) <= 12)
            i = 1;
        else
        if(Math.abs(i - 50) <= 12)
            i = 2;
        else
        if(Math.abs(i - 75) <= 12)
            i = 3;
        else
            i = 4;
        return i;
    }

    private int getXPosition(SeekBar seekbar)
    {
        float f = mRemainTime_2.getPaint().measureText(mRemainTime_2.getText().toString());
        float f1 = ((android.widget.LinearLayout.LayoutParams)seekbar.getLayoutParams()).getMarginStart();
        float f2 = seekbar.getMeasuredWidth() - seekbar.getThumb().getIntrinsicWidth();
        return (int)(((((float)seekbar.getProgress() * f2) / (float)seekbar.getMax() + (float)(seekbar.getThumb().getIntrinsicWidth() / 2)) - f / 2.0F) + f1);
    }

    private boolean isSilenceModeEnabled()
    {
        boolean flag = false;
        if(mCurrentMode > 0)
            flag = true;
        return flag;
    }

    private int progressToMinute(int i)
    {
        int j = 0;
        if(i > 50) goto _L2; else goto _L1
_L1:
        j = (i / 25) * 30;
_L4:
        return j;
_L2:
        if(i <= 75)
            j = 120;
        else
        if(i <= 100)
            j = 480;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int timeToMinute(long l)
    {
        if(l == 0L)
            return 0;
        if(l <= 0x1b7740L)
            return 30;
        if(l <= 0x36ee80L)
            return 60;
        return l > 0x6ddd00L ? 480 : 120;
    }

    private int timeToProgress(long l)
    {
        long l1 = 0L;
        if(l > 3600L) goto _L2; else goto _L1
_L1:
        l1 = l / 72L;
_L4:
        return (int)l1;
_L2:
        if(l <= 7200L)
            l1 = (l - 3600L) / 144L + 50L;
        else
        if(l <= 28800L)
            l1 = (l - 3600L) / 864L + 75L;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String turnMillSecondsToHour(long l)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = (int)(l / 0x36ee80L);
        int j = (int)(l % 0x36ee80L);
        int k = j / 60000;
        j = (j % 60000) / 1000;
        if(i > 0)
        {
            if(i < 10)
                stringbuilder.append("0");
            stringbuilder.append(i).append(":");
        }
        if(k < 10)
            stringbuilder.append("0");
        stringbuilder.append(k).append(":");
        if(j < 10)
            stringbuilder.append("0");
        stringbuilder.append(j);
        return stringbuilder.toString();
    }

    private void updateRadioGroup()
    {
        if(isSilenceModeEnabled() && mShowing)
        {
            RadioGroup radiogroup = mRadioGroup;
            int i;
            if(mCurrentMode == 4)
                i = 0x110c003e;
            else
                i = 0x110c003f;
            radiogroup.check(i);
            changeSilenceModeTitle(true);
        }
    }

    private void updateRemainText(boolean flag)
    {
        if(mRemainTextShown == flag)
            return;
        Log.d("RingerModeLayout", "updateRemainText...");
        mRemainTextShown = flag;
        float f;
        float f1;
        ValueAnimator valueanimator;
        if(flag)
        {
            f = 0.0F;
            f1 = 1.0F;
        } else
        {
            f = 1.0F;
            f1 = 0.0F;
        }
        valueanimator = ValueAnimator.ofFloat(new float[] {
            f, f1
        });
        valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                float f2 = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                RingerModeLayout._2D_get7(RingerModeLayout.this).setAlpha(f2);
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
);
        valueanimator.setDuration(300L);
        valueanimator.start();
    }

    private void updateRemainTimeSeekbar()
    {
        if(!mShowing)
            return;
        long l = ExtraNotificationManager.getRemainTime(mContext);
        if(l > 0L)
        {
            mTimeLabel.setVisibility(8);
            mRemainTime_2.setVisibility(0);
            updateRemainText(mSilenceModeExpanded ^ true);
            mTimeSeekbar.setProgress(timeToProgress(l / 1000L));
            mRemainTime_1.setText(mContext.getString(0x110800e6, new Object[] {
                turnMillSecondsToHour(l)
            }));
            mRemainTime_2.setText(turnMillSecondsToHour(l));
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)mRemainTime_2.getLayoutParams();
            layoutparams.setMarginStart(getXPosition(mTimeSeekbar));
            mRemainTime_2.setLayoutParams(layoutparams);
            mHandler.removeMessages(1);
            mHandler.sendMessageDelayed(mHandler.obtainMessage(1), 1000L);
        } else
        {
            mTimeLabel.setVisibility(0);
            mRemainTime_2.setVisibility(8);
            updateRemainText(false);
            mTimeSeekbar.setProgress(0);
        }
    }

    public void cleanUp()
    {
        mHandler.removeMessages(1);
        if(!mShowing)
            return;
        long l = ExtraNotificationManager.getRemainTime(mContext);
        int i = ExtraNotificationManager.getZenMode(mContext);
        if(Math.abs(mOrignalRemain - l) >= 30000L || mOrignalMode != i)
        {
            int j = timeToMinute(l);
            android.provider.MiuiSettings.SilenceMode.reportRingerModeInfo("silence_DND", android.provider.MiuiSettings.SilenceMode.MISTAT_RINGERMODE_LIST[i], String.valueOf(j), System.currentTimeMillis());
        }
        mShowing = false;
        mSilenceModeObserver.unregister();
        mTimeSeekbar = null;
        mRemainTime_2 = null;
        mRadioGroup = null;
        mTimeList.clear();
    }

    public void expandSilenceModeContent(boolean flag)
    {
        boolean flag1;
        if(mSilenceModeExpandContent != null)
            flag1 = mSilenceModeExpandContent.isAttachedToWindow();
        else
            flag1 = false;
        mAnimating = flag1;
        changeSilenceModeTitle(isSilenceModeEnabled());
        if(mSilenceModeExpanded == flag || flag && mSlidingButton.isChecked() ^ true || mAnimating ^ true)
        {
            Log.d("RingerModeLayout", (new StringBuilder()).append("Silence mode content is alread ").append(flag).toString());
            mAnimating = false;
            return;
        }
        mSilenceModeExpanded = flag;
        int i;
        int j;
        ValueAnimator valueanimator;
        if(flag)
        {
            i = 0;
            j = ContentHeight;
        } else
        {
            i = mSilenceModeExpandContent.getHeight();
            j = 0;
        }
        valueanimator = ValueAnimator.ofInt(new int[] {
            i, j
        });
        valueanimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator1)
            {
                int k = ((Integer)valueanimator1.getAnimatedValue()).intValue();
                RingerModeLayout._2D_get11(RingerModeLayout.this).getLayoutParams().height = k;
                RingerModeLayout._2D_get11(RingerModeLayout.this).requestLayout();
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
);
        valueanimator.addListener(new android.animation.Animator.AnimatorListener() {

            public void onAnimationCancel(Animator animator)
            {
            }

            public void onAnimationEnd(Animator animator)
            {
                animator = (ViewGroup)RingerModeLayout._2D_get3(RingerModeLayout.this).getParent();
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)animator.getLayoutParams();
                marginlayoutparams.height = -2;
                animator.setLayoutParams(marginlayoutparams);
                RingerModeLayout._2D_get16(RingerModeLayout.this).rescheduleTimeout(true);
                RingerModeLayout._2D_set0(RingerModeLayout.this, false);
            }

            public void onAnimationRepeat(Animator animator)
            {
            }

            public void onAnimationStart(Animator animator)
            {
                animator = (ViewGroup)RingerModeLayout._2D_get3(RingerModeLayout.this).getParent();
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)animator.getLayoutParams();
                marginlayoutparams.height = 1000;
                animator.setLayoutParams(marginlayoutparams);
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
);
        valueanimator.setInterpolator(new DecelerateInterpolator());
        valueanimator.setDuration(300L);
        valueanimator.start();
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessage(1);
    }

    public void init()
    {
        Log.d("RingerModeLayout", "init...");
        mSilenceModeContent = (RelativeLayout)findViewById(0x110c0037);
        mSilenceModeExpandContent = (LinearLayout)findViewById(0x110c003b);
        mSilenceModeTitle = (TextView)findViewById(0x110c0038);
        mTimeSeekbar = (SeekBar)findViewById(0x110c0048);
        mRadioGroup = (RadioGroup)findViewById(0x110c003d);
        mStandardBtn = (RadioButton)findViewById(0x110c003e);
        mTotalBtn = (RadioButton)findViewById(0x110c003f);
        mHelpBtn = (ImageView)findViewById(0x110c0040);
        mSlidingButton = (SlidingButton)findViewById(0x110c0039);
        mRemainTime_1 = (TextView)findViewById(0x110c003a);
        mRemainTime_2 = (TextView)findViewById(0x110c0047);
        mTimeLabel = (RelativeLayout)findViewById(0x110c0041);
        mTimeList = new ArrayList();
        mTimeList.add((TextView)findViewById(0x110c0042));
        mTimeList.add((TextView)findViewById(0x110c0045));
        mTimeList.add((TextView)findViewById(0x110c0044));
        mTimeList.add((TextView)findViewById(0x110c0046));
        mTimeList.add((TextView)findViewById(0x110c0043));
        for(Iterator iterator = mTimeList.iterator(); iterator.hasNext(); ((TextView)iterator.next()).setOnClickListener(mTimeLabelListener));
        mSelectedText = (TextView)mTimeList.get(0);
        mSelectedText.setTextColor(getResources().getColor(0x11060020));
        mSelectedText.setTextSize(1, 12F);
        mRadioGroup.measure(0, 0);
        mStandardBtn.getLayoutParams().height = mRadioGroup.getMeasuredHeight();
        mTotalBtn.getLayoutParams().height = mRadioGroup.getMeasuredHeight();
        findViewById(0x110c003c).getLayoutParams().height = mRadioGroup.getMeasuredHeight();
        mSilenceModeExpandContent.measure(0, 0);
        ContentHeight = mSilenceModeExpandContent.getMeasuredHeight();
        mHelpBtn.setOnClickListener(mHelpButtonListener);
        mShowing = true;
        mSilenceModeObserver.register();
        mSilenceModeContent.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(RingerModeLayout._2D_get0(RingerModeLayout.this) || RingerModeLayout._2D_get16(RingerModeLayout.this).mExpandAnimating)
                {
                    return;
                } else
                {
                    expandSilenceModeContent(mSilenceModeExpanded ^ true);
                    RingerModeLayout._2D_get16(RingerModeLayout.this).setExpandedH(false);
                    return;
                }
            }

            final RingerModeLayout this$0;

            
            {
                this$0 = RingerModeLayout.this;
                super();
            }
        }
);
        mCurrentMode = android.provider.MiuiSettings.SilenceMode.getZenMode(mContext);
        mSlidingButton.setChecked(isSilenceModeEnabled());
        updateRadioGroup();
        mSlidingButton.setOnCheckedChangeListener(mSilenceButtonChangedListener);
        mTimeSeekbar.setOnSeekBarChangeListener(mTimeSeekBarChangedListener);
        mTotalBtn.setOnClickListener(mRadioButtonListener);
        mStandardBtn.setOnClickListener(mRadioButtonListener);
        mSilenceModeExpandContent.getLayoutParams().height = 0;
        mSilenceModeExpanded = false;
        mRemainTextShown = false;
        if(isSilenceModeEnabled())
        {
            int i;
            if(mCurrentMode == 4)
                i = 0x110800db;
            else
                i = 0x110800dc;
            mSilenceModeTitle.setText(i);
        } else
        {
            mSilenceModeTitle.setText(0x110800d8);
        }
        updateRemainTimeSeekbar();
    }

    public void setLooper(Looper looper)
    {
        mLooper = looper;
        mHandler = new H(mLooper);
    }

    public void setParentDialog(ViewGroup viewgroup)
    {
        mDialogView = viewgroup;
    }

    public void setVolumeDialog(VolumeDialog volumedialog)
    {
        mVolumeDialog = volumedialog;
    }

    private static final int ANIMATION_DURATION = 300;
    private static final String TAG = "RingerModeLayout";
    private int ContentHeight;
    private boolean mAnimating;
    private final Context mContext;
    private int mCurrentMode;
    private ViewGroup mDialogView;
    private H mHandler;
    private ImageView mHelpBtn;
    private android.view.View.OnClickListener mHelpButtonListener;
    private Looper mLooper;
    private int mOrignalMode;
    private long mOrignalRemain;
    private android.view.View.OnClickListener mRadioButtonListener;
    private RadioGroup mRadioGroup;
    private boolean mRemainTextShown;
    private TextView mRemainTime_1;
    private TextView mRemainTime_2;
    private TextView mSelectedText;
    private boolean mShowing;
    private android.widget.CompoundButton.OnCheckedChangeListener mSilenceButtonChangedListener;
    private RelativeLayout mSilenceModeContent;
    private LinearLayout mSilenceModeExpandContent;
    public boolean mSilenceModeExpanded;
    private final SilenceModeObserver mSilenceModeObserver = new SilenceModeObserver();
    private TextView mSilenceModeTitle;
    private SlidingButton mSlidingButton;
    private RadioButton mStandardBtn;
    private RelativeLayout mTimeLabel;
    private android.view.View.OnClickListener mTimeLabelListener;
    private List mTimeList;
    private android.widget.SeekBar.OnSeekBarChangeListener mTimeSeekBarChangedListener;
    private SeekBar mTimeSeekbar;
    private RadioButton mTotalBtn;
    private VolumeDialog mVolumeDialog;
}
