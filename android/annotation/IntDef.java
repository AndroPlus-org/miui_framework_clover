// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.annotation;

import java.lang.annotation.Annotation;

public interface IntDef
    extends Annotation
{

    public abstract boolean flag();

    public abstract String[] prefix();

    public abstract long[] value();
}
