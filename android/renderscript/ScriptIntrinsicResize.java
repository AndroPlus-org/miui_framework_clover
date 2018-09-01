// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, RenderScript, RSIllegalArgumentException, Allocation, 
//            Element

public final class ScriptIntrinsicResize extends ScriptIntrinsic
{

    private ScriptIntrinsicResize(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicResize create(RenderScript renderscript)
    {
        return new ScriptIntrinsicResize(renderscript.nScriptIntrinsicCreate(12, 0L), renderscript);
    }

    public void forEach_bicubic(Allocation allocation)
    {
        if(allocation == mInput)
        {
            throw new RSIllegalArgumentException("Output cannot be same as Input.");
        } else
        {
            forEach_bicubic(allocation, null);
            return;
        }
    }

    public void forEach_bicubic(Allocation allocation, Script.LaunchOptions launchoptions)
    {
        forEach(0, (Allocation)null, allocation, null, launchoptions);
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(0, null);
    }

    public Script.KernelID getKernelID_bicubic()
    {
        return createKernelID(0, 2, null, null);
    }

    public void setInput(Allocation allocation)
    {
        Element element = allocation.getElement();
        if(!element.isCompatible(Element.U8(mRS)) && element.isCompatible(Element.U8_2(mRS)) ^ true && element.isCompatible(Element.U8_3(mRS)) ^ true && element.isCompatible(Element.U8_4(mRS)) ^ true && element.isCompatible(Element.F32(mRS)) ^ true && element.isCompatible(Element.F32_2(mRS)) ^ true && element.isCompatible(Element.F32_3(mRS)) ^ true && element.isCompatible(Element.F32_4(mRS)) ^ true)
        {
            throw new RSIllegalArgumentException("Unsupported element type.");
        } else
        {
            mInput = allocation;
            setVar(0, allocation);
            return;
        }
    }

    private Allocation mInput;
}
