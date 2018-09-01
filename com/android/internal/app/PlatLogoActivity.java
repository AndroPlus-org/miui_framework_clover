// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PlatLogoActivity extends Activity
{

    public PlatLogoActivity()
    {
        mInterpolator = new PathInterpolator(0.0F, 0.0F, 0.5F, 1.0F);
    }

    public void onAttachedToWindow()
    {
        final ImageView im = getResources().getDisplayMetrics();
        float f = ((DisplayMetrics) (im)).density;
        int i = (int)(Math.min(Math.min(((DisplayMetrics) (im)).widthPixels, ((DisplayMetrics) (im)).heightPixels), 600F * f) - 100F * f);
        im = new ImageView(this);
        int j = (int)(40F * f);
        im.setPadding(j, j, j, j);
        im.setTranslationZ(20F);
        im.setScaleX(0.5F);
        im.setScaleY(0.5F);
        im.setAlpha(0.0F);
        im.setBackground(new RippleDrawable(ColorStateList.valueOf(0xff776677), getDrawable(0x108060d), null));
        im.setOutlineProvider(new ViewOutlineProvider() {

            public void getOutline(View view, Outline outline)
            {
                int k = view.getWidth();
                int l = view.getHeight();
                outline.setOval((int)((double)k * 0.125D), (int)((double)l * 0.125D), (int)((double)k * 0.95999999999999996D), (int)((double)l * 0.95999999999999996D));
            }

            final PlatLogoActivity this$0;

            
            {
                this$0 = PlatLogoActivity.this;
                super();
            }
        }
);
        im.setElevation(12F * f);
        im.setClickable(true);
        im.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                im.setOnLongClickListener(im. new android.view.View.OnLongClickListener() {

                    public boolean onLongClick(View view)
                    {
                        if(mTapCount < 5)
                            return false;
                        view = getContentResolver();
                        if(android.provider.Settings.System.getLong(view, "egg_mode", 0L) == 0L)
                            try
                            {
                                android.provider.Settings.System.putLong(view, "egg_mode", System.currentTimeMillis());
                            }
                            // Misplaced declaration of an exception variable
                            catch(View view)
                            {
                                Log.e("PlatLogoActivity", "Can't write settings", view);
                            }
                        im.post(new Runnable() {

                            public void run()
                            {
                                try
                                {
                                    PlatLogoActivity platlogoactivity = _fld0;
                                    Intent intent = JVM INSTR new #36  <Class Intent>;
                                    intent.Intent("android.intent.action.MAIN");
                                    platlogoactivity.startActivity(intent.setFlags(0x10808000).addCategory("com.android.internal.category.PLATLOGO"));
                                }
                                catch(ActivityNotFoundException activitynotfoundexception)
                                {
                                    Log.e("PlatLogoActivity", "No more eggs.");
                                }
                                finish();
                            }

                            final _cls1 this$2;

            
            {
                this$2 = _cls1.this;
                super();
            }
                        }
);
                        return true;
                    }

                    final _cls2 this$1;
                    final ImageView val$im;

            
            {
                this$1 = final__pcls2;
                im = ImageView.this;
                super();
            }
                }
);
                view = PlatLogoActivity.this;
                view.mTapCount = ((PlatLogoActivity) (view)).mTapCount + 1;
            }

            final PlatLogoActivity this$0;
            final ImageView val$im;

            
            {
                this$0 = PlatLogoActivity.this;
                im = imageview;
                super();
            }
        }
);
        im.setFocusable(true);
        im.requestFocus();
        im.setOnKeyListener(new android.view.View.OnKeyListener() {

            public boolean onKey(View view, int k, KeyEvent keyevent)
            {
                if(k != 4 && keyevent.getAction() == 0)
                {
                    view = PlatLogoActivity.this;
                    view.mKeyCount = ((PlatLogoActivity) (view)).mKeyCount + 1;
                    if(mKeyCount > 2)
                        if(mTapCount > 5)
                            im.performLongClick();
                        else
                            im.performClick();
                    return true;
                } else
                {
                    return false;
                }
            }

            final PlatLogoActivity this$0;
            final ImageView val$im;

            
            {
                this$0 = PlatLogoActivity.this;
                im = imageview;
                super();
            }
        }
);
        mLayout.addView(im, new android.widget.FrameLayout.LayoutParams(i, i, 17));
        im.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setInterpolator(mInterpolator).setDuration(500L).setStartDelay(800L).start();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mLayout = new FrameLayout(this);
        setContentView(mLayout);
    }

    public static final boolean FINISH = true;
    PathInterpolator mInterpolator;
    int mKeyCount;
    FrameLayout mLayout;
    int mTapCount;
}
