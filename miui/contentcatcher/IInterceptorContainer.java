// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher;

import com.miui.internal.contentcatcher.IInterceptor;

public interface IInterceptorContainer
{

    public abstract IInterceptor getInterceptor();

    public abstract void setInterceptor(IInterceptor iinterceptor);
}
