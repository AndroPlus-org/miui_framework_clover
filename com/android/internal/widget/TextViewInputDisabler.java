// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;

public class TextViewInputDisabler
{

    public TextViewInputDisabler(TextView textview)
    {
        mTextView = textview;
        mDefaultFilters = mTextView.getFilters();
    }

    public void setInputEnabled(boolean flag)
    {
        TextView textview = mTextView;
        InputFilter ainputfilter[];
        if(flag)
            ainputfilter = mDefaultFilters;
        else
            ainputfilter = mNoInputFilters;
        textview.setFilters(ainputfilter);
    }

    private InputFilter mDefaultFilters[];
    private InputFilter mNoInputFilters[] = {
        new InputFilter() {

            public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
            {
                return "";
            }

            final TextViewInputDisabler this$0;

            
            {
                this$0 = TextViewInputDisabler.this;
                super();
            }
        }

    };
    private TextView mTextView;
}
