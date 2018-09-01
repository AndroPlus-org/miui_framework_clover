// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

public class AccountItemView extends LinearLayout
{

    public AccountItemView(Context context)
    {
        this(context, null);
    }

    public AccountItemView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x10900f7, null);
        addView(context);
        initViewItem(context);
    }

    private void initViewItem(View view)
    {
        mAccountIcon = (ImageView)view.findViewById(0x1020006);
        mAccountName = (TextView)view.findViewById(0x1020016);
        mAccountNumber = (TextView)view.findViewById(0x1020010);
    }

    private void setText(TextView textview, String s)
    {
        if(TextUtils.isEmpty(s))
        {
            textview.setVisibility(8);
        } else
        {
            textview.setText(s);
            textview.setVisibility(0);
        }
    }

    public void setAccountIcon(int i)
    {
        mAccountIcon.setImageResource(i);
    }

    public void setAccountIcon(Drawable drawable)
    {
        mAccountIcon.setBackgroundDrawable(drawable);
    }

    public void setAccountName(String s)
    {
        setText(mAccountName, s);
    }

    public void setAccountNumber(String s)
    {
        setText(mAccountNumber, s);
    }

    public void setViewItem(AccountViewAdapter.AccountElements accountelements)
    {
        Drawable drawable = accountelements.getDrawable();
        if(drawable != null)
            setAccountIcon(drawable);
        else
            setAccountIcon(accountelements.getIcon());
        setAccountName(accountelements.getName());
        setAccountNumber(accountelements.getNumber());
    }

    private ImageView mAccountIcon;
    private TextView mAccountName;
    private TextView mAccountNumber;
}
