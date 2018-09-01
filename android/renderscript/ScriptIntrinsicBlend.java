// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Allocation, Element, RSIllegalArgumentException, 
//            RenderScript

public class ScriptIntrinsicBlend extends ScriptIntrinsic
{

    ScriptIntrinsicBlend(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    private void blend(int i, Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        if(!allocation.getElement().isCompatible(Element.U8_4(mRS)))
            throw new RSIllegalArgumentException("Input is not of expected format.");
        if(!allocation1.getElement().isCompatible(Element.U8_4(mRS)))
        {
            throw new RSIllegalArgumentException("Output is not of expected format.");
        } else
        {
            forEach(i, allocation, allocation1, null, launchoptions);
            return;
        }
    }

    public static ScriptIntrinsicBlend create(RenderScript renderscript, Element element)
    {
        return new ScriptIntrinsicBlend(renderscript.nScriptIntrinsicCreate(7, element.getID(renderscript)), renderscript);
    }

    public void forEachAdd(Allocation allocation, Allocation allocation1)
    {
        forEachAdd(allocation, allocation1, null);
    }

    public void forEachAdd(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(34, allocation, allocation1, launchoptions);
    }

    public void forEachClear(Allocation allocation, Allocation allocation1)
    {
        forEachClear(allocation, allocation1, null);
    }

    public void forEachClear(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(0, allocation, allocation1, launchoptions);
    }

    public void forEachDst(Allocation allocation, Allocation allocation1)
    {
    }

    public void forEachDst(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
    }

    public void forEachDstAtop(Allocation allocation, Allocation allocation1)
    {
        forEachDstAtop(allocation, allocation1, null);
    }

    public void forEachDstAtop(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(10, allocation, allocation1, launchoptions);
    }

    public void forEachDstIn(Allocation allocation, Allocation allocation1)
    {
        forEachDstIn(allocation, allocation1, null);
    }

    public void forEachDstIn(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(6, allocation, allocation1, launchoptions);
    }

    public void forEachDstOut(Allocation allocation, Allocation allocation1)
    {
        forEachDstOut(allocation, allocation1, null);
    }

    public void forEachDstOut(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(8, allocation, allocation1, launchoptions);
    }

    public void forEachDstOver(Allocation allocation, Allocation allocation1)
    {
        forEachDstOver(allocation, allocation1, null);
    }

    public void forEachDstOver(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(4, allocation, allocation1, launchoptions);
    }

    public void forEachMultiply(Allocation allocation, Allocation allocation1)
    {
        forEachMultiply(allocation, allocation1, null);
    }

    public void forEachMultiply(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(14, allocation, allocation1, launchoptions);
    }

    public void forEachSrc(Allocation allocation, Allocation allocation1)
    {
        forEachSrc(allocation, allocation1, null);
    }

    public void forEachSrc(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(1, allocation, allocation1, null);
    }

    public void forEachSrcAtop(Allocation allocation, Allocation allocation1)
    {
        forEachSrcAtop(allocation, allocation1, null);
    }

    public void forEachSrcAtop(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(9, allocation, allocation1, launchoptions);
    }

    public void forEachSrcIn(Allocation allocation, Allocation allocation1)
    {
        forEachSrcIn(allocation, allocation1, null);
    }

    public void forEachSrcIn(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(5, allocation, allocation1, launchoptions);
    }

    public void forEachSrcOut(Allocation allocation, Allocation allocation1)
    {
        forEachSrcOut(allocation, allocation1, null);
    }

    public void forEachSrcOut(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(7, allocation, allocation1, launchoptions);
    }

    public void forEachSrcOver(Allocation allocation, Allocation allocation1)
    {
        forEachSrcOver(allocation, allocation1, null);
    }

    public void forEachSrcOver(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(3, allocation, allocation1, launchoptions);
    }

    public void forEachSubtract(Allocation allocation, Allocation allocation1)
    {
        forEachSubtract(allocation, allocation1, null);
    }

    public void forEachSubtract(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(35, allocation, allocation1, launchoptions);
    }

    public void forEachXor(Allocation allocation, Allocation allocation1)
    {
        forEachXor(allocation, allocation1, null);
    }

    public void forEachXor(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        blend(11, allocation, allocation1, launchoptions);
    }

    public Script.KernelID getKernelIDAdd()
    {
        return createKernelID(34, 3, null, null);
    }

    public Script.KernelID getKernelIDClear()
    {
        return createKernelID(0, 3, null, null);
    }

    public Script.KernelID getKernelIDDst()
    {
        return createKernelID(2, 3, null, null);
    }

    public Script.KernelID getKernelIDDstAtop()
    {
        return createKernelID(10, 3, null, null);
    }

    public Script.KernelID getKernelIDDstIn()
    {
        return createKernelID(6, 3, null, null);
    }

    public Script.KernelID getKernelIDDstOut()
    {
        return createKernelID(8, 3, null, null);
    }

    public Script.KernelID getKernelIDDstOver()
    {
        return createKernelID(4, 3, null, null);
    }

    public Script.KernelID getKernelIDMultiply()
    {
        return createKernelID(14, 3, null, null);
    }

    public Script.KernelID getKernelIDSrc()
    {
        return createKernelID(1, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcAtop()
    {
        return createKernelID(9, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcIn()
    {
        return createKernelID(5, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcOut()
    {
        return createKernelID(7, 3, null, null);
    }

    public Script.KernelID getKernelIDSrcOver()
    {
        return createKernelID(3, 3, null, null);
    }

    public Script.KernelID getKernelIDSubtract()
    {
        return createKernelID(35, 3, null, null);
    }

    public Script.KernelID getKernelIDXor()
    {
        return createKernelID(11, 3, null, null);
    }
}
