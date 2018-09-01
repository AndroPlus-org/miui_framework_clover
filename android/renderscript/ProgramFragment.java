// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            Program, RenderScript, Element, Type

public class ProgramFragment extends Program
{
    public static class Builder extends Program.BaseProgramBuilder
    {

        public ProgramFragment create()
        {
            mRS.validate();
            long al[] = new long[(mInputCount + mOutputCount + mConstantCount + mTextureCount) * 2];
            String as[] = new String[mTextureCount];
            int i = 0;
            for(int j = 0; j < mInputCount; j++)
            {
                int j1 = i + 1;
                al[i] = Program.ProgramParam.INPUT.mID;
                i = j1 + 1;
                al[j1] = mInputs[j].getID(mRS);
            }

            for(int k = 0; k < mOutputCount; k++)
            {
                int k1 = i + 1;
                al[i] = Program.ProgramParam.OUTPUT.mID;
                i = k1 + 1;
                al[k1] = mOutputs[k].getID(mRS);
            }

            for(int l = 0; l < mConstantCount; l++)
            {
                int l1 = i + 1;
                al[i] = Program.ProgramParam.CONSTANT.mID;
                i = l1 + 1;
                al[l1] = mConstants[l].getID(mRS);
            }

            for(int i1 = 0; i1 < mTextureCount; i1++)
            {
                int i2 = i + 1;
                al[i] = Program.ProgramParam.TEXTURE_TYPE.mID;
                i = i2 + 1;
                al[i2] = mTextureTypes[i1].mID;
                as[i1] = mTextureNames[i1];
            }

            ProgramFragment programfragment = new ProgramFragment(mRS.nProgramFragmentCreate(mShader, as, al), mRS);
            initProgram(programfragment);
            return programfragment;
        }

        public Builder(RenderScript renderscript)
        {
            super(renderscript);
        }
    }


    ProgramFragment(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }
}
