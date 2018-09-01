// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;


// Referenced classes of package android.renderscript:
//            ProgramFragment, RenderScript, Element, Allocation, 
//            FieldPacker, Float4, Type

public class ProgramFragmentFixedFunction extends ProgramFragment
{
    public static class Builder
    {

        private static int[] _2D_getandroid_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$EnvModeSwitchesValues()
        {
            if(_2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$EnvModeSwitchesValues != null)
                return _2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$EnvModeSwitchesValues;
            int ai[] = new int[EnvMode.values().length];
            try
            {
                ai[EnvMode.DECAL.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[EnvMode.MODULATE.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[EnvMode.REPLACE.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$EnvModeSwitchesValues = ai;
            return ai;
        }

        private static int[] _2D_getandroid_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues()
        {
            if(_2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues != null)
                return _2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues;
            int ai[] = new int[Format.values().length];
            try
            {
                ai[Format.ALPHA.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[Format.LUMINANCE_ALPHA.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[Format.RGB.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Format.RGBA.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues = ai;
            return ai;
        }

        private void buildShaderString()
        {
            int i;
            mShader = "//rs_shader_internal\n";
            mShader = (new StringBuilder()).append(mShader).append("varying lowp vec4 varColor;\n").toString();
            mShader = (new StringBuilder()).append(mShader).append("varying vec2 varTex0;\n").toString();
            mShader = (new StringBuilder()).append(mShader).append("void main() {\n").toString();
            if(mVaryingColorEnable)
                mShader = (new StringBuilder()).append(mShader).append("  lowp vec4 col = varColor;\n").toString();
            else
                mShader = (new StringBuilder()).append(mShader).append("  lowp vec4 col = UNI_Color;\n").toString();
            if(mNumTextures != 0)
                if(mPointSpriteEnable)
                    mShader = (new StringBuilder()).append(mShader).append("  vec2 t0 = gl_PointCoord;\n").toString();
                else
                    mShader = (new StringBuilder()).append(mShader).append("  vec2 t0 = varTex0.xy;\n").toString();
            i = 0;
            if(i < mNumTextures)
            {
                switch(_2D_getandroid_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$EnvModeSwitchesValues()[mSlots[i].env.ordinal()])
                {
                case 3: // '\003'
                    switch(_2D_getandroid_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues()[mSlots[i].format.ordinal()])
                    {
                    case 1: // '\001'
                        mShader = (new StringBuilder()).append(mShader).append("  col.a = texture2D(UNI_Tex0, t0).a;\n").toString();
                        break;

                    case 2: // '\002'
                        mShader = (new StringBuilder()).append(mShader).append("  col.rgba = texture2D(UNI_Tex0, t0).rgba;\n").toString();
                        break;

                    case 3: // '\003'
                        mShader = (new StringBuilder()).append(mShader).append("  col.rgb = texture2D(UNI_Tex0, t0).rgb;\n").toString();
                        break;

                    case 4: // '\004'
                        mShader = (new StringBuilder()).append(mShader).append("  col.rgba = texture2D(UNI_Tex0, t0).rgba;\n").toString();
                        break;
                    }
                    // fall through

                default:
                    if(false)
                        ;
                    break;

                case 2: // '\002'
                    switch(_2D_getandroid_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues()[mSlots[i].format.ordinal()])
                    {
                    case 1: // '\001'
                        mShader = (new StringBuilder()).append(mShader).append("  col.a *= texture2D(UNI_Tex0, t0).a;\n").toString();
                        break;

                    case 2: // '\002'
                        mShader = (new StringBuilder()).append(mShader).append("  col.rgba *= texture2D(UNI_Tex0, t0).rgba;\n").toString();
                        break;

                    case 3: // '\003'
                        mShader = (new StringBuilder()).append(mShader).append("  col.rgb *= texture2D(UNI_Tex0, t0).rgb;\n").toString();
                        break;

                    case 4: // '\004'
                        mShader = (new StringBuilder()).append(mShader).append("  col.rgba *= texture2D(UNI_Tex0, t0).rgba;\n").toString();
                        break;
                    }
                    if(false)
                        ;
                    break;

                case 1: // '\001'
                    mShader = (new StringBuilder()).append(mShader).append("  col = texture2D(UNI_Tex0, t0);\n").toString();
                    break;
                }
                while(true) 
                {
                    i++;
                    break MISSING_BLOCK_LABEL_159;
                }
            }
            mShader = (new StringBuilder()).append(mShader).append("  gl_FragColor = col;\n").toString();
            mShader = (new StringBuilder()).append(mShader).append("}\n").toString();
            return;
        }

        public ProgramFragmentFixedFunction create()
        {
            Object obj = new InternalBuilder(mRS);
            mNumTextures = 0;
            for(int i = 0; i < 2; i++)
                if(mSlots[i] != null)
                    mNumTextures = mNumTextures + 1;

            buildShaderString();
            ((InternalBuilder) (obj)).setShader(mShader);
            Object obj1 = null;
            if(!mVaryingColorEnable)
            {
                obj1 = new Element.Builder(mRS);
                ((Element.Builder) (obj1)).add(Element.F32_4(mRS), "Color");
                obj1 = new Type.Builder(mRS, ((Element.Builder) (obj1)).create());
                ((Type.Builder) (obj1)).setX(1);
                obj1 = ((Type.Builder) (obj1)).create();
                ((InternalBuilder) (obj)).addConstant(((Type) (obj1)));
            }
            for(int j = 0; j < mNumTextures; j++)
                ((InternalBuilder) (obj)).addTexture(Program.TextureType.TEXTURE_2D);

            obj = ((InternalBuilder) (obj)).create();
            obj.mTextureCount = 2;
            if(!mVaryingColorEnable)
            {
                Allocation allocation = Allocation.createTyped(mRS, ((Type) (obj1)));
                obj1 = new FieldPacker(16);
                ((FieldPacker) (obj1)).addF32(new Float4(1.0F, 1.0F, 1.0F, 1.0F));
                allocation.setFromFieldPacker(0, ((FieldPacker) (obj1)));
                ((ProgramFragmentFixedFunction) (obj)).bindConstants(allocation, 0);
            }
            return ((ProgramFragmentFixedFunction) (obj));
        }

        public Builder setPointSpriteTexCoordinateReplacement(boolean flag)
        {
            mPointSpriteEnable = flag;
            return this;
        }

        public Builder setTexture(EnvMode envmode, Format format, int i)
            throws IllegalArgumentException
        {
            if(i < 0 || i >= 2)
            {
                throw new IllegalArgumentException("MAX_TEXTURE exceeded.");
            } else
            {
                mSlots[i] = new Slot(envmode, format);
                return this;
            }
        }

        public Builder setVaryingColor(boolean flag)
        {
            mVaryingColorEnable = flag;
            return this;
        }

        private static final int _2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$EnvModeSwitchesValues[];
        private static final int _2D_android_2D_renderscript_2D_ProgramFragmentFixedFunction$Builder$FormatSwitchesValues[];
        public static final int MAX_TEXTURE = 2;
        int mNumTextures;
        boolean mPointSpriteEnable;
        RenderScript mRS;
        String mShader;
        Slot mSlots[];
        boolean mVaryingColorEnable;

        public Builder(RenderScript renderscript)
        {
            mRS = renderscript;
            mSlots = new Slot[2];
            mPointSpriteEnable = false;
        }
    }

    public static final class Builder.EnvMode extends Enum
    {

        public static Builder.EnvMode valueOf(String s)
        {
            return (Builder.EnvMode)Enum.valueOf(android/renderscript/ProgramFragmentFixedFunction$Builder$EnvMode, s);
        }

        public static Builder.EnvMode[] values()
        {
            return $VALUES;
        }

        private static final Builder.EnvMode $VALUES[];
        public static final Builder.EnvMode DECAL;
        public static final Builder.EnvMode MODULATE;
        public static final Builder.EnvMode REPLACE;
        int mID;

        static 
        {
            REPLACE = new Builder.EnvMode("REPLACE", 0, 1);
            MODULATE = new Builder.EnvMode("MODULATE", 1, 2);
            DECAL = new Builder.EnvMode("DECAL", 2, 3);
            $VALUES = (new Builder.EnvMode[] {
                REPLACE, MODULATE, DECAL
            });
        }

        private Builder.EnvMode(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static final class Builder.Format extends Enum
    {

        public static Builder.Format valueOf(String s)
        {
            return (Builder.Format)Enum.valueOf(android/renderscript/ProgramFragmentFixedFunction$Builder$Format, s);
        }

        public static Builder.Format[] values()
        {
            return $VALUES;
        }

        private static final Builder.Format $VALUES[];
        public static final Builder.Format ALPHA;
        public static final Builder.Format LUMINANCE_ALPHA;
        public static final Builder.Format RGB;
        public static final Builder.Format RGBA;
        int mID;

        static 
        {
            ALPHA = new Builder.Format("ALPHA", 0, 1);
            LUMINANCE_ALPHA = new Builder.Format("LUMINANCE_ALPHA", 1, 2);
            RGB = new Builder.Format("RGB", 2, 3);
            RGBA = new Builder.Format("RGBA", 3, 4);
            $VALUES = (new Builder.Format[] {
                ALPHA, LUMINANCE_ALPHA, RGB, RGBA
            });
        }

        private Builder.Format(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    private class Builder.Slot
    {

        Builder.EnvMode env;
        Builder.Format format;
        final Builder this$1;

        Builder.Slot(Builder.EnvMode envmode, Builder.Format format1)
        {
            this$1 = Builder.this;
            super();
            env = envmode;
            format = format1;
        }
    }

    static class InternalBuilder extends Program.BaseProgramBuilder
    {

        public ProgramFragmentFixedFunction create()
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

            ProgramFragmentFixedFunction programfragmentfixedfunction = new ProgramFragmentFixedFunction(mRS.nProgramFragmentCreate(mShader, as, al), mRS);
            initProgram(programfragmentfixedfunction);
            return programfragmentfixedfunction;
        }

        public InternalBuilder(RenderScript renderscript)
        {
            super(renderscript);
        }
    }


    ProgramFragmentFixedFunction(long l, RenderScript renderscript)
    {
        super(l, renderscript);
    }
}
