// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.annotation;

import java.lang.annotation.Annotation;

public interface BroadcastBehavior
    extends Annotation
{

    public abstract boolean explicitOnly();

    public abstract boolean includeBackground();

    public abstract boolean protectedBroadcast();

    public abstract boolean registeredOnly();
}
