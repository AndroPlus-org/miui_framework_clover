// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Matrix4f, Float4, RenderScript, 
//            FieldPacker, Allocation, Element, RSIllegalArgumentException, 
//            Matrix3f

public final class ScriptIntrinsicColorMatrix extends ScriptIntrinsic
{

    private ScriptIntrinsicColorMatrix(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public static ScriptIntrinsicColorMatrix create(RenderScript renderscript)
    {
        return new ScriptIntrinsicColorMatrix(renderscript.nScriptIntrinsicCreate(2, 0L), renderscript);
    }

    public static ScriptIntrinsicColorMatrix create(RenderScript renderscript, Element element)
    {
        return create(renderscript);
    }

    private void setMatrix()
    {
        FieldPacker fieldpacker = new FieldPacker(64);
        fieldpacker.addMatrix(mMatrix);
        setVar(0, fieldpacker);
    }

    public void forEach(Allocation allocation, Allocation allocation1)
    {
        forEach(allocation, allocation1, null);
    }

    public void forEach(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        if(!allocation.getElement().isCompatible(Element.U8(mRS)) && allocation.getElement().isCompatible(Element.U8_2(mRS)) ^ true && allocation.getElement().isCompatible(Element.U8_3(mRS)) ^ true && allocation.getElement().isCompatible(Element.U8_4(mRS)) ^ true && allocation.getElement().isCompatible(Element.F32(mRS)) ^ true && allocation.getElement().isCompatible(Element.F32_2(mRS)) ^ true && allocation.getElement().isCompatible(Element.F32_3(mRS)) ^ true && allocation.getElement().isCompatible(Element.F32_4(mRS)) ^ true)
            throw new RSIllegalArgumentException("Unsupported element type.");
        if(!allocation1.getElement().isCompatible(Element.U8(mRS)) && allocation1.getElement().isCompatible(Element.U8_2(mRS)) ^ true && allocation1.getElement().isCompatible(Element.U8_3(mRS)) ^ true && allocation1.getElement().isCompatible(Element.U8_4(mRS)) ^ true && allocation1.getElement().isCompatible(Element.F32(mRS)) ^ true && allocation1.getElement().isCompatible(Element.F32_2(mRS)) ^ true && allocation1.getElement().isCompatible(Element.F32_3(mRS)) ^ true && allocation1.getElement().isCompatible(Element.F32_4(mRS)) ^ true)
        {
            throw new RSIllegalArgumentException("Unsupported element type.");
        } else
        {
            forEach(0, allocation, allocation1, null, launchoptions);
            return;
        }
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 3, null, null);
    }

    public void setAdd(float f, float f1, float f2, float f3)
    {
        mAdd.x = f;
        mAdd.y = f1;
        mAdd.z = f2;
        mAdd.w = f3;
        FieldPacker fieldpacker = new FieldPacker(16);
        fieldpacker.addF32(mAdd.x);
        fieldpacker.addF32(mAdd.y);
        fieldpacker.addF32(mAdd.z);
        fieldpacker.addF32(mAdd.w);
        setVar(1, fieldpacker);
    }

    public void setAdd(Float4 float4)
    {
        mAdd.x = float4.x;
        mAdd.y = float4.y;
        mAdd.z = float4.z;
        mAdd.w = float4.w;
        FieldPacker fieldpacker = new FieldPacker(16);
        fieldpacker.addF32(float4.x);
        fieldpacker.addF32(float4.y);
        fieldpacker.addF32(float4.z);
        fieldpacker.addF32(float4.w);
        setVar(1, fieldpacker);
    }

    public void setColorMatrix(Matrix3f matrix3f)
    {
        mMatrix.load(matrix3f);
        setMatrix();
    }

    public void setColorMatrix(Matrix4f matrix4f)
    {
        mMatrix.load(matrix4f);
        setMatrix();
    }

    public void setGreyscale()
    {
        mMatrix.loadIdentity();
        mMatrix.set(0, 0, 0.299F);
        mMatrix.set(1, 0, 0.587F);
        mMatrix.set(2, 0, 0.114F);
        mMatrix.set(0, 1, 0.299F);
        mMatrix.set(1, 1, 0.587F);
        mMatrix.set(2, 1, 0.114F);
        mMatrix.set(0, 2, 0.299F);
        mMatrix.set(1, 2, 0.587F);
        mMatrix.set(2, 2, 0.114F);
        setMatrix();
    }

    public void setRGBtoYUV()
    {
        mMatrix.loadIdentity();
        mMatrix.set(0, 0, 0.299F);
        mMatrix.set(1, 0, 0.587F);
        mMatrix.set(2, 0, 0.114F);
        mMatrix.set(0, 1, -0.14713F);
        mMatrix.set(1, 1, -0.28886F);
        mMatrix.set(2, 1, 0.436F);
        mMatrix.set(0, 2, 0.615F);
        mMatrix.set(1, 2, -0.51499F);
        mMatrix.set(2, 2, -0.10001F);
        setMatrix();
    }

    public void setYUVtoRGB()
    {
        mMatrix.loadIdentity();
        mMatrix.set(0, 0, 1.0F);
        mMatrix.set(1, 0, 0.0F);
        mMatrix.set(2, 0, 1.13983F);
        mMatrix.set(0, 1, 1.0F);
        mMatrix.set(1, 1, -0.39465F);
        mMatrix.set(2, 1, -0.5806F);
        mMatrix.set(0, 2, 1.0F);
        mMatrix.set(1, 2, 2.03211F);
        mMatrix.set(2, 2, 0.0F);
        setMatrix();
    }

    private final Float4 mAdd = new Float4();
    private final Matrix4f mMatrix = new Matrix4f();
}
