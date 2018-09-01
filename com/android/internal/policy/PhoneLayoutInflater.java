// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class PhoneLayoutInflater extends LayoutInflater
{

    public PhoneLayoutInflater(Context context)
    {
        super(context);
    }

    protected PhoneLayoutInflater(LayoutInflater layoutinflater, Context context)
    {
        super(layoutinflater, context);
    }

    public LayoutInflater cloneInContext(Context context)
    {
        return new PhoneLayoutInflater(this, context);
    }

    protected View onCreateView(String s, AttributeSet attributeset)
        throws ClassNotFoundException
    {
        String as[];
        int i;
        int j;
        as = sClassPrefixList;
        i = 0;
        j = as.length;
_L3:
        if(i >= j) goto _L2; else goto _L1
_L1:
        Object obj = as[i];
        obj = createView(s, ((String) (obj)), attributeset);
        if(obj != null)
            return ((View) (obj));
        continue; /* Loop/switch isn't completed */
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        i++;
          goto _L3
_L2:
        return super.onCreateView(s, attributeset);
    }

    private static final String sClassPrefixList[] = {
        "android.widget.", "android.webkit.", "android.app."
    };

}
