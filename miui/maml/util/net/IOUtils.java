// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util.net;

import java.io.*;

public final class IOUtils
{

    public IOUtils()
    {
    }

    public static void closeQuietly(InputStream inputstream)
    {
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_8;
        inputstream.close();
_L2:
        return;
        inputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void closeQuietly(OutputStream outputstream)
    {
        if(outputstream == null)
            break MISSING_BLOCK_LABEL_12;
        try
        {
            outputstream.flush();
        }
        catch(IOException ioexception) { }
        outputstream.close();
_L2:
        return;
        outputstream;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void closeQuietly(Reader reader)
    {
        if(reader == null)
            break MISSING_BLOCK_LABEL_8;
        reader.close();
_L2:
        return;
        reader;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void closeQuietly(Writer writer)
    {
        if(writer == null)
            break MISSING_BLOCK_LABEL_8;
        writer.close();
_L2:
        return;
        writer;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
