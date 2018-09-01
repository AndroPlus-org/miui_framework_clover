// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Element, RenderScript, RSIllegalArgumentException, 
//            Allocation, Type

public final class ScriptIntrinsic3DLUT extends ScriptIntrinsic
{

    private ScriptIntrinsic3DLUT(long l, RenderScript renderscript, Element element)
    {
        super(l, renderscript);
        mElement = element;
    }

    public static ScriptIntrinsic3DLUT create(RenderScript renderscript, Element element)
    {
        long l = renderscript.nScriptIntrinsicCreate(8, element.getID(renderscript));
        if(!element.isCompatible(Element.U8_4(renderscript)))
            throw new RSIllegalArgumentException("Element must be compatible with uchar4.");
        else
            return new ScriptIntrinsic3DLUT(l, renderscript, element);
    }

    public void forEach(Allocation allocation, Allocation allocation1)
    {
        forEach(allocation, allocation1, null);
    }

    public void forEach(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        forEach(0, allocation, allocation1, null, launchoptions);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 3, null, null);
    }

    public void setLUT(Allocation allocation)
    {
        Type type = allocation.getType();
        if(type.getZ() == 0)
            throw new RSIllegalArgumentException("LUT must be 3d.");
        if(!type.getElement().isCompatible(mElement))
        {
            throw new RSIllegalArgumentException("LUT element type must match.");
        } else
        {
            mLUT = allocation;
            setVar(0, mLUT);
            return;
        }
    }

    private Element mElement;
    private Allocation mLUT;
}
