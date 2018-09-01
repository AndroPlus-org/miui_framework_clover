// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.dispatch;

import java.lang.reflect.Method;

public interface Dispatchable
{

    public abstract Object dispatch(Method method, Object aobj[])
        throws Throwable;
}
