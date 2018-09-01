// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.annotation;

import java.lang.annotation.Annotation;

public interface Dimension
    extends Annotation
{

    public abstract int unit();

    public static final int DP = 0;
    public static final int PX = 1;
    public static final int SP = 2;
}
