// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics.palette;

import java.util.List;

public interface Quantizer
{

    public abstract List getQuantizedColors();

    public abstract void quantize(int ai[], int i, Palette.Filter afilter[]);
}
