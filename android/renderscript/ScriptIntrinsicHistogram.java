// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Element, RSIllegalArgumentException, RenderScript, 
//            Allocation, Type, FieldPacker

public final class ScriptIntrinsicHistogram extends ScriptIntrinsic
{

    private ScriptIntrinsicHistogram(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicHistogram create(RenderScript renderscript, Element element)
    {
        if(!element.isCompatible(Element.U8_4(renderscript)) && element.isCompatible(Element.U8_3(renderscript)) ^ true && element.isCompatible(Element.U8_2(renderscript)) ^ true && element.isCompatible(Element.U8(renderscript)) ^ true)
            throw new RSIllegalArgumentException("Unsupported element type.");
        else
            return new ScriptIntrinsicHistogram(renderscript.nScriptIntrinsicCreate(9, element.getID(renderscript)), renderscript);
    }

    public void forEach(Allocation allocation)
    {
        forEach(allocation, null);
    }

    public void forEach(Allocation allocation, Script.LaunchOptions launchoptions)
    {
        if(allocation.getType().getElement().getVectorSize() < mOut.getType().getElement().getVectorSize())
            throw new RSIllegalArgumentException("Input vector size must be >= output vector size.");
        if(!allocation.getType().getElement().isCompatible(Element.U8(mRS)) && allocation.getType().getElement().isCompatible(Element.U8_2(mRS)) ^ true && allocation.getType().getElement().isCompatible(Element.U8_3(mRS)) ^ true && allocation.getType().getElement().isCompatible(Element.U8_4(mRS)) ^ true)
        {
            throw new RSIllegalArgumentException("Input type must be U8, U8_1, U8_2 or U8_4.");
        } else
        {
            forEach(0, allocation, null, null, launchoptions);
            return;
        }
    }

    public void forEach_Dot(Allocation allocation)
    {
        forEach_Dot(allocation, null);
    }

    public void forEach_Dot(Allocation allocation, Script.LaunchOptions launchoptions)
    {
        if(mOut.getType().getElement().getVectorSize() != 1)
            throw new RSIllegalArgumentException("Output vector size must be one.");
        if(!allocation.getType().getElement().isCompatible(Element.U8(mRS)) && allocation.getType().getElement().isCompatible(Element.U8_2(mRS)) ^ true && allocation.getType().getElement().isCompatible(Element.U8_3(mRS)) ^ true && allocation.getType().getElement().isCompatible(Element.U8_4(mRS)) ^ true)
        {
            throw new RSIllegalArgumentException("Input type must be U8, U8_1, U8_2 or U8_4.");
        } else
        {
            forEach(1, allocation, null, null, launchoptions);
            return;
        }
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(1, null);
    }

    public Script.KernelID getKernelID_Separate()
    {
        return createKernelID(0, 3, null, null);
    }

    public void setDotCoefficients(float f, float f1, float f2, float f3)
    {
        while(f < 0.0F || f1 < 0.0F || f2 < 0.0F || f3 < 0.0F) 
            throw new RSIllegalArgumentException("Coefficient may not be negative.");
        if(f + f1 + f2 + f3 > 1.0F)
        {
            throw new RSIllegalArgumentException("Sum of coefficients must be 1.0 or less.");
        } else
        {
            FieldPacker fieldpacker = new FieldPacker(16);
            fieldpacker.addF32(f);
            fieldpacker.addF32(f1);
            fieldpacker.addF32(f2);
            fieldpacker.addF32(f3);
            setVar(0, fieldpacker);
            return;
        }
    }

    public void setOutput(Allocation allocation)
    {
        mOut = allocation;
        if(mOut.getType().getElement() != Element.U32(mRS) && mOut.getType().getElement() != Element.U32_2(mRS) && mOut.getType().getElement() != Element.U32_3(mRS) && mOut.getType().getElement() != Element.U32_4(mRS) && mOut.getType().getElement() != Element.I32(mRS) && mOut.getType().getElement() != Element.I32_2(mRS) && mOut.getType().getElement() != Element.I32_3(mRS) && mOut.getType().getElement() != Element.I32_4(mRS))
            throw new RSIllegalArgumentException("Output type must be U32 or I32.");
        while(mOut.getType().getX() != 256 || mOut.getType().getY() != 0 || mOut.getType().hasMipmaps() || mOut.getType().getYuv() != 0) 
            throw new RSIllegalArgumentException("Output must be 1D, 256 elements.");
        setVar(1, allocation);
    }

    private Allocation mOut;
}
