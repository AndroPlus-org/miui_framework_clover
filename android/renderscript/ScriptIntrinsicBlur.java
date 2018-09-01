// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Element, RSIllegalArgumentException, RenderScript, 
//            Allocation, Type

public final class ScriptIntrinsicBlur extends ScriptIntrinsic
{

    private ScriptIntrinsicBlur(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicBlur create(RenderScript renderscript, Element element)
    {
        if(!element.isCompatible(Element.U8_4(renderscript)) && element.isCompatible(Element.U8(renderscript)) ^ true)
        {
            throw new RSIllegalArgumentException("Unsupported element type.");
        } else
        {
            renderscript = new ScriptIntrinsicBlur(renderscript.nScriptIntrinsicCreate(5, element.getID(renderscript)), renderscript);
            renderscript.setRadius(5F);
            return renderscript;
        }
    }

    public void forEach(Allocation allocation)
    {
        if(allocation.getType().getY() == 0)
        {
            throw new RSIllegalArgumentException("Output is a 1D Allocation");
        } else
        {
            forEach(0, (Allocation)null, allocation, null);
            return;
        }
    }

    public void forEach(Allocation allocation, Script.LaunchOptions launchoptions)
    {
        if(allocation.getType().getY() == 0)
        {
            throw new RSIllegalArgumentException("Output is a 1D Allocation");
        } else
        {
            forEach(0, (Allocation)null, allocation, null, launchoptions);
            return;
        }
    }

    public Script.FieldID getFieldID_Input()
    {
        return createFieldID(1, null);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 2, null, null);
    }

    public void setInput(Allocation allocation)
    {
        if(allocation.getType().getY() == 0)
        {
            throw new RSIllegalArgumentException("Input set to a 1D Allocation");
        } else
        {
            mInput = allocation;
            setVar(1, allocation);
            return;
        }
    }

    public void setRadius(float f)
    {
        if(f <= 0.0F || f > 25F)
        {
            throw new RSIllegalArgumentException("Radius out of range (0 < r <= 25).");
        } else
        {
            setVar(0, f);
            return;
        }
    }

    private Allocation mInput;
    private final float mValues[] = new float[9];
}
