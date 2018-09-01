// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.lang.annotation.Annotation;

public interface GenerateProgramPort
    extends Annotation
{

    public abstract boolean hasDefault();

    public abstract String name();

    public abstract Class type();

    public abstract String variableName();
}
