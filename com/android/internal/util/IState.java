// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.Message;

public interface IState
{

    public abstract void enter();

    public abstract void exit();

    public abstract String getName();

    public abstract boolean processMessage(Message message);

    public static final boolean HANDLED = true;
    public static final boolean NOT_HANDLED = false;
}
