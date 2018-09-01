// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.text;

import android.filterfw.core.*;
import android.filterfw.format.ObjectFormat;
import android.util.Log;

public class StringLogger extends Filter
{

    public StringLogger(String s)
    {
        super(s);
    }

    public void process(FilterContext filtercontext)
    {
        Log.i("StringLogger", pullInput("string").getObjectValue().toString());
    }

    public void setupPorts()
    {
        addMaskedInputPort("string", ObjectFormat.fromClass(java/lang/Object, 1));
    }
}
