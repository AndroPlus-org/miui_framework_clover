// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.annotation;

import java.lang.annotation.Annotation;

public interface Size
    extends Annotation
{

    public abstract long max();

    public abstract long min();

    public abstract long multiple();

    public abstract long value();
}
