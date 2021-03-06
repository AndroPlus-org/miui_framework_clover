// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Element, RSIllegalArgumentException, RenderScript, 
//            Allocation, FieldPacker

public final class ScriptIntrinsicConvolve3x3 extends ScriptIntrinsic
{

    private ScriptIntrinsicConvolve3x3(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicConvolve3x3 create(RenderScript renderscript, Element element)
    {
        if(!element.isCompatible(Element.U8(renderscript)) && element.isCompatible(Element.U8_2(renderscript)) ^ true && element.isCompatible(Element.U8_3(renderscript)) ^ true && element.isCompatible(Element.U8_4(renderscript)) ^ true && element.isCompatible(Element.F32(renderscript)) ^ true && element.isCompatible(Element.F32_2(renderscript)) ^ true && element.isCompatible(Element.F32_3(renderscript)) ^ true && element.isCompatible(Element.F32_4(renderscript)) ^ true)
        {
            throw new RSIllegalArgumentException("Unsupported element type.");
        } else
        {
            renderscript = new ScriptIntrinsicConvolve3x3(renderscript.nScriptIntrinsicCreate(1, element.getID(renderscript)), renderscript);
            renderscript.setCoefficients(new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F
            });
            return renderscript;
        }
    }

    public void forEach(Allocation allocation)
    {
        forEach(0, (Allocation)null, allocation, null);
    }

    public void forEach(Allocation allocation, Script.LaunchOptions launchoptions)
    {
        forEach(0, (Allocation)null, allocation, null, launchoptions);
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(1, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 2, null, null);
    }

    public void setCoefficients(float af[])
    {
        FieldPacker fieldpacker = new FieldPacker(36);
        for(int i = 0; i < mValues.length; i++)
        {
            mValues[i] = af[i];
            fieldpacker.addF32(mValues[i]);
        }

        setVar(0, fieldpacker);
    }

    public void setInput(Allocation allocation)
    {
        mInput = allocation;
        setVar(1, allocation);
    }

    private Allocation mInput;
    private final float mValues[] = new float[9];
}
