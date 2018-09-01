// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;

public class TextProgressBar extends RelativeLayout
    implements android.widget.Chronometer.OnChronometerTickListener
{

    public TextProgressBar(Context context)
    {
        super(context);
        mChronometer = null;
        mProgressBar = null;
        mDurationBase = -1L;
        mDuration = -1;
        mChronometerFollow = false;
        mChronometerGravity = 0;
    }

    public TextProgressBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mChronometer = null;
        mProgressBar = null;
        mDurationBase = -1L;
        mDuration = -1;
        mChronometerFollow = false;
        mChronometerGravity = 0;
    }

    public TextProgressBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mChronometer = null;
        mProgressBar = null;
        mDurationBase = -1L;
        mDuration = -1;
        mChronometerFollow = false;
        mChronometerGravity = 0;
    }

    public TextProgressBar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mChronometer = null;
        mProgressBar = null;
        mDurationBase = -1L;
        mDuration = -1;
        mChronometerFollow = false;
        mChronometerGravity = 0;
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        i = view.getId();
        if(i != 0x1020014 || !(view instanceof Chronometer)) goto _L2; else goto _L1
_L1:
        mChronometer = (Chronometer)view;
        mChronometer.setOnChronometerTickListener(this);
        boolean flag;
        if(layoutparams.width == -2)
            flag = true;
        else
            flag = false;
        mChronometerFollow = flag;
        mChronometerGravity = mChronometer.getGravity() & 0x800007;
_L4:
        return;
_L2:
        if(i == 0x102000d && (view instanceof ProgressBar))
            mProgressBar = (ProgressBar)view;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onChronometerTick(Chronometer chronometer)
    {
        if(mProgressBar == null)
            throw new RuntimeException("Expecting child ProgressBar with id 'android.R.id.progress'");
        long l = SystemClock.elapsedRealtime();
        if(l >= mDurationBase)
            mChronometer.stop();
        int i = (int)(mDurationBase - l);
        mProgressBar.setProgress(mDuration - i);
        if(mChronometerFollow)
        {
            chronometer = (android.widget.RelativeLayout.LayoutParams)mProgressBar.getLayoutParams();
            int k = mProgressBar.getWidth() - (((android.widget.RelativeLayout.LayoutParams) (chronometer)).leftMargin + ((android.widget.RelativeLayout.LayoutParams) (chronometer)).rightMargin);
            int i1 = (mProgressBar.getProgress() * k) / mProgressBar.getMax();
            int j1 = ((android.widget.RelativeLayout.LayoutParams) (chronometer)).leftMargin;
            int j = 0;
            int k1 = mChronometer.getWidth();
            if(mChronometerGravity == 0x800005)
                j = -k1;
            else
            if(mChronometerGravity == 1)
                j = -(k1 / 2);
            i1 = i1 + j1 + j;
            k1 = k - ((android.widget.RelativeLayout.LayoutParams) (chronometer)).rightMargin - k1;
            if(i1 < ((android.widget.RelativeLayout.LayoutParams) (chronometer)).leftMargin)
            {
                j = ((android.widget.RelativeLayout.LayoutParams) (chronometer)).leftMargin;
            } else
            {
                j = i1;
                if(i1 > k1)
                    j = k1;
            }
            ((android.widget.RelativeLayout.LayoutParams)mChronometer.getLayoutParams()).leftMargin = j;
            mChronometer.requestLayout();
        }
    }

    public void setDurationBase(long l)
    {
        mDurationBase = l;
        if(mProgressBar == null || mChronometer == null)
            throw new RuntimeException("Expecting child ProgressBar with id 'android.R.id.progress' and Chronometer id 'android.R.id.text1'");
        mDuration = (int)(l - mChronometer.getBase());
        if(mDuration <= 0)
            mDuration = 1;
        mProgressBar.setMax(mDuration);
    }

    static final int CHRONOMETER_ID = 0x1020014;
    static final int PROGRESSBAR_ID = 0x102000d;
    public static final String TAG = "TextProgressBar";
    Chronometer mChronometer;
    boolean mChronometerFollow;
    int mChronometerGravity;
    int mDuration;
    long mDurationBase;
    ProgressBar mProgressBar;
}
