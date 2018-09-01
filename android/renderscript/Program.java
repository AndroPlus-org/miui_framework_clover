// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.res.Resources;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.*;

// Referenced classes of package android.renderscript:
//            BaseObj, Allocation, Type, RenderScript, 
//            Sampler, Element, RSIllegalArgumentException

public class Program extends BaseObj
{
    public static class BaseProgramBuilder
    {

        public BaseProgramBuilder addConstant(Type type)
            throws IllegalStateException
        {
            if(mConstantCount >= 8)
                throw new RSIllegalArgumentException("Max input count exceeded.");
            if(type.getElement().isComplex())
            {
                throw new RSIllegalArgumentException("Complex elements not allowed.");
            } else
            {
                mConstants[mConstantCount] = type;
                mConstantCount = mConstantCount + 1;
                return this;
            }
        }

        public BaseProgramBuilder addTexture(TextureType texturetype)
            throws IllegalArgumentException
        {
            addTexture(texturetype, (new StringBuilder()).append("Tex").append(mTextureCount).toString());
            return this;
        }

        public BaseProgramBuilder addTexture(TextureType texturetype, String s)
            throws IllegalArgumentException
        {
            if(mTextureCount >= 8)
            {
                throw new IllegalArgumentException("Max texture count exceeded.");
            } else
            {
                mTextureTypes[mTextureCount] = texturetype;
                mTextureNames[mTextureCount] = s;
                mTextureCount = mTextureCount + 1;
                return this;
            }
        }

        public int getCurrentConstantIndex()
        {
            return mConstantCount - 1;
        }

        public int getCurrentTextureIndex()
        {
            return mTextureCount - 1;
        }

        protected void initProgram(Program program)
        {
            program.mInputs = new Element[mInputCount];
            System.arraycopy(mInputs, 0, program.mInputs, 0, mInputCount);
            program.mOutputs = new Element[mOutputCount];
            System.arraycopy(mOutputs, 0, program.mOutputs, 0, mOutputCount);
            program.mConstants = new Type[mConstantCount];
            System.arraycopy(mConstants, 0, program.mConstants, 0, mConstantCount);
            program.mTextureCount = mTextureCount;
            program.mTextures = new TextureType[mTextureCount];
            System.arraycopy(mTextureTypes, 0, program.mTextures, 0, mTextureCount);
            program.mTextureNames = new String[mTextureCount];
            System.arraycopy(mTextureNames, 0, program.mTextureNames, 0, mTextureCount);
        }

        public BaseProgramBuilder setShader(Resources resources, int i)
        {
            InputStream inputstream = resources.openRawResource(i);
            resources = new byte[1024];
            i = 0;
_L1:
            int j = resources.length - i;
            int k;
            byte abyte0[];
            k = j;
            abyte0 = resources;
            if(j != 0)
                break MISSING_BLOCK_LABEL_61;
            byte abyte1[];
            abyte1 = new byte[resources.length * 2];
            System.arraycopy(resources, 0, abyte1, 0, resources.length);
            abyte0 = abyte1;
            k = abyte1.length - i;
            k = inputstream.read(abyte0, i, k);
            if(k <= 0)
            {
                try
                {
                    inputstream.close();
                }
                // Misplaced declaration of an exception variable
                catch(Resources resources)
                {
                    throw new android.content.res.Resources.NotFoundException();
                }
                try
                {
                    resources = JVM INSTR new #57  <Class String>;
                    resources.String(abyte0, 0, i, "UTF-8");
                    mShader = resources;
                }
                // Misplaced declaration of an exception variable
                catch(Resources resources)
                {
                    Log.e("RenderScript shader creation", "Could not decode shader string");
                }
                return this;
            }
            i += k;
            resources = abyte0;
              goto _L1
            resources;
            inputstream.close();
            throw resources;
        }

        public BaseProgramBuilder setShader(String s)
        {
            mShader = s;
            return this;
        }

        int mConstantCount;
        Type mConstants[];
        int mInputCount;
        Element mInputs[];
        int mOutputCount;
        Element mOutputs[];
        RenderScript mRS;
        String mShader;
        int mTextureCount;
        String mTextureNames[];
        TextureType mTextureTypes[];
        Type mTextures[];

        protected BaseProgramBuilder(RenderScript renderscript)
        {
            mRS = renderscript;
            mInputs = new Element[8];
            mOutputs = new Element[8];
            mConstants = new Type[8];
            mInputCount = 0;
            mOutputCount = 0;
            mConstantCount = 0;
            mTextureCount = 0;
            mTextureTypes = new TextureType[8];
            mTextureNames = new String[8];
        }
    }

