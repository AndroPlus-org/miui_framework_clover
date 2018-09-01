// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.res.Resources;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package android.renderscript:
//            Script, RSRuntimeException, RenderScript

public class ScriptC extends Script
{

    protected ScriptC(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    protected ScriptC(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    protected ScriptC(RenderScript renderscript, Resources resources, int i)
    {
        super(0L, renderscript);
        long l = internalCreate(renderscript, resources, i);
        if(l == 0L)
        {
            throw new RSRuntimeException("Loading of ScriptC script failed.");
        } else
        {
            setID(l);
            return;
        }
    }

    protected ScriptC(RenderScript renderscript, String s, byte abyte0[], byte abyte1[])
    {
        super(0L, renderscript);
        long l;
        if(RenderScript.sPointerSize == 4)
            l = internalStringCreate(renderscript, s, abyte0);
        else
            l = internalStringCreate(renderscript, s, abyte1);
        if(l == 0L)
        {
            throw new RSRuntimeException("Loading of ScriptC script failed.");
        } else
        {
            setID(l);
            return;
        }
    }

    private static long internalCreate(RenderScript renderscript, Resources resources, int i)
    {
        android/renderscript/ScriptC;
        JVM INSTR monitorenter ;
        InputStream inputstream = resources.openRawResource(i);
        byte abyte0[] = new byte[1024];
        int j = 0;
_L2:
        int k = abyte0.length - j;
        int l;
        byte abyte1[];
        l = k;
        abyte1 = abyte0;
        if(k != 0)
            break MISSING_BLOCK_LABEL_73;
        byte abyte2[];
        abyte2 = new byte[abyte0.length * 2];
        System.arraycopy(abyte0, 0, abyte2, 0, abyte0.length);
        abyte1 = abyte2;
        l = abyte2.length - j;
        l = inputstream.read(abyte1, j, l);
        if(l > 0)
            break MISSING_BLOCK_LABEL_118;
        inputstream.close();
        long l1 = renderscript.nScriptCCreate(resources.getResourceEntryName(i), RenderScript.getCachePath(), abyte1, j);
        android/renderscript/ScriptC;
        JVM INSTR monitorexit ;
        return l1;
        j += l;
        abyte0 = abyte1;
        if(true) goto _L2; else goto _L1
_L1:
        renderscript;
        try
        {
            inputstream.close();
            throw renderscript;
        }
        // Misplaced declaration of an exception variable
        catch(RenderScript renderscript) { }
        renderscript = JVM INSTR new #79  <Class android.content.res.Resources$NotFoundException>;
        renderscript.android.content.res.Resources.NotFoundException();
        throw renderscript;
        renderscript;
        android/renderscript/ScriptC;
        JVM INSTR monitorexit ;
        throw renderscript;
    }

    private static long internalStringCreate(RenderScript renderscript, String s, byte abyte0[])
    {
        android/renderscript/ScriptC;
        JVM INSTR monitorenter ;
        long l = renderscript.nScriptCCreate(s, RenderScript.getCachePath(), abyte0, abyte0.length);
        android/renderscript/ScriptC;
        JVM INSTR monitorexit ;
        return l;
        renderscript;
        throw renderscript;
    }

    private static final String TAG = "ScriptC";
}
