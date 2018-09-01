// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;

public class NotificationExpandButton extends ImageView
{

    public NotificationExpandButton(Context context)
    {
        super(context);
    }

    public NotificationExpandButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public NotificationExpandButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public NotificationExpandButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    private void extendRectToMinTouchSize(Rect rect)
    {
        int i = (int)(getResources().getDisplayMetrics().density * 48F);
        rect.left = rect.centerX() - i / 2;
        rect.right = rect.left + i;
        rect.top = rect.centerY() - i / 2;
        rect.bottom = rect.top + i;
    }

    public void getBoundsOnScreen(Rect rect, boolean flag)
    {
        super.getBoundsOnScreen(rect, flag);
        extendRectToMinTouchSize(rect);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/Button.getName());
    }
}
