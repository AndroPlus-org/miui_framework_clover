// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ScriptIntrinsic, Matrix4f, Element, Allocation, 
//            RenderScript, RSIllegalArgumentException

public final class ScriptIntrinsicLUT extends ScriptIntrinsic
{

    private ScriptIntrinsicLUT(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        mDirty = true;
        mTables = Allocation.createSized(renderscript, Element.U8(renderscript), 1024);
        for(int i = 0; i < 256; i++)
        {
            mCache[i] = (byte)i;
            mCache[i + 256] = (byte)i;
            mCache[i + 512] = (byte)i;
            mCache[i + 768] = (byte)i;
        }

        setVar(0, mTables);
    }

    public static ScriptIntrinsicLUT create(RenderScript renderscript, Element element)
    {
        return new ScriptIntrinsicLUT(renderscript.nScriptIntrinsicCreate(3, element.getID(renderscript)), renderscript);
    }

    private void validate(int i, int j)
    {
        if(i < 0 || i > 255)
            throw new RSIllegalArgumentException("Index out of range (0-255).");
        if(j < 0 || j > 255)
            throw new RSIllegalArgumentException("Value out of range (0-255).");
        else
            return;
    }

    public void destroy()
    {
        mTables.destroy();
        super.destroy();
    }

    public void forEach(Allocation allocation, Allocation allocation1)
    {
        forEach(allocation, allocation1, null);
    }

    public void forEach(Allocation allocation, Allocation allocation1, Script.LaunchOptions launchoptions)
    {
        if(mDirty)
        {
            mDirty = false;
            mTables.copyFromUnchecked(mCache);
        }
        forEach(0, allocation, allocation1, null, launchoptions);
    }

    public Script.KernelID getKernelID()
    {
        return createKernelID(0, 3, null, null);
    }

    public void setAlpha(int i, int j)
    {
        validate(i, j);
        mCache[i + 768] = (byte)j;
        mDirty = true;
    }

    public void setBlue(int i, int j)
    {
        validate(i, j);
        mCache[i + 512] = (byte)j;
        mDirty = true;
    }

    public void setGreen(int i, int j)
    {
        validate(i, j);
        mCache[i + 256] = (byte)j;
        mDirty = true;
    }

    public void setRed(int i, int j)
    {
        validate(i, j);
        mCache[i] = (byte)j;
        mDirty = true;
    }

    private final byte mCache[] = new byte[1024];
    private boolean mDirty;
    private final Matrix4f mMatrix = new Matrix4f();
    private Allocation mTables;
}