    static final class ProgramParam extends Enum
    {

        public static ProgramParam valueOf(String s)
        {
            return (ProgramParam)Enum.valueOf(android/renderscript/Program$ProgramParam, s);
        }

        public static ProgramParam[] values()
        {
            return $VALUES;
        }

        private static final ProgramParam $VALUES[];
        public static final ProgramParam CONSTANT;
        public static final ProgramParam INPUT;
        public static final ProgramParam OUTPUT;
        public static final ProgramParam TEXTURE_TYPE;
        int mID;

        static 
        {
            INPUT = new ProgramParam("INPUT", 0, 0);
            OUTPUT = new ProgramParam("OUTPUT", 1, 1);
            CONSTANT = new ProgramParam("CONSTANT", 2, 2);
            TEXTURE_TYPE = new ProgramParam("TEXTURE_TYPE", 3, 3);
            $VALUES = (new ProgramParam[] {
                INPUT, OUTPUT, CONSTANT, TEXTURE_TYPE
            });
        }

        private ProgramParam(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static final class TextureType extends Enum
    {

        public static TextureType valueOf(String s)
        {
            return (TextureType)Enum.valueOf(android/renderscript/Program$TextureType, s);
        }

        public static TextureType[] values()
        {
            return $VALUES;
        }

        private static final TextureType $VALUES[];
        public static final TextureType TEXTURE_2D;
        public static final TextureType TEXTURE_CUBE;
        int mID;

        static 
        {
            TEXTURE_2D = new TextureType("TEXTURE_2D", 0, 0);
            TEXTURE_CUBE = new TextureType("TEXTURE_CUBE", 1, 1);
            $VALUES = (new TextureType[] {
                TEXTURE_2D, TEXTURE_CUBE
            });
        }

        private TextureType(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }


    Program(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        guard.open("destroy");
    }

    public void bindConstants(Allocation allocation, int i)
    {
        if(i < 0 || i >= mConstants.length)
            throw new IllegalArgumentException("Slot ID out of range.");
        if(allocation != null && allocation.getType().getID(mRS) != mConstants[i].getID(mRS))
            throw new IllegalArgumentException("Allocation type does not match slot type.");
        long l;
        if(allocation != null)
            l = allocation.getID(mRS);
        else
            l = 0L;
        mRS.nProgramBindConstants(getID(mRS), i, l);
    }

    public void bindSampler(Sampler sampler, int i)
        throws IllegalArgumentException
    {
        mRS.validate();
        if(i < 0 || i >= mTextureCount)
            throw new IllegalArgumentException("Slot ID out of range.");
        long l;
        if(sampler != null)
            l = sampler.getID(mRS);
        else
            l = 0L;
        mRS.nProgramBindSampler(getID(mRS), i, l);
    }

    public void bindTexture(Allocation allocation, int i)
        throws IllegalArgumentException
    {
        mRS.validate();
        if(i < 0 || i >= mTextureCount)
            throw new IllegalArgumentException("Slot ID out of range.");
        if(allocation != null && allocation.getType().hasFaces() && mTextures[i] != TextureType.TEXTURE_CUBE)
            throw new IllegalArgumentException("Cannot bind cubemap to 2d texture slot");
        long l;
        if(allocation != null)
            l = allocation.getID(mRS);
        else
            l = 0L;
        mRS.nProgramBindTexture(getID(mRS), i, l);
    }

    public Type getConstant(int i)
    {
        if(i < 0 || i >= mConstants.length)
            throw new IllegalArgumentException("Slot ID out of range.");
        else
            return mConstants[i];
    }

    public int getConstantCount()
    {
        int i;
        if(mConstants != null)
            i = mConstants.length;
        else
            i = 0;
        return i;
    }

    public int getTextureCount()
    {
        return mTextureCount;
    }

    public String getTextureName(int i)
    {
        if(i < 0 || i >= mTextureCount)
            throw new IllegalArgumentException("Slot ID out of range.");
        else
            return mTextureNames[i];
    }

    public TextureType getTextureType(int i)
    {
        if(i < 0 || i >= mTextureCount)
            throw new IllegalArgumentException("Slot ID out of range.");
        else
            return mTextures[i];
    }

    static final int MAX_CONSTANT = 8;
    static final int MAX_INPUT = 8;
    static final int MAX_OUTPUT = 8;
    static final int MAX_TEXTURE = 8;
    Type mConstants[];
    Element mInputs[];
    Element mOutputs[];
    String mShader;
    int mTextureCount;
    String mTextureNames[];
    TextureType mTextures[];
}
