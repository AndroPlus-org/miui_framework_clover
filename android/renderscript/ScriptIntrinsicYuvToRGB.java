// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Element, RenderScript, Allocation

public final class ScriptIntrinsicYuvToRGB extends ScriptIntrinsic
{

    ScriptIntrinsicYuvToRGB(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicYuvToRGB create(RenderScript renderscript, Element element)
    {
        return new ScriptIntrinsicYuvToRGB(renderscript.nScriptIntrinsicCreate(6, element.getID(renderscript)), renderscript);
    }

    public void forEach(Allocation allocation)
    {
        forEach(0, (Allocation)null, allocation, null);
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(0, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 2, null, null);
    }

    public void setInput(Allocation allocation)
    {
        mInput = allocation;
        setVar(0, allocation);
    }

    private Allocation mInput;
}
