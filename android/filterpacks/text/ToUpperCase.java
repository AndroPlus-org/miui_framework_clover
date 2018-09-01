// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.text;

import android.filterfw.core.*;
import android.filterfw.format.ObjectFormat;
import java.util.Locale;

public class ToUpperCase extends Filter
{

    public ToUpperCase(String s)
    {
        super(s);
    }

    public void process(FilterContext filtercontext)
    {
        String s = (String)pullInput("mixedcase").getObjectValue();
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        filtercontext.setObjectValue(s.toUpperCase(Locale.getDefault()));
        pushOutput("uppercase", filtercontext);
    }

    public void setupPorts()
    {
        mOutputFormat = ObjectFormat.fromClass(java/lang/String, 1);
        addMaskedInputPort("mixedcase", mOutputFormat);
        addOutputPort("uppercase", mOutputFormat);
    }

    private FrameFormat mOutputFormat;
}
