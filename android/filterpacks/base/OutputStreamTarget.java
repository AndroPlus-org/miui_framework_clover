// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class OutputStreamTarget extends Filter
{

    public OutputStreamTarget(String s)
    {
        super(s);
    }

    public void process(FilterContext filtercontext)
    {
        filtercontext = pullInput("data");
        if(filtercontext.getFormat().getObjectClass() == java/lang/String)
            filtercontext = ByteBuffer.wrap(((String)filtercontext.getObjectValue()).getBytes());
        else
            filtercontext = filtercontext.getData();
        try
        {
            mOutputStream.write(filtercontext.array(), 0, filtercontext.limit());
            mOutputStream.flush();
            return;
        }
        // Misplaced declaration of an exception variable
        catch(FilterContext filtercontext)
        {
            throw new RuntimeException((new StringBuilder()).append("OutputStreamTarget: Could not write to stream: ").append(filtercontext.getMessage()).append("!").toString());
        }
    }

    public void setupPorts()
    {
        addInputPort("data");
    }

    private OutputStream mOutputStream;
}
