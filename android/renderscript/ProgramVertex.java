// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            Program, RenderScript, Element, RSIllegalArgumentException, 
//            Type

public class ProgramVertex extends Program
{
    public static class Builder extends Program.BaseProgramBuilder
    {

        public Builder addInput(Element element)
            throws IllegalStateException
        {
            if(mInputCount >= 8)
                throw new RSIllegalArgumentException("Max input count exceeded.");
            if(element.isComplex())
            {
                throw new RSIllegalArgumentException("Complex elements not allowed.");
            } else
            {
                Element aelement[] = mInputs;
                int i = mInputCount;
                mInputCount = i + 1;
                aelement[i] = element;
                return this;
            }
        }

        public ProgramVertex create()
        {
            mRS.validate();
            long al[] = new long[(mInputCount + mOutputCount + mConstantCount + mTextureCount) * 2];
            String as[] = new String[mTextureCount];
            int i = 0;
            for(int k = 0; k < mInputCount; k++)
            {
                int k1 = i + 1;
                al[i] = Program.ProgramParam.INPUT.mID;
                i = k1 + 1;
                al[k1] = mInputs[k].getID(mRS);
            }

            for(int l = 0; l < mOutputCount; l++)
            {
                int l1 = i + 1;
                al[i] = Program.ProgramParam.OUTPUT.mID;
                i = l1 + 1;
                al[l1] = mOutputs[l].getID(mRS);
            }

            for(int i1 = 0; i1 < mConstantCount; i1++)
            {
                int i2 = i + 1;
                al[i] = Program.ProgramParam.CONSTANT.mID;
                i = i2 + 1;
                al[i2] = mConstants[i1].getID(mRS);
            }

            boolean flag = false;
            int j1 = i;
            for(int j = ((flag) ? 1 : 0); j < mTextureCount; j++)
            {
                int j2 = j1 + 1;
                al[j1] = Program.ProgramParam.TEXTURE_TYPE.mID;
                j1 = j2 + 1;
                al[j2] = mTextureTypes[j].mID;
                as[j] = mTextureNames[j];
            }

            ProgramVertex programvertex = new ProgramVertex(mRS.nProgramVertexCreate(mShader, as, al), mRS);
            initProgram(programvertex);
            return programvertex;
        }

        public Builder(RenderScript renderscript)
        {
            super(renderscript);
        }
    }


    ProgramVertex(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }

    public Element getInput(int i)
    {
        if(i < 0 || i >= mInputs.length)
            throw new IllegalArgumentException("Slot ID out of range.");
        else
            return mInputs[i];
    }

    public int getInputCount()
    {
        int i;
        if(mInputs != null)
            i = mInputs.length;
        else
            i = 0;
        return i;
    }
}
