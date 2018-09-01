// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript

public class ProgramRaster extends BaseObj
{
    public static class Builder
    {

        public ProgramRaster create()
        {
            mRS.validate();
            ProgramRaster programraster = new ProgramRaster(mRS.nProgramRasterCreate(mPointSprite, mCullMode.mID), mRS);
            programraster.mPointSprite = mPointSprite;
            programraster.mCullMode = mCullMode;
            return programraster;
        }

        public Builder setCullMode(CullMode cullmode)
        {
            mCullMode = cullmode;
            return this;
        }

        public Builder setPointSpriteEnabled(boolean flag)
        {
            mPointSprite = flag;
            return this;
        }

        CullMode mCullMode;
        boolean mPointSprite;
        RenderScript mRS;

        public Builder(RenderScript renderscript)
        {
            mRS = renderscript;
            mPointSprite = false;
            mCullMode = CullMode.BACK;
        }
    }

    public static final class CullMode extends Enum
    {

        public static CullMode valueOf(String s)
        {
            return (CullMode)Enum.valueOf(android/renderscript/ProgramRaster$CullMode, s);
        }

        public static CullMode[] values()
        {
            return $VALUES;
        }

        private static final CullMode $VALUES[];
        public static final CullMode BACK;
        public static final CullMode FRONT;
        public static final CullMode NONE;
        int mID;

        static 
        {
            BACK = new CullMode("BACK", 0, 0);
            FRONT = new CullMode("FRONT", 1, 1);
            NONE = new CullMode("NONE", 2, 2);
            $VALUES = (new CullMode[] {
                BACK, FRONT, NONE
            });
        }

        private CullMode(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }


    ProgramRaster(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        mPointSprite = false;
        mCullMode = CullMode.BACK;
    }

    public static ProgramRaster CULL_BACK(RenderScript renderscript)
    {
        if(renderscript.mProgramRaster_CULL_BACK == null)
        {
            Builder builder = new Builder(renderscript);
            builder.setCullMode(CullMode.BACK);
            renderscript.mProgramRaster_CULL_BACK = builder.create();
        }
        return renderscript.mProgramRaster_CULL_BACK;
    }

    public static ProgramRaster CULL_FRONT(RenderScript renderscript)
    {
        if(renderscript.mProgramRaster_CULL_FRONT == null)
        {
            Builder builder = new Builder(renderscript);
            builder.setCullMode(CullMode.FRONT);
            renderscript.mProgramRaster_CULL_FRONT = builder.create();
        }
        return renderscript.mProgramRaster_CULL_FRONT;
    }

    public static ProgramRaster CULL_NONE(RenderScript renderscript)
    {
        if(renderscript.mProgramRaster_CULL_NONE == null)
        {
            Builder builder = new Builder(renderscript);
            builder.setCullMode(CullMode.NONE);
            renderscript.mProgramRaster_CULL_NONE = builder.create();
        }
        return renderscript.mProgramRaster_CULL_NONE;
    }

    public CullMode getCullMode()
    {
        return mCullMode;
    }

    public boolean isPointSpriteEnabled()
    {
        return mPointSprite;
    }

    CullMode mCullMode;
    boolean mPointSprite;
}
