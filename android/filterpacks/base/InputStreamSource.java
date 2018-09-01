// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;
import android.filterfw.format.PrimitiveFormat;
import java.io.*;
import java.nio.ByteBuffer;

public class InputStreamSource extends Filter
{

    public InputStreamSource(String s)
    {
        super(s);
        mOutputFormat = null;
    }

    public void process(FilterContext filtercontext)
    {
        int i = 0;
        Object obj;
        byte abyte0[];
        obj = JVM INSTR new #31  <Class ByteArrayOutputStream>;
        ((ByteArrayOutputStream) (obj)).ByteArrayOutputStream();
        abyte0 = new byte[1024];
_L1:
        int j = mInputStream.read(abyte0);
label0:
        {
            if(j <= 0)
                break label0;
            try
            {
                ((ByteArrayOutputStream) (obj)).write(abyte0, 0, j);
            }
            // Misplaced declaration of an exception variable
            catch(FilterContext filtercontext)
            {
                throw new RuntimeException((new StringBuilder()).append("InputStreamSource: Could not read stream: ").append(filtercontext.getMessage()).append("!").toString());
            }
            i += j;
        }
          goto _L1
        obj = ByteBuffer.wrap(((ByteArrayOutputStream) (obj)).toByteArray());
        mOutputFormat.setDimensions(i);
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        filtercontext.setData(((ByteBuffer) (obj)));
        pushOutput("data", filtercontext);
        filtercontext.release();
        closeOutputPort("data");
        return;
    }

    public void setupPorts()
    {
        int i = FrameFormat.readTargetString(mTarget);
        if(mOutputFormat == null)
            mOutputFormat = PrimitiveFormat.createByteFormat(i);
        addOutputPort("data", mOutputFormat);
    }

    private InputStream mInputStream;
    private MutableFrameFormat mOutputFormat;
    private String mTarget;
}
