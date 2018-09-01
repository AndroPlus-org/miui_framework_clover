// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.view.View;
import android.widget.AdapterView;

class NavItemSelectedListener
    implements android.widget.AdapterView.OnItemSelectedListener
{

    public NavItemSelectedListener(android.app.ActionBar.OnNavigationListener onnavigationlistener)
    {
        mListener = onnavigationlistener;
    }

    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
    {
        if(mListener != null)
            mListener.onNavigationItemSelected(i, l);
    }

    public void onNothingSelected(AdapterView adapterview)
    {
    }

    private final android.app.ActionBar.OnNavigationListener mListener;
}
