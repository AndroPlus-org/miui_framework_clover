// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Slog;
import android.view.*;
import android.widget.TextView;

public class TooltipPopup
{

    public TooltipPopup(Context context)
    {
        mContext = context;
        mContentView = LayoutInflater.from(mContext).inflate(0x1090115, null);
        mMessageView = (TextView)mContentView.findViewById(0x102000b);
        mLayoutParams.setTitle(mContext.getString(0x104064a));
        mLayoutParams.packageName = mContext.getOpPackageName();
        mLayoutParams.type = 1005;
        mLayoutParams.width = -2;
        mLayoutParams.height = -2;
        mLayoutParams.format = -3;
        mLayoutParams.windowAnimations = 0x1030300;
        mLayoutParams.flags = 24;
    }

    private void computePosition(View view, int i, int j, boolean flag, android.view.WindowManager.LayoutParams layoutparams)
    {
        layoutparams.token = view.getApplicationWindowToken();
        int k = mContext.getResources().getDimensionPixelOffset(0x10501af);
        int i1;
        Object obj;
        if(view.getWidth() < k)
            i = view.getWidth() / 2;
        if(view.getHeight() >= k)
        {
            int l = mContext.getResources().getDimensionPixelOffset(0x10501ae);
            k = j + l;
            l = j - l;
            j = k;
            k = l;
        } else
        {
            j = view.getHeight();
            k = 0;
        }
        layoutparams.gravity = 49;
        obj = mContext.getResources();
        if(flag)
            i1 = 0x10501b2;
        else
            i1 = 0x10501b1;
        i1 = ((Resources) (obj)).getDimensionPixelOffset(i1);
        obj = WindowManagerGlobal.getInstance().getWindowView(view.getApplicationWindowToken());
        if(obj == null)
        {
            Slog.e("TooltipPopup", "Cannot find app view");
            return;
        }
        ((View) (obj)).getWindowVisibleDisplayFrame(mTmpDisplayFrame);
        ((View) (obj)).getLocationOnScreen(mTmpAppPos);
        view.getLocationOnScreen(mTmpAnchorPos);
        view = mTmpAnchorPos;
        view[0] = view[0] - mTmpAppPos[0];
        view = mTmpAnchorPos;
        view[1] = view[1] - mTmpAppPos[1];
        layoutparams.x = (mTmpAnchorPos[0] + i) - mTmpDisplayFrame.width() / 2;
        i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        mContentView.measure(i, i);
        i = mContentView.getMeasuredHeight();
        k = (mTmpAnchorPos[1] + k) - i1 - i;
        j = mTmpAnchorPos[1] + j + i1;
        if(flag)
        {
            if(k >= 0)
                layoutparams.y = k;
            else
                layoutparams.y = j;
        } else
        if(j + i <= mTmpDisplayFrame.height())
            layoutparams.y = j;
        else
            layoutparams.y = k;
    }

    public View getContentView()
    {
        return mContentView;
    }

    public void hide()
    {
        if(!isShowing())
        {
            return;
        } else
        {
            ((WindowManager)mContext.getSystemService("window")).removeView(mContentView);
            return;
        }
    }

    public boolean isShowing()
    {
        boolean flag;
        if(mContentView.getParent() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void show(View view, int i, int j, boolean flag, CharSequence charsequence)
    {
        if(isShowing())
            hide();
        mMessageView.setText(charsequence);
        computePosition(view, i, j, flag, mLayoutParams);
        ((WindowManager)mContext.getSystemService("window")).addView(mContentView, mLayoutParams);
    }

    private static final String TAG = "TooltipPopup";
    private final View mContentView;
    private final Context mContext;
    private final android.view.WindowManager.LayoutParams mLayoutParams = new android.view.WindowManager.LayoutParams();
    private final TextView mMessageView;
    private final int mTmpAnchorPos[] = new int[2];
    private final int mTmpAppPos[] = new int[2];
    private final Rect mTmpDisplayFrame = new Rect();
}
