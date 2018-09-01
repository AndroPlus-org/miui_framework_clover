// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os.storage;

import android.content.Context;
import miui.app.ProgressDialog;

class ExternalStorageFormatterInjector
{

    ExternalStorageFormatterInjector()
    {
    }

    static ProgressDialog createProgressDialog(Context context)
    {
        return new ProgressDialog(context, miui.R.style.Theme_Light_Dialog_Alert);
    }
}
