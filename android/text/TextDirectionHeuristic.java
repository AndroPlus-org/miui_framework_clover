// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


public interface TextDirectionHeuristic
{

    public abstract boolean isRtl(CharSequence charsequence, int i, int j);

    public abstract boolean isRtl(char ac[], int i, int j);
}
