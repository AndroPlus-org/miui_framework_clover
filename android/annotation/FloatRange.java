// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.annotation;

import java.lang.annotation.Annotation;

public interface FloatRange
    extends Annotation
{

    public abstract double from();

    public abstract boolean fromInclusive();

    public abstract double to();

    public abstract boolean toInclusive();
}
