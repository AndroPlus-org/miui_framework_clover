// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.mipay.android.manager;

import android.content.Context;

public interface IMipayManager
{

    public abstract boolean contains(String s);

    public abstract int generateKeyPair(String s, String s1);

    public abstract String getFpIds();

    public abstract int getSupportBIOTypes(Context context);

    public abstract int getVersion();

    public abstract int removeAllKey();

    public abstract byte[] sign();

    public abstract int signInit(String s, String s1);

    public abstract int signUpdate(byte abyte0[], int i, int j);
}
