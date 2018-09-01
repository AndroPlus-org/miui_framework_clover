// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


public class FunctionalUtils
{
    public static interface ThrowingConsumer
    {

        public abstract void accept(Object obj)
            throws Exception;
    }

    public static interface ThrowingRunnable
    {

        public abstract void run()
            throws Exception;
    }

    public static interface ThrowingSupplier
    {

        public abstract Object get()
            throws Exception;
    }


    private FunctionalUtils()
    {
    }
}
