// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            Script, RSRuntimeException, RenderScript

public abstract class ScriptIntrinsic extends Script
{

    ScriptIntrinsic(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        if(l == 0L)
            throw new RSRuntimeException("Loading of ScriptIntrinsic failed.");
        else
            return;
    }
}
