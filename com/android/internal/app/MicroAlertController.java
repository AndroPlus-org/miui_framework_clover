// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.widget.ScrollView;
import android.widget.TextView;

// Referenced classes of package com.android.internal.app:
//            AlertController

public class MicroAlertController extends AlertController
{

    public MicroAlertController(Context context, DialogInterface dialoginterface, Window window)
    {
        super(context, dialoginterface, window);
    }

    protected void setupButtons(ViewGroup viewgroup)
    {
        super.setupButtons(viewgroup);
        if(viewgroup.getVisibility() == 8)
            viewgroup.setVisibility(4);
    }

    protected void setupContent(ViewGroup viewgroup)
    {
        mScrollView = (ScrollView)mWindow.findViewById(0x10203c2);
        mMessageView = (TextView)viewgroup.findViewById(0x102000b);
        if(mMessageView == null)
            return;
        if(mMessage != null)
        {
            mMessageView.setText(mMessage);
        } else
        {
            mMessageView.setVisibility(8);
            viewgroup.removeView(mMessageView);
            if(mListView != null)
            {
                viewgroup = mScrollView.findViewById(0x1020464);
                ((ViewGroup)viewgroup.getParent()).removeView(viewgroup);
                Object obj = new android.widget.FrameLayout.LayoutParams(viewgroup.getLayoutParams());
                obj.gravity = 48;
                viewgroup.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                obj = mScrollView.findViewById(0x10201da);
                ((ViewGroup)((View) (obj)).getParent()).removeView(((View) (obj)));
                Object obj1 = new android.widget.FrameLayout.LayoutParams(((View) (obj)).getLayoutParams());
                obj1.gravity = 80;
                ((View) (obj)).setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj1)));
                obj1 = (ViewGroup)mScrollView.getParent();
                ((ViewGroup) (obj1)).removeViewAt(((ViewGroup) (obj1)).indexOfChild(mScrollView));
                ((ViewGroup) (obj1)).addView(mListView, new android.view.ViewGroup.LayoutParams(-1, -1));
                ((ViewGroup) (obj1)).addView(viewgroup);
                ((ViewGroup) (obj1)).addView(((View) (obj)));
            } else
            {
                viewgroup.setVisibility(8);
            }
        }
    }

    protected void setupTitle(ViewGroup viewgroup)
    {
        super.setupTitle(viewgroup);
        if(viewgroup.getVisibility() == 8)
            viewgroup.setVisibility(4);
    }
}
