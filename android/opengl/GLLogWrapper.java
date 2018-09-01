// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import java.io.IOException;
import java.io.Writer;
import java.nio.*;
import java.util.Arrays;
import javax.microedition.khronos.opengles.*;

// Referenced classes of package android.opengl:
//            GLWrapperBase

class GLLogWrapper extends GLWrapperBase
{
    private class PointerInfo
    {

        public void bindByteBuffer()
        {
            ByteBuffer bytebuffer = null;
            if(mPointer != null)
                bytebuffer = GLLogWrapper._2D_wrap0(GLLogWrapper.this, -1, mPointer);
            mTempByteBuffer = bytebuffer;
        }

        public int getStride()
        {
            int i;
            if(mStride > 0)
                i = mStride;
            else
                i = sizeof(mType) * mSize;
            return i;
        }

        public int sizeof(int i)
        {
            switch(i)
            {
            default:
                return 0;

            case 5121: 
                return 1;

            case 5120: 
                return 1;

            case 5122: 
                return 2;

            case 5132: 
                return 4;

            case 5126: 
                return 4;
            }
        }

        public void unbindByteBuffer()
        {
            mTempByteBuffer = null;
        }

        public Buffer mPointer;
        public int mSize;
        public int mStride;
        public ByteBuffer mTempByteBuffer;
        public int mType;
        final GLLogWrapper this$0;

        public PointerInfo()
        {
            this$0 = GLLogWrapper.this;
            super();
        }

        public PointerInfo(int i, int j, int k, Buffer buffer)
        {
            this$0 = GLLogWrapper.this;
            super();
            mSize = i;
            mType = j;
            mStride = k;
            mPointer = buffer;
        }
    }


    static ByteBuffer _2D_wrap0(GLLogWrapper gllogwrapper, int i, Buffer buffer)
    {
        return gllogwrapper.toByteBuffer(i, buffer);
    }

    public GLLogWrapper(GL gl, Writer writer, boolean flag)
    {
        super(gl);
        mColorPointer = new PointerInfo();
        mNormalPointer = new PointerInfo();
        mTexCoordPointer = new PointerInfo();
        mVertexPointer = new PointerInfo();
        mLog = writer;
        mLogArgumentNames = flag;
    }

    private void arg(String s, float f)
    {
        arg(s, Float.toString(f));
    }

    private void arg(String s, int i)
    {
        arg(s, Integer.toString(i));
    }

    private void arg(String s, int i, FloatBuffer floatbuffer)
    {
        arg(s, toString(i, floatbuffer));
    }

    private void arg(String s, int i, IntBuffer intbuffer)
    {
        arg(s, toString(i, 0, intbuffer));
    }

    private void arg(String s, int i, ShortBuffer shortbuffer)
    {
        arg(s, toString(i, shortbuffer));
    }

    private void arg(String s, int i, float af[], int j)
    {
        arg(s, toString(i, af, j));
    }

    private void arg(String s, int i, int ai[], int j)
    {
        arg(s, toString(i, 0, ai, j));
    }

    private void arg(String s, int i, short aword0[], int j)
    {
        arg(s, toString(i, aword0, j));
    }

    private void arg(String s, String s1)
    {
        int i = mArgCount;
        mArgCount = i + 1;
        if(i > 0)
            log(", ");
        if(mLogArgumentNames)
            log((new StringBuilder()).append(s).append("=").toString());
        log(s1);
    }

    private void arg(String s, boolean flag)
    {
        arg(s, Boolean.toString(flag));
    }

    private void argPointer(int i, int j, int k, Buffer buffer)
    {
        arg("size", i);
        arg("type", getPointerTypeName(j));
        arg("stride", k);
        arg("pointer", buffer.toString());
    }

    private void begin(String s)
    {
        log((new StringBuilder()).append(s).append('(').toString());
        mArgCount = 0;
    }

    private void bindArrays()
    {
        if(mColorArrayEnabled)
            mColorPointer.bindByteBuffer();
        if(mNormalArrayEnabled)
            mNormalPointer.bindByteBuffer();
        if(mTextureCoordArrayEnabled)
            mTexCoordPointer.bindByteBuffer();
        if(mVertexArrayEnabled)
            mVertexPointer.bindByteBuffer();
    }

    private void checkError()
    {
        int i = mgl.glGetError();
        if(i != 0)
            logLine((new StringBuilder()).append("glError: ").append(Integer.toString(i)).toString());
    }

    private void doArrayElement(StringBuilder stringbuilder, boolean flag, String s, PointerInfo pointerinfo, int i)
    {
        int j;
        int k;
        int l;
        int i1;
        if(!flag)
            return;
        stringbuilder.append(" ");
        stringbuilder.append(s).append(":{");
        if(pointerinfo == null || pointerinfo.mTempByteBuffer == null)
        {
            stringbuilder.append("undefined }");
            return;
        }
        if(pointerinfo.mStride < 0)
        {
            stringbuilder.append("invalid stride");
            return;
        }
        j = pointerinfo.getStride();
        s = pointerinfo.mTempByteBuffer;
        k = pointerinfo.mSize;
        l = pointerinfo.mType;
        i1 = pointerinfo.sizeof(l);
        j *= i;
        i = 0;
_L8:
        if(i >= k)
            break MISSING_BLOCK_LABEL_300;
        if(i > 0)
            stringbuilder.append(", ");
        l;
        JVM INSTR lookupswitch 5: default 176
    //                   5120: 196
    //                   5121: 213
    //                   5122: 234
    //                   5126: 278
    //                   5132: 256;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L5:
        break MISSING_BLOCK_LABEL_278;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuilder.append("?");
_L9:
        j += i1;
        i++;
        if(true) goto _L8; else goto _L7
_L7:
        stringbuilder.append(Integer.toString(s.get(j)));
          goto _L9
_L3:
        stringbuilder.append(Integer.toString(s.get(j) & 0xff));
          goto _L9
_L4:
        stringbuilder.append(Integer.toString(s.asShortBuffer().get(j / 2)));
          goto _L9
_L6:
        stringbuilder.append(Integer.toString(s.asIntBuffer().get(j / 4)));
          goto _L9
        stringbuilder.append(Float.toString(s.asFloatBuffer().get(j / 4)));
          goto _L9
        stringbuilder.append("}");
        return;
    }

    private void doElement(StringBuilder stringbuilder, int i, int j)
    {
        stringbuilder.append(" [").append(i).append(" : ").append(j).append("] =");
        doArrayElement(stringbuilder, mVertexArrayEnabled, "v", mVertexPointer, j);
        doArrayElement(stringbuilder, mNormalArrayEnabled, "n", mNormalPointer, j);
        doArrayElement(stringbuilder, mColorArrayEnabled, "c", mColorPointer, j);
        doArrayElement(stringbuilder, mTextureCoordArrayEnabled, "t", mTexCoordPointer, j);
        stringbuilder.append("\n");
    }

    private void end()
    {
        log(");\n");
        flush();
    }

    private void endLogIndices()
    {
        log(mStringBuilder.toString());
        unbindArrays();
    }

    private void flush()
    {
        mLog.flush();
_L1:
        return;
        IOException ioexception;
        ioexception;
        mLog = null;
          goto _L1
    }

    private void formattedAppend(StringBuilder stringbuilder, int i, int j)
    {
        j;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 29
    //                   1 38
    //                   2 50;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        stringbuilder.append(i);
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append(Float.intBitsToFloat(i));
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append((float)i / 65536F);
        if(true) goto _L1; else goto _L5
_L5:
    }

    private String getBeginMode(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 0: // '\0'
            return "GL_POINTS";

        case 1: // '\001'
            return "GL_LINES";

        case 2: // '\002'
            return "GL_LINE_LOOP";

        case 3: // '\003'
            return "GL_LINE_STRIP";

        case 4: // '\004'
            return "GL_TRIANGLES";

        case 5: // '\005'
            return "GL_TRIANGLE_STRIP";

        case 6: // '\006'
            return "GL_TRIANGLE_FAN";
        }
    }

    private String getCap(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 2912: 
            return "GL_FOG";

        case 2896: 
            return "GL_LIGHTING";

        case 3553: 
            return "GL_TEXTURE_2D";

        case 2884: 
            return "GL_CULL_FACE";

        case 3008: 
            return "GL_ALPHA_TEST";

        case 3042: 
            return "GL_BLEND";

        case 3058: 
            return "GL_COLOR_LOGIC_OP";

        case 3024: 
            return "GL_DITHER";

        case 2960: 
            return "GL_STENCIL_TEST";

        case 2929: 
            return "GL_DEPTH_TEST";

        case 16384: 
            return "GL_LIGHT0";

        case 16385: 
            return "GL_LIGHT1";

        case 16386: 
            return "GL_LIGHT2";

        case 16387: 
            return "GL_LIGHT3";

        case 16388: 
            return "GL_LIGHT4";

        case 16389: 
            return "GL_LIGHT5";

        case 16390: 
            return "GL_LIGHT6";

        case 16391: 
            return "GL_LIGHT7";

        case 2832: 
            return "GL_POINT_SMOOTH";

        case 2848: 
            return "GL_LINE_SMOOTH";

        case 2903: 
            return "GL_COLOR_MATERIAL";

        case 2977: 
            return "GL_NORMALIZE";

        case 32826: 
            return "GL_RESCALE_NORMAL";

        case 32884: 
            return "GL_VERTEX_ARRAY";

        case 32885: 
            return "GL_NORMAL_ARRAY";

        case 32886: 
            return "GL_COLOR_ARRAY";

        case 32888: 
            return "GL_TEXTURE_COORD_ARRAY";

        case 32925: 
            return "GL_MULTISAMPLE";

        case 32926: 
            return "GL_SAMPLE_ALPHA_TO_COVERAGE";

        case 32927: 
            return "GL_SAMPLE_ALPHA_TO_ONE";

        case 32928: 
            return "GL_SAMPLE_COVERAGE";

        case 3089: 
            return "GL_SCISSOR_TEST";
        }
    }

    private String getClearBufferMask(int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int j = i;
        if((i & 0x100) != 0)
        {
            stringbuilder.append("GL_DEPTH_BUFFER_BIT");
            j = i & 0xfffffeff;
        }
        i = j;
        if((j & 0x400) != 0)
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append(" | ");
            stringbuilder.append("GL_STENCIL_BUFFER_BIT");
            i = j & 0xfffffbff;
        }
        j = i;
        if((i & 0x4000) != 0)
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append(" | ");
            stringbuilder.append("GL_COLOR_BUFFER_BIT");
            j = i & 0xffffbfff;
        }
        if(j != 0)
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append(" | ");
            stringbuilder.append(getHex(j));
        }
        return stringbuilder.toString();
    }

    private String getClientState(int i)
    {
        switch(i)
        {
        case 32887: 
        default:
            return getHex(i);

        case 32886: 
            return "GL_COLOR_ARRAY";

        case 32884: 
            return "GL_VERTEX_ARRAY";

        case 32885: 
            return "GL_NORMAL_ARRAY";

        case 32888: 
            return "GL_TEXTURE_COORD_ARRAY";
        }
    }

    public static String getErrorString(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 0: // '\0'
            return "GL_NO_ERROR";

        case 1280: 
            return "GL_INVALID_ENUM";

        case 1281: 
            return "GL_INVALID_VALUE";

        case 1282: 
            return "GL_INVALID_OPERATION";

        case 1283: 
            return "GL_STACK_OVERFLOW";

        case 1284: 
            return "GL_STACK_UNDERFLOW";

        case 1285: 
            return "GL_OUT_OF_MEMORY";
        }
    }

    private String getFaceName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 1032: 
            return "GL_FRONT_AND_BACK";
        }
    }

    private String getFactor(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 0: // '\0'
            return "GL_ZERO";

        case 1: // '\001'
            return "GL_ONE";

        case 768: 
            return "GL_SRC_COLOR";

        case 769: 
            return "GL_ONE_MINUS_SRC_COLOR";

        case 774: 
            return "GL_DST_COLOR";

        case 775: 
            return "GL_ONE_MINUS_DST_COLOR";

        case 770: 
            return "GL_SRC_ALPHA";

        case 771: 
            return "GL_ONE_MINUS_SRC_ALPHA";

        case 772: 
            return "GL_DST_ALPHA";

        case 773: 
            return "GL_ONE_MINUS_DST_ALPHA";

        case 776: 
            return "GL_SRC_ALPHA_SATURATE";
        }
    }

    private String getFogPName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 2914: 
            return "GL_FOG_DENSITY";

        case 2915: 
            return "GL_FOG_START";

        case 2916: 
            return "GL_FOG_END";

        case 2917: 
            return "GL_FOG_MODE";

        case 2918: 
            return "GL_FOG_COLOR";
        }
    }

    private int getFogParamCount(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 2914: 
            return 1;

        case 2915: 
            return 1;

        case 2916: 
            return 1;

        case 2917: 
            return 1;

        case 2918: 
            return 4;
        }
    }

    private static String getHex(int i)
    {
        return (new StringBuilder()).append("0x").append(Integer.toHexString(i)).toString();
    }

    private String getHintMode(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 4353: 
            return "GL_FASTEST";

        case 4354: 
            return "GL_NICEST";

        case 4352: 
            return "GL_DONT_CARE";
        }
    }

    private String getHintTarget(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 3156: 
            return "GL_FOG_HINT";

        case 3154: 
            return "GL_LINE_SMOOTH_HINT";

        case 3152: 
            return "GL_PERSPECTIVE_CORRECTION_HINT";

        case 3153: 
            return "GL_POINT_SMOOTH_HINT";

        case 3155: 
            return "GL_POLYGON_SMOOTH_HINT";

        case 33170: 
            return "GL_GENERATE_MIPMAP_HINT";
        }
    }

    private String getIndexType(int i)
    {
        switch(i)
        {
        case 5122: 
        default:
            return getHex(i);

        case 5123: 
            return "GL_UNSIGNED_SHORT";

        case 5121: 
            return "GL_UNSIGNED_BYTE";
        }
    }

    private int getIntegerStateFormat(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 35213: 
        case 35214: 
        case 35215: 
            return 1;
        }
    }

    private String getIntegerStateName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 3413: 
            return "GL_ALPHA_BITS";

        case 33902: 
            return "GL_ALIASED_LINE_WIDTH_RANGE";

        case 33901: 
            return "GL_ALIASED_POINT_SIZE_RANGE";

        case 3412: 
            return "GL_BLUE_BITS";

        case 34467: 
            return "GL_COMPRESSED_TEXTURE_FORMATS";

        case 3414: 
            return "GL_DEPTH_BITS";

        case 3411: 
            return "GL_GREEN_BITS";

        case 33001: 
            return "GL_MAX_ELEMENTS_INDICES";

        case 33000: 
            return "GL_MAX_ELEMENTS_VERTICES";

        case 3377: 
            return "GL_MAX_LIGHTS";

        case 3379: 
            return "GL_MAX_TEXTURE_SIZE";

        case 3386: 
            return "GL_MAX_VIEWPORT_DIMS";

        case 3382: 
            return "GL_MAX_MODELVIEW_STACK_DEPTH";

        case 3384: 
            return "GL_MAX_PROJECTION_STACK_DEPTH";

        case 3385: 
            return "GL_MAX_TEXTURE_STACK_DEPTH";

        case 34018: 
            return "GL_MAX_TEXTURE_UNITS";

        case 34466: 
            return "GL_NUM_COMPRESSED_TEXTURE_FORMATS";

        case 3410: 
            return "GL_RED_BITS";

        case 2850: 
            return "GL_SMOOTH_LINE_WIDTH_RANGE";

        case 2834: 
            return "GL_SMOOTH_POINT_SIZE_RANGE";

        case 3415: 
            return "GL_STENCIL_BITS";

        case 3408: 
            return "GL_SUBPIXEL_BITS";

        case 35213: 
            return "GL_MODELVIEW_MATRIX_FLOAT_AS_INT_BITS_OES";

        case 35214: 
            return "GL_PROJECTION_MATRIX_FLOAT_AS_INT_BITS_OES";

        case 35215: 
            return "GL_TEXTURE_MATRIX_FLOAT_AS_INT_BITS_OES";
        }
    }

    private int getIntegerStateSize(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 3413: 
            return 1;

        case 33902: 
            return 2;

        case 33901: 
            return 2;

        case 3412: 
            return 1;

        case 34467: 
            int ai[] = new int[1];
            mgl.glGetIntegerv(34466, ai, 0);
            return ai[0];

        case 3414: 
            return 1;

        case 3411: 
            return 1;

        case 33001: 
            return 1;

        case 33000: 
            return 1;

        case 3377: 
            return 1;

        case 3379: 
            return 1;

        case 3386: 
            return 2;

        case 3382: 
            return 1;

        case 3384: 
            return 1;

        case 3385: 
            return 1;

        case 34018: 
            return 1;

        case 34466: 
            return 1;

        case 3410: 
            return 1;

        case 2850: 
            return 2;

        case 2834: 
            return 2;

        case 3415: 
            return 1;

        case 3408: 
            return 1;

        case 35213: 
        case 35214: 
        case 35215: 
            return 16;
        }
    }

    private String getLightModelPName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 2899: 
            return "GL_LIGHT_MODEL_AMBIENT";

        case 2898: 
            return "GL_LIGHT_MODEL_TWO_SIDE";
        }
    }

    private int getLightModelParamCount(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 2899: 
            return 4;

        case 2898: 
            return 1;
        }
    }

    private String getLightName(int i)
    {
        if(i >= 16384 && i <= 16391)
            return (new StringBuilder()).append("GL_LIGHT").append(Integer.toString(i)).toString();
        else
            return getHex(i);
    }

    private String getLightPName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 4608: 
            return "GL_AMBIENT";

        case 4609: 
            return "GL_DIFFUSE";

        case 4610: 
            return "GL_SPECULAR";

        case 4611: 
            return "GL_POSITION";

        case 4612: 
            return "GL_SPOT_DIRECTION";

        case 4613: 
            return "GL_SPOT_EXPONENT";

        case 4614: 
            return "GL_SPOT_CUTOFF";

        case 4615: 
            return "GL_CONSTANT_ATTENUATION";

        case 4616: 
            return "GL_LINEAR_ATTENUATION";

        case 4617: 
            return "GL_QUADRATIC_ATTENUATION";
        }
    }

    private int getLightParamCount(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 4608: 
            return 4;

        case 4609: 
            return 4;

        case 4610: 
            return 4;

        case 4611: 
            return 4;

        case 4612: 
            return 3;

        case 4613: 
            return 1;

        case 4614: 
            return 1;

        case 4615: 
            return 1;

        case 4616: 
            return 1;

        case 4617: 
            return 1;
        }
    }

    private String getMaterialPName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 4608: 
            return "GL_AMBIENT";

        case 4609: 
            return "GL_DIFFUSE";

        case 4610: 
            return "GL_SPECULAR";

        case 5632: 
            return "GL_EMISSION";

        case 5633: 
            return "GL_SHININESS";

        case 5634: 
            return "GL_AMBIENT_AND_DIFFUSE";
        }
    }

    private int getMaterialParamCount(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 4608: 
            return 4;

        case 4609: 
            return 4;

        case 4610: 
            return 4;

        case 5632: 
            return 4;

        case 5633: 
            return 1;

        case 5634: 
            return 4;
        }
    }

    private String getMatrixMode(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 5888: 
            return "GL_MODELVIEW";

        case 5889: 
            return "GL_PROJECTION";

        case 5890: 
            return "GL_TEXTURE";
        }
    }

    private String getPointerTypeName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 5120: 
            return "GL_BYTE";

        case 5121: 
            return "GL_UNSIGNED_BYTE";

        case 5122: 
            return "GL_SHORT";

        case 5132: 
            return "GL_FIXED";

        case 5126: 
            return "GL_FLOAT";
        }
    }

    private String getShadeModel(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 7424: 
            return "GL_FLAT";

        case 7425: 
            return "GL_SMOOTH";
        }
    }

    private String getTextureEnvPName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 8704: 
            return "GL_TEXTURE_ENV_MODE";

        case 8705: 
            return "GL_TEXTURE_ENV_COLOR";
        }
    }

    private int getTextureEnvParamCount(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 8704: 
            return 1;

        case 8705: 
            return 4;
        }
    }

    private String getTextureEnvParamName(float f)
    {
        int i = (int)f;
        if(f == (float)i)
            switch(i)
            {
            default:
                return getHex(i);

            case 7681: 
                return "GL_REPLACE";

            case 8448: 
                return "GL_MODULATE";

            case 8449: 
                return "GL_DECAL";

            case 3042: 
                return "GL_BLEND";

            case 260: 
                return "GL_ADD";

            case 34160: 
                return "GL_COMBINE";
            }
        else
            return Float.toString(f);
    }

    private String getTextureEnvTarget(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 8960: 
            return "GL_TEXTURE_ENV";
        }
    }

    private String getTexturePName(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 10240: 
            return "GL_TEXTURE_MAG_FILTER";

        case 10241: 
            return "GL_TEXTURE_MIN_FILTER";

        case 10242: 
            return "GL_TEXTURE_WRAP_S";

        case 10243: 
            return "GL_TEXTURE_WRAP_T";

        case 33169: 
            return "GL_GENERATE_MIPMAP";

        case 35741: 
            return "GL_TEXTURE_CROP_RECT_OES";
        }
    }

    private String getTextureParamName(float f)
    {
        int i = (int)f;
        if(f == (float)i)
            switch(i)
            {
            default:
                return getHex(i);

            case 33071: 
                return "GL_CLAMP_TO_EDGE";

            case 10497: 
                return "GL_REPEAT";

            case 9728: 
                return "GL_NEAREST";

            case 9729: 
                return "GL_LINEAR";

            case 9984: 
                return "GL_NEAREST_MIPMAP_NEAREST";

            case 9985: 
                return "GL_LINEAR_MIPMAP_NEAREST";

            case 9986: 
                return "GL_NEAREST_MIPMAP_LINEAR";

            case 9987: 
                return "GL_LINEAR_MIPMAP_LINEAR";
            }
        else
            return Float.toString(f);
    }

    private String getTextureTarget(int i)
    {
        switch(i)
        {
        default:
            return getHex(i);

        case 3553: 
            return "GL_TEXTURE_2D";
        }
    }

    private void log(String s)
    {
        mLog.write(s);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void logLine(String s)
    {
        log((new StringBuilder()).append(s).append('\n').toString());
    }

    private void returns(int i)
    {
        returns(Integer.toString(i));
    }

    private void returns(String s)
    {
        log((new StringBuilder()).append(") returns ").append(s).append(";\n").toString());
        flush();
    }

    private void startLogIndices()
    {
        mStringBuilder = new StringBuilder();
        mStringBuilder.append("\n");
        bindArrays();
    }

    private ByteBuffer toByteBuffer(int i, Buffer buffer)
    {
        int j;
        if(i < 0)
            j = 1;
        else
            j = 0;
        if(buffer instanceof ByteBuffer)
        {
            ByteBuffer bytebuffer = (ByteBuffer)buffer;
            int l = bytebuffer.position();
            if(j != 0)
                i = bytebuffer.limit() - l;
            buffer = ByteBuffer.allocate(i).order(bytebuffer.order());
            for(j = 0; j < i; j++)
                buffer.put(bytebuffer.get());

            bytebuffer.position(l);
        } else
        if(buffer instanceof CharBuffer)
        {
            CharBuffer charbuffer1 = (CharBuffer)buffer;
            int i1 = charbuffer1.position();
            if(j != 0)
                i = (charbuffer1.limit() - i1) * 2;
            buffer = ByteBuffer.allocate(i).order(charbuffer1.order());
            CharBuffer charbuffer = buffer.asCharBuffer();
            for(j = 0; j < i / 2; j++)
                charbuffer.put(charbuffer1.get());

            charbuffer1.position(i1);
        } else
        if(buffer instanceof ShortBuffer)
        {
            ShortBuffer shortbuffer = (ShortBuffer)buffer;
            int j1 = shortbuffer.position();
            if(j != 0)
                i = (shortbuffer.limit() - j1) * 2;
            buffer = ByteBuffer.allocate(i).order(shortbuffer.order());
            ShortBuffer shortbuffer1 = buffer.asShortBuffer();
            for(j = 0; j < i / 2; j++)
                shortbuffer1.put(shortbuffer.get());

            shortbuffer.position(j1);
        } else
        if(buffer instanceof IntBuffer)
        {
            IntBuffer intbuffer = (IntBuffer)buffer;
            int k1 = intbuffer.position();
            if(j != 0)
                i = (intbuffer.limit() - k1) * 4;
            buffer = ByteBuffer.allocate(i).order(intbuffer.order());
            IntBuffer intbuffer1 = buffer.asIntBuffer();
            for(j = 0; j < i / 4; j++)
                intbuffer1.put(intbuffer.get());

            intbuffer.position(k1);
        } else
        if(buffer instanceof FloatBuffer)
        {
            FloatBuffer floatbuffer1 = (FloatBuffer)buffer;
            int l1 = floatbuffer1.position();
            if(j != 0)
                i = (floatbuffer1.limit() - l1) * 4;
            buffer = ByteBuffer.allocate(i).order(floatbuffer1.order());
            FloatBuffer floatbuffer = buffer.asFloatBuffer();
            for(j = 0; j < i / 4; j++)
                floatbuffer.put(floatbuffer1.get());

            floatbuffer1.position(l1);
        } else
        if(buffer instanceof DoubleBuffer)
        {
            DoubleBuffer doublebuffer = (DoubleBuffer)buffer;
            int i2 = doublebuffer.position();
            if(j != 0)
                i = (doublebuffer.limit() - i2) * 8;
            buffer = ByteBuffer.allocate(i).order(doublebuffer.order());
            DoubleBuffer doublebuffer1 = buffer.asDoubleBuffer();
            for(j = 0; j < i / 8; j++)
                doublebuffer1.put(doublebuffer.get());

            doublebuffer.position(i2);
        } else
        if(buffer instanceof LongBuffer)
        {
            LongBuffer longbuffer1 = (LongBuffer)buffer;
            int j2 = longbuffer1.position();
            if(j != 0)
                i = (longbuffer1.limit() - j2) * 8;
            buffer = ByteBuffer.allocate(i).order(longbuffer1.order());
            LongBuffer longbuffer = buffer.asLongBuffer();
            for(int k = 0; k < i / 8; k++)
                longbuffer.put(longbuffer1.get());

            longbuffer1.position(j2);
        } else
        {
            throw new RuntimeException("Unimplemented Buffer subclass.");
        }
        buffer.rewind();
        buffer.order(ByteOrder.nativeOrder());
        return buffer;
    }

    private char[] toCharIndices(int i, int j, Buffer buffer)
    {
        char ac[] = new char[i];
        j;
        JVM INSTR tableswitch 5121 5123: default 32
    //                   5121 35
    //                   5122 32
    //                   5123 84;
           goto _L1 _L2 _L1 _L3
_L1:
        return ac;
_L2:
        buffer = toByteBuffer(i, buffer);
        byte abyte0[] = buffer.array();
        int k = buffer.arrayOffset();
        j = 0;
        while(j < i) 
        {
            ac[j] = (char)(abyte0[k + j] & 0xff);
            j++;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(buffer instanceof CharBuffer)
            buffer = (CharBuffer)buffer;
        else
            buffer = toByteBuffer(i * 2, buffer).asCharBuffer();
        i = buffer.position();
        buffer.position(0);
        buffer.get(ac);
        buffer.position(i);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private String toString(int i, int j, IntBuffer intbuffer)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        for(int k = 0; k < i; k++)
        {
            stringbuilder.append(" [").append(k).append("] = ");
            formattedAppend(stringbuilder, intbuffer.get(k), j);
            stringbuilder.append('\n');
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(int i, int j, int ai[], int k)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        int l = ai.length;
        int i1 = 0;
        while(i1 < i) 
        {
            int j1 = k + i1;
            stringbuilder.append(" [").append(j1).append("] = ");
            if(j1 < 0 || j1 >= l)
                stringbuilder.append("out of bounds");
            else
                formattedAppend(stringbuilder, ai[j1], j);
            stringbuilder.append('\n');
            i1++;
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(int i, FloatBuffer floatbuffer)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        for(int j = 0; j < i; j++)
            stringbuilder.append(" [").append(j).append("] = ").append(floatbuffer.get(j)).append('\n');

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(int i, ShortBuffer shortbuffer)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        for(int j = 0; j < i; j++)
            stringbuilder.append(" [").append(j).append("] = ").append(shortbuffer.get(j)).append('\n');

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(int i, float af[], int j)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        int k = af.length;
        int l = 0;
        while(l < i) 
        {
            int i1 = j + l;
            stringbuilder.append("[").append(i1).append("] = ");
            if(i1 < 0 || i1 >= k)
                stringbuilder.append("out of bounds");
            else
                stringbuilder.append(af[i1]);
            stringbuilder.append('\n');
            l++;
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private String toString(int i, short aword0[], int j)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{\n");
        int k = aword0.length;
        int l = 0;
        while(l < i) 
        {
            int i1 = j + l;
            stringbuilder.append(" [").append(i1).append("] = ");
            if(i1 < 0 || i1 >= k)
                stringbuilder.append("out of bounds");
            else
                stringbuilder.append(aword0[i1]);
            stringbuilder.append('\n');
            l++;
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private void unbindArrays()
    {
        if(mColorArrayEnabled)
            mColorPointer.unbindByteBuffer();
        if(mNormalArrayEnabled)
            mNormalPointer.unbindByteBuffer();
        if(mTextureCoordArrayEnabled)
            mTexCoordPointer.unbindByteBuffer();
        if(mVertexArrayEnabled)
            mVertexPointer.unbindByteBuffer();
    }

    public void glActiveTexture(int i)
    {
        begin("glActiveTexture");
        arg("texture", i);
        end();
        mgl.glActiveTexture(i);
        checkError();
    }

    public void glAlphaFunc(int i, float f)
    {
        begin("glAlphaFunc");
        arg("func", i);
        arg("ref", f);
        end();
        mgl.glAlphaFunc(i, f);
        checkError();
    }

    public void glAlphaFuncx(int i, int j)
    {
        begin("glAlphaFuncx");
        arg("func", i);
        arg("ref", j);
        end();
        mgl.glAlphaFuncx(i, j);
        checkError();
    }

    public void glBindBuffer(int i, int j)
    {
        begin("glBindBuffer");
        arg("target", i);
        arg("buffer", j);
        end();
        mgl11.glBindBuffer(i, j);
        checkError();
    }

    public void glBindFramebufferOES(int i, int j)
    {
        begin("glBindFramebufferOES");
        arg("target", i);
        arg("framebuffer", j);
        end();
        mgl11ExtensionPack.glBindFramebufferOES(i, j);
        checkError();
    }

    public void glBindRenderbufferOES(int i, int j)
    {
        begin("glBindRenderbufferOES");
        arg("target", i);
        arg("renderbuffer", j);
        end();
        mgl11ExtensionPack.glBindRenderbufferOES(i, j);
        checkError();
    }

    public void glBindTexture(int i, int j)
    {
        begin("glBindTexture");
        arg("target", getTextureTarget(i));
        arg("texture", j);
        end();
        mgl.glBindTexture(i, j);
        checkError();
    }

    public void glBlendEquation(int i)
    {
        begin("glBlendEquation");
        arg("mode", i);
        end();
        mgl11ExtensionPack.glBlendEquation(i);
        checkError();
    }

    public void glBlendEquationSeparate(int i, int j)
    {
        begin("glBlendEquationSeparate");
        arg("modeRGB", i);
        arg("modeAlpha", j);
        end();
        mgl11ExtensionPack.glBlendEquationSeparate(i, j);
        checkError();
    }

    public void glBlendFunc(int i, int j)
    {
        begin("glBlendFunc");
        arg("sfactor", getFactor(i));
        arg("dfactor", getFactor(j));
        end();
        mgl.glBlendFunc(i, j);
        checkError();
    }

    public void glBlendFuncSeparate(int i, int j, int k, int l)
    {
        begin("glBlendFuncSeparate");
        arg("srcRGB", i);
        arg("dstRGB", j);
        arg("srcAlpha", k);
        arg("dstAlpha", l);
        end();
        mgl11ExtensionPack.glBlendFuncSeparate(i, j, k, l);
        checkError();
    }

    public void glBufferData(int i, int j, Buffer buffer, int k)
    {
        begin("glBufferData");
        arg("target", i);
        arg("size", j);
        arg("data", buffer.toString());
        arg("usage", k);
        end();
        mgl11.glBufferData(i, j, buffer, k);
        checkError();
    }

    public void glBufferSubData(int i, int j, int k, Buffer buffer)
    {
        begin("glBufferSubData");
        arg("target", i);
        arg("offset", j);
        arg("size", k);
        arg("data", buffer.toString());
        end();
        mgl11.glBufferSubData(i, j, k, buffer);
        checkError();
    }

    public int glCheckFramebufferStatusOES(int i)
    {
        begin("glCheckFramebufferStatusOES");
        arg("target", i);
        end();
        i = mgl11ExtensionPack.glCheckFramebufferStatusOES(i);
        checkError();
        return i;
    }

    public void glClear(int i)
    {
        begin("glClear");
        arg("mask", getClearBufferMask(i));
        end();
        mgl.glClear(i);
        checkError();
    }

    public void glClearColor(float f, float f1, float f2, float f3)
    {
        begin("glClearColor");
        arg("red", f);
        arg("green", f1);
        arg("blue", f2);
        arg("alpha", f3);
        end();
        mgl.glClearColor(f, f1, f2, f3);
        checkError();
    }

    public void glClearColorx(int i, int j, int k, int l)
    {
        begin("glClearColor");
        arg("red", i);
        arg("green", j);
        arg("blue", k);
        arg("alpha", l);
        end();
        mgl.glClearColorx(i, j, k, l);
        checkError();
    }

    public void glClearDepthf(float f)
    {
        begin("glClearDepthf");
        arg("depth", f);
        end();
        mgl.glClearDepthf(f);
        checkError();
    }

    public void glClearDepthx(int i)
    {
        begin("glClearDepthx");
        arg("depth", i);
        end();
        mgl.glClearDepthx(i);
        checkError();
    }

    public void glClearStencil(int i)
    {
        begin("glClearStencil");
        arg("s", i);
        end();
        mgl.glClearStencil(i);
        checkError();
    }

    public void glClientActiveTexture(int i)
    {
        begin("glClientActiveTexture");
        arg("texture", i);
        end();
        mgl.glClientActiveTexture(i);
        checkError();
    }

    public void glClipPlanef(int i, FloatBuffer floatbuffer)
    {
        begin("glClipPlanef");
        arg("plane", i);
        arg("equation", 4, floatbuffer);
        end();
        mgl11.glClipPlanef(i, floatbuffer);
        checkError();
    }

    public void glClipPlanef(int i, float af[], int j)
    {
        begin("glClipPlanef");
        arg("plane", i);
        arg("equation", 4, af, j);
        arg("offset", j);
        end();
        mgl11.glClipPlanef(i, af, j);
        checkError();
    }

    public void glClipPlanex(int i, IntBuffer intbuffer)
    {
        begin("glClipPlanef");
        arg("plane", i);
        arg("equation", 4, intbuffer);
        end();
        mgl11.glClipPlanex(i, intbuffer);
        checkError();
    }

    public void glClipPlanex(int i, int ai[], int j)
    {
        begin("glClipPlanex");
        arg("plane", i);
        arg("equation", 4, ai, j);
        arg("offset", j);
        end();
        mgl11.glClipPlanex(i, ai, j);
        checkError();
    }

    public void glColor4f(float f, float f1, float f2, float f3)
    {
        begin("glColor4f");
        arg("red", f);
        arg("green", f1);
        arg("blue", f2);
        arg("alpha", f3);
        end();
        mgl.glColor4f(f, f1, f2, f3);
        checkError();
    }

    public void glColor4ub(byte byte0, byte byte1, byte byte2, byte byte3)
    {
        begin("glColor4ub");
        arg("red", byte0);
        arg("green", byte1);
        arg("blue", byte2);
        arg("alpha", byte3);
        end();
        mgl11.glColor4ub(byte0, byte1, byte2, byte3);
        checkError();
    }

    public void glColor4x(int i, int j, int k, int l)
    {
        begin("glColor4x");
        arg("red", i);
        arg("green", j);
        arg("blue", k);
        arg("alpha", l);
        end();
        mgl.glColor4x(i, j, k, l);
        checkError();
    }

    public void glColorMask(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        begin("glColorMask");
        arg("red", flag);
        arg("green", flag1);
        arg("blue", flag2);
        arg("alpha", flag3);
        end();
        mgl.glColorMask(flag, flag1, flag2, flag3);
        checkError();
    }

    public void glColorPointer(int i, int j, int k, int l)
    {
        begin("glColorPointer");
        arg("size", i);
        arg("type", j);
        arg("stride", k);
        arg("offset", l);
        end();
        mgl11.glColorPointer(i, j, k, l);
        checkError();
    }

    public void glColorPointer(int i, int j, int k, Buffer buffer)
    {
        begin("glColorPointer");
        argPointer(i, j, k, buffer);
        end();
        mColorPointer = new PointerInfo(i, j, k, buffer);
        mgl.glColorPointer(i, j, k, buffer);
        checkError();
    }

    public void glCompressedTexImage2D(int i, int j, int k, int l, int i1, int j1, int k1, 
            Buffer buffer)
    {
        begin("glCompressedTexImage2D");
        arg("target", getTextureTarget(i));
        arg("level", j);
        arg("internalformat", k);
        arg("width", l);
        arg("height", i1);
        arg("border", j1);
        arg("imageSize", k1);
        arg("data", buffer.toString());
        end();
        mgl.glCompressedTexImage2D(i, j, k, l, i1, j1, k1, buffer);
        checkError();
    }

    public void glCompressedTexSubImage2D(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, Buffer buffer)
    {
        begin("glCompressedTexSubImage2D");
        arg("target", getTextureTarget(i));
        arg("level", j);
        arg("xoffset", k);
        arg("yoffset", l);
        arg("width", i1);
        arg("height", j1);
        arg("format", k1);
        arg("imageSize", l1);
        arg("data", buffer.toString());
        end();
        mgl.glCompressedTexSubImage2D(i, j, k, l, i1, j1, k1, l1, buffer);
        checkError();
    }

    public void glCopyTexImage2D(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        begin("glCopyTexImage2D");
        arg("target", getTextureTarget(i));
        arg("level", j);
        arg("internalformat", k);
        arg("x", l);
        arg("y", i1);
        arg("width", j1);
        arg("height", k1);
        arg("border", l1);
        end();
        mgl.glCopyTexImage2D(i, j, k, l, i1, j1, k1, l1);
        checkError();
    }

    public void glCopyTexSubImage2D(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        begin("glCopyTexSubImage2D");
        arg("target", getTextureTarget(i));
        arg("level", j);
        arg("xoffset", k);
        arg("yoffset", l);
        arg("x", i1);
        arg("y", j1);
        arg("width", k1);
        arg("height", l1);
        end();
        mgl.glCopyTexSubImage2D(i, j, k, l, i1, j1, k1, l1);
        checkError();
    }

    public void glCullFace(int i)
    {
        begin("glCullFace");
        arg("mode", i);
        end();
        mgl.glCullFace(i);
        checkError();
    }

    public void glCurrentPaletteMatrixOES(int i)
    {
        begin("glCurrentPaletteMatrixOES");
        arg("matrixpaletteindex", i);
        end();
        mgl11Ext.glCurrentPaletteMatrixOES(i);
        checkError();
    }

    public void glDeleteBuffers(int i, IntBuffer intbuffer)
    {
        begin("glDeleteBuffers");
        arg("n", i);
        arg("buffers", intbuffer.toString());
        end();
        mgl11.glDeleteBuffers(i, intbuffer);
        checkError();
    }

    public void glDeleteBuffers(int i, int ai[], int j)
    {
        begin("glDeleteBuffers");
        arg("n", i);
        arg("buffers", ai.toString());
        arg("offset", j);
        end();
        mgl11.glDeleteBuffers(i, ai, j);
        checkError();
    }

    public void glDeleteFramebuffersOES(int i, IntBuffer intbuffer)
    {
        begin("glDeleteFramebuffersOES");
        arg("n", i);
        arg("framebuffers", intbuffer.toString());
        end();
        mgl11ExtensionPack.glDeleteFramebuffersOES(i, intbuffer);
        checkError();
    }

    public void glDeleteFramebuffersOES(int i, int ai[], int j)
    {
        begin("glDeleteFramebuffersOES");
        arg("n", i);
        arg("framebuffers", ai.toString());
        arg("offset", j);
        end();
        mgl11ExtensionPack.glDeleteFramebuffersOES(i, ai, j);
        checkError();
    }

    public void glDeleteRenderbuffersOES(int i, IntBuffer intbuffer)
    {
        begin("glDeleteRenderbuffersOES");
        arg("n", i);
        arg("renderbuffers", intbuffer.toString());
        end();
        mgl11ExtensionPack.glDeleteRenderbuffersOES(i, intbuffer);
        checkError();
    }

    public void glDeleteRenderbuffersOES(int i, int ai[], int j)
    {
        begin("glDeleteRenderbuffersOES");
        arg("n", i);
        arg("renderbuffers", ai.toString());
        arg("offset", j);
        end();
        mgl11ExtensionPack.glDeleteRenderbuffersOES(i, ai, j);
        checkError();
    }

    public void glDeleteTextures(int i, IntBuffer intbuffer)
    {
        begin("glDeleteTextures");
        arg("n", i);
        arg("textures", i, intbuffer);
        end();
        mgl.glDeleteTextures(i, intbuffer);
        checkError();
    }

    public void glDeleteTextures(int i, int ai[], int j)
    {
        begin("glDeleteTextures");
        arg("n", i);
        arg("textures", i, ai, j);
        arg("offset", j);
        end();
        mgl.glDeleteTextures(i, ai, j);
        checkError();
    }

    public void glDepthFunc(int i)
    {
        begin("glDepthFunc");
        arg("func", i);
        end();
        mgl.glDepthFunc(i);
        checkError();
    }

    public void glDepthMask(boolean flag)
    {
        begin("glDepthMask");
        arg("flag", flag);
        end();
        mgl.glDepthMask(flag);
        checkError();
    }

    public void glDepthRangef(float f, float f1)
    {
        begin("glDepthRangef");
        arg("near", f);
        arg("far", f1);
        end();
        mgl.glDepthRangef(f, f1);
        checkError();
    }

    public void glDepthRangex(int i, int j)
    {
        begin("glDepthRangex");
        arg("near", i);
        arg("far", j);
        end();
        mgl.glDepthRangex(i, j);
        checkError();
    }

    public void glDisable(int i)
    {
        begin("glDisable");
        arg("cap", getCap(i));
        end();
        mgl.glDisable(i);
        checkError();
    }

    public void glDisableClientState(int i)
    {
        begin("glDisableClientState");
        arg("array", getClientState(i));
        end();
        i;
        JVM INSTR tableswitch 32884 32888: default 60
    //                   32884 99
    //                   32885 83
    //                   32886 75
    //                   32887 60
    //                   32888 91;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
        mgl.glDisableClientState(i);
        checkError();
        return;
_L4:
        mColorArrayEnabled = false;
        continue; /* Loop/switch isn't completed */
_L3:
        mNormalArrayEnabled = false;
        continue; /* Loop/switch isn't completed */
_L5:
        mTextureCoordArrayEnabled = false;
        continue; /* Loop/switch isn't completed */
_L2:
        mVertexArrayEnabled = false;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void glDrawArrays(int i, int j, int k)
    {
        begin("glDrawArrays");
        arg("mode", i);
        arg("first", j);
        arg("count", k);
        startLogIndices();
        for(int l = 0; l < k; l++)
            doElement(mStringBuilder, l, j + l);

        endLogIndices();
        end();
        mgl.glDrawArrays(i, j, k);
        checkError();
    }

    public void glDrawElements(int i, int j, int k, int l)
    {
        begin("glDrawElements");
        arg("mode", i);
        arg("count", j);
        arg("type", k);
        arg("offset", l);
        end();
        mgl11.glDrawElements(i, j, k, l);
        checkError();
    }

    public void glDrawElements(int i, int j, int k, Buffer buffer)
    {
        begin("glDrawElements");
        arg("mode", getBeginMode(i));
        arg("count", j);
        arg("type", getIndexType(k));
        char ac[] = toCharIndices(j, k, buffer);
        int l = ac.length;
        startLogIndices();
        for(int i1 = 0; i1 < l; i1++)
            doElement(mStringBuilder, i1, ac[i1]);

        endLogIndices();
        end();
        mgl.glDrawElements(i, j, k, buffer);
        checkError();
    }

    public void glDrawTexfOES(float f, float f1, float f2, float f3, float f4)
    {
        begin("glDrawTexfOES");
        arg("x", f);
        arg("y", f1);
        arg("z", f2);
        arg("width", f3);
        arg("height", f4);
        end();
        mgl11Ext.glDrawTexfOES(f, f1, f2, f3, f4);
        checkError();
    }

    public void glDrawTexfvOES(FloatBuffer floatbuffer)
    {
        begin("glDrawTexfvOES");
        arg("coords", 5, floatbuffer);
        end();
        mgl11Ext.glDrawTexfvOES(floatbuffer);
        checkError();
    }

    public void glDrawTexfvOES(float af[], int i)
    {
        begin("glDrawTexfvOES");
        arg("coords", 5, af, i);
        arg("offset", i);
        end();
        mgl11Ext.glDrawTexfvOES(af, i);
        checkError();
    }

    public void glDrawTexiOES(int i, int j, int k, int l, int i1)
    {
        begin("glDrawTexiOES");
        arg("x", i);
        arg("y", j);
        arg("z", k);
        arg("width", l);
        arg("height", i1);
        end();
        mgl11Ext.glDrawTexiOES(i, j, k, l, i1);
        checkError();
    }

    public void glDrawTexivOES(IntBuffer intbuffer)
    {
        begin("glDrawTexivOES");
        arg("coords", 5, intbuffer);
        end();
        mgl11Ext.glDrawTexivOES(intbuffer);
        checkError();
    }

    public void glDrawTexivOES(int ai[], int i)
    {
        begin("glDrawTexivOES");
        arg("coords", 5, ai, i);
        arg("offset", i);
        end();
        mgl11Ext.glDrawTexivOES(ai, i);
        checkError();
    }

    public void glDrawTexsOES(short word0, short word1, short word2, short word3, short word4)
    {
        begin("glDrawTexsOES");
        arg("x", word0);
        arg("y", word1);
        arg("z", word2);
        arg("width", word3);
        arg("height", word4);
        end();
        mgl11Ext.glDrawTexsOES(word0, word1, word2, word3, word4);
        checkError();
    }

    public void glDrawTexsvOES(ShortBuffer shortbuffer)
    {
        begin("glDrawTexsvOES");
        arg("coords", 5, shortbuffer);
        end();
        mgl11Ext.glDrawTexsvOES(shortbuffer);
        checkError();
    }

    public void glDrawTexsvOES(short aword0[], int i)
    {
        begin("glDrawTexsvOES");
        arg("coords", 5, aword0, i);
        arg("offset", i);
        end();
        mgl11Ext.glDrawTexsvOES(aword0, i);
        checkError();
    }

    public void glDrawTexxOES(int i, int j, int k, int l, int i1)
    {
        begin("glDrawTexxOES");
        arg("x", i);
        arg("y", j);
        arg("z", k);
        arg("width", l);
        arg("height", i1);
        end();
        mgl11Ext.glDrawTexxOES(i, j, k, l, i1);
        checkError();
    }

    public void glDrawTexxvOES(IntBuffer intbuffer)
    {
        begin("glDrawTexxvOES");
        arg("coords", 5, intbuffer);
        end();
        mgl11Ext.glDrawTexxvOES(intbuffer);
        checkError();
    }

    public void glDrawTexxvOES(int ai[], int i)
    {
        begin("glDrawTexxvOES");
        arg("coords", 5, ai, i);
        arg("offset", i);
        end();
        mgl11Ext.glDrawTexxvOES(ai, i);
        checkError();
    }

    public void glEnable(int i)
    {
        begin("glEnable");
        arg("cap", getCap(i));
        end();
        mgl.glEnable(i);
        checkError();
    }

    public void glEnableClientState(int i)
    {
        begin("glEnableClientState");
        arg("array", getClientState(i));
        end();
        i;
        JVM INSTR tableswitch 32884 32888: default 60
    //                   32884 99
    //                   32885 83
    //                   32886 75
    //                   32887 60
    //                   32888 91;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
        mgl.glEnableClientState(i);
        checkError();
        return;
_L4:
        mColorArrayEnabled = true;
        continue; /* Loop/switch isn't completed */
_L3:
        mNormalArrayEnabled = true;
        continue; /* Loop/switch isn't completed */
_L5:
        mTextureCoordArrayEnabled = true;
        continue; /* Loop/switch isn't completed */
_L2:
        mVertexArrayEnabled = true;
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void glFinish()
    {
        begin("glFinish");
        end();
        mgl.glFinish();
        checkError();
    }

    public void glFlush()
    {
        begin("glFlush");
        end();
        mgl.glFlush();
        checkError();
    }

    public void glFogf(int i, float f)
    {
        begin("glFogf");
        arg("pname", i);
        arg("param", f);
        end();
        mgl.glFogf(i, f);
        checkError();
    }

    public void glFogfv(int i, FloatBuffer floatbuffer)
    {
        begin("glFogfv");
        arg("pname", getFogPName(i));
        arg("params", getFogParamCount(i), floatbuffer);
        end();
        mgl.glFogfv(i, floatbuffer);
        checkError();
    }

    public void glFogfv(int i, float af[], int j)
    {
        begin("glFogfv");
        arg("pname", getFogPName(i));
        arg("params", getFogParamCount(i), af, j);
        arg("offset", j);
        end();
        mgl.glFogfv(i, af, j);
        checkError();
    }

    public void glFogx(int i, int j)
    {
        begin("glFogx");
        arg("pname", getFogPName(i));
        arg("param", j);
        end();
        mgl.glFogx(i, j);
        checkError();
    }

    public void glFogxv(int i, IntBuffer intbuffer)
    {
        begin("glFogxv");
        arg("pname", getFogPName(i));
        arg("params", getFogParamCount(i), intbuffer);
        end();
        mgl.glFogxv(i, intbuffer);
        checkError();
    }

    public void glFogxv(int i, int ai[], int j)
    {
        begin("glFogxv");
        arg("pname", getFogPName(i));
        arg("params", getFogParamCount(i), ai, j);
        arg("offset", j);
        end();
        mgl.glFogxv(i, ai, j);
        checkError();
    }

    public void glFramebufferRenderbufferOES(int i, int j, int k, int l)
    {
        begin("glFramebufferRenderbufferOES");
        arg("target", i);
        arg("attachment", j);
        arg("renderbuffertarget", k);
        arg("renderbuffer", l);
        end();
        mgl11ExtensionPack.glFramebufferRenderbufferOES(i, j, k, l);
        checkError();
    }

    public void glFramebufferTexture2DOES(int i, int j, int k, int l, int i1)
    {
        begin("glFramebufferTexture2DOES");
        arg("target", i);
        arg("attachment", j);
        arg("textarget", k);
        arg("texture", l);
        arg("level", i1);
        end();
        mgl11ExtensionPack.glFramebufferTexture2DOES(i, j, k, l, i1);
        checkError();
    }

    public void glFrontFace(int i)
    {
        begin("glFrontFace");
        arg("mode", i);
        end();
        mgl.glFrontFace(i);
        checkError();
    }

    public void glFrustumf(float f, float f1, float f2, float f3, float f4, float f5)
    {
        begin("glFrustumf");
        arg("left", f);
        arg("right", f1);
        arg("bottom", f2);
        arg("top", f3);
        arg("near", f4);
        arg("far", f5);
        end();
        mgl.glFrustumf(f, f1, f2, f3, f4, f5);
        checkError();
    }

    public void glFrustumx(int i, int j, int k, int l, int i1, int j1)
    {
        begin("glFrustumx");
        arg("left", i);
        arg("right", j);
        arg("bottom", k);
        arg("top", l);
        arg("near", i1);
        arg("far", j1);
        end();
        mgl.glFrustumx(i, j, k, l, i1, j1);
        checkError();
    }

    public void glGenBuffers(int i, IntBuffer intbuffer)
    {
        begin("glGenBuffers");
        arg("n", i);
        arg("buffers", intbuffer.toString());
        end();
        mgl11.glGenBuffers(i, intbuffer);
        checkError();
    }

    public void glGenBuffers(int i, int ai[], int j)
    {
        begin("glGenBuffers");
        arg("n", i);
        arg("buffers", ai.toString());
        arg("offset", j);
        end();
        mgl11.glGenBuffers(i, ai, j);
        checkError();
    }

    public void glGenFramebuffersOES(int i, IntBuffer intbuffer)
    {
        begin("glGenFramebuffersOES");
        arg("n", i);
        arg("framebuffers", intbuffer.toString());
        end();
        mgl11ExtensionPack.glGenFramebuffersOES(i, intbuffer);
        checkError();
    }

    public void glGenFramebuffersOES(int i, int ai[], int j)
    {
        begin("glGenFramebuffersOES");
        arg("n", i);
        arg("framebuffers", ai.toString());
        arg("offset", j);
        end();
        mgl11ExtensionPack.glGenFramebuffersOES(i, ai, j);
        checkError();
    }

    public void glGenRenderbuffersOES(int i, IntBuffer intbuffer)
    {
        begin("glGenRenderbuffersOES");
        arg("n", i);
        arg("renderbuffers", intbuffer.toString());
        end();
        mgl11ExtensionPack.glGenRenderbuffersOES(i, intbuffer);
        checkError();
    }

    public void glGenRenderbuffersOES(int i, int ai[], int j)
    {
        begin("glGenRenderbuffersOES");
        arg("n", i);
        arg("renderbuffers", ai.toString());
        arg("offset", j);
        end();
        mgl11ExtensionPack.glGenRenderbuffersOES(i, ai, j);
        checkError();
    }

    public void glGenTextures(int i, IntBuffer intbuffer)
    {
        begin("glGenTextures");
        arg("n", i);
        arg("textures", intbuffer.toString());
        mgl.glGenTextures(i, intbuffer);
        returns(toString(i, 0, intbuffer));
        checkError();
    }

    public void glGenTextures(int i, int ai[], int j)
    {
        begin("glGenTextures");
        arg("n", i);
        arg("textures", Arrays.toString(ai));
        arg("offset", j);
        mgl.glGenTextures(i, ai, j);
        returns(toString(i, 0, ai, j));
        checkError();
    }

    public void glGenerateMipmapOES(int i)
    {
        begin("glGenerateMipmapOES");
        arg("target", i);
        end();
        mgl11ExtensionPack.glGenerateMipmapOES(i);
        checkError();
    }

    public void glGetBooleanv(int i, IntBuffer intbuffer)
    {
        begin("glGetBooleanv");
        arg("pname", i);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetBooleanv(i, intbuffer);
        checkError();
    }

    public void glGetBooleanv(int i, boolean aflag[], int j)
    {
        begin("glGetBooleanv");
        arg("pname", i);
        arg("params", aflag.toString());
        arg("offset", j);
        end();
        mgl11.glGetBooleanv(i, aflag, j);
        checkError();
    }

    public void glGetBufferParameteriv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetBufferParameteriv");
        arg("target", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetBufferParameteriv(i, j, intbuffer);
        checkError();
    }

    public void glGetBufferParameteriv(int i, int j, int ai[], int k)
    {
        begin("glGetBufferParameteriv");
        arg("target", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetBufferParameteriv(i, j, ai, k);
        checkError();
    }

    public void glGetClipPlanef(int i, FloatBuffer floatbuffer)
    {
        begin("glGetClipPlanef");
        arg("pname", i);
        arg("eqn", floatbuffer.toString());
        end();
        mgl11.glGetClipPlanef(i, floatbuffer);
        checkError();
    }

    public void glGetClipPlanef(int i, float af[], int j)
    {
        begin("glGetClipPlanef");
        arg("pname", i);
        arg("eqn", af.toString());
        arg("offset", j);
        end();
        mgl11.glGetClipPlanef(i, af, j);
        checkError();
    }

    public void glGetClipPlanex(int i, IntBuffer intbuffer)
    {
        begin("glGetClipPlanex");
        arg("pname", i);
        arg("eqn", intbuffer.toString());
        end();
        mgl11.glGetClipPlanex(i, intbuffer);
        checkError();
    }

    public void glGetClipPlanex(int i, int ai[], int j)
    {
        begin("glGetClipPlanex");
        arg("pname", i);
        arg("eqn", ai.toString());
        arg("offset", j);
        end();
        mgl11.glGetClipPlanex(i, ai, j);
    }

    public int glGetError()
    {
        begin("glGetError");
        int i = mgl.glGetError();
        returns(i);
        return i;
    }

    public void glGetFixedv(int i, IntBuffer intbuffer)
    {
        begin("glGetFixedv");
        arg("pname", i);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetFixedv(i, intbuffer);
        checkError();
    }

    public void glGetFixedv(int i, int ai[], int j)
    {
        begin("glGetFixedv");
        arg("pname", i);
        arg("params", ai.toString());
        arg("offset", j);
        end();
        mgl11.glGetFixedv(i, ai, j);
    }

    public void glGetFloatv(int i, FloatBuffer floatbuffer)
    {
        begin("glGetFloatv");
        arg("pname", i);
        arg("params", floatbuffer.toString());
        end();
        mgl11.glGetFloatv(i, floatbuffer);
        checkError();
    }

    public void glGetFloatv(int i, float af[], int j)
    {
        begin("glGetFloatv");
        arg("pname", i);
        arg("params", af.toString());
        arg("offset", j);
        end();
        mgl11.glGetFloatv(i, af, j);
    }

    public void glGetFramebufferAttachmentParameterivOES(int i, int j, int k, IntBuffer intbuffer)
    {
        begin("glGetFramebufferAttachmentParameterivOES");
        arg("target", i);
        arg("attachment", j);
        arg("pname", k);
        arg("params", intbuffer.toString());
        end();
        mgl11ExtensionPack.glGetFramebufferAttachmentParameterivOES(i, j, k, intbuffer);
        checkError();
    }

    public void glGetFramebufferAttachmentParameterivOES(int i, int j, int k, int ai[], int l)
    {
        begin("glGetFramebufferAttachmentParameterivOES");
        arg("target", i);
        arg("attachment", j);
        arg("pname", k);
        arg("params", ai.toString());
        arg("offset", l);
        end();
        mgl11ExtensionPack.glGetFramebufferAttachmentParameterivOES(i, j, k, ai, l);
        checkError();
    }

    public void glGetIntegerv(int i, IntBuffer intbuffer)
    {
        begin("glGetIntegerv");
        arg("pname", getIntegerStateName(i));
        arg("params", intbuffer.toString());
        mgl.glGetIntegerv(i, intbuffer);
        returns(toString(getIntegerStateSize(i), getIntegerStateFormat(i), intbuffer));
        checkError();
    }

    public void glGetIntegerv(int i, int ai[], int j)
    {
        begin("glGetIntegerv");
        arg("pname", getIntegerStateName(i));
        arg("params", Arrays.toString(ai));
        arg("offset", j);
        mgl.glGetIntegerv(i, ai, j);
        returns(toString(getIntegerStateSize(i), getIntegerStateFormat(i), ai, j));
        checkError();
    }

    public void glGetLightfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glGetLightfv");
        arg("light", i);
        arg("pname", j);
        arg("params", floatbuffer.toString());
        end();
        mgl11.glGetLightfv(i, j, floatbuffer);
        checkError();
    }

    public void glGetLightfv(int i, int j, float af[], int k)
    {
        begin("glGetLightfv");
        arg("light", i);
        arg("pname", j);
        arg("params", af.toString());
        arg("offset", k);
        end();
        mgl11.glGetLightfv(i, j, af, k);
        checkError();
    }

    public void glGetLightxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetLightxv");
        arg("light", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetLightxv(i, j, intbuffer);
        checkError();
    }

    public void glGetLightxv(int i, int j, int ai[], int k)
    {
        begin("glGetLightxv");
        arg("light", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetLightxv(i, j, ai, k);
        checkError();
    }

    public void glGetMaterialfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glGetMaterialfv");
        arg("face", i);
        arg("pname", j);
        arg("params", floatbuffer.toString());
        end();
        mgl11.glGetMaterialfv(i, j, floatbuffer);
        checkError();
    }

    public void glGetMaterialfv(int i, int j, float af[], int k)
    {
        begin("glGetMaterialfv");
        arg("face", i);
        arg("pname", j);
        arg("params", af.toString());
        arg("offset", k);
        end();
        mgl11.glGetMaterialfv(i, j, af, k);
        checkError();
    }

    public void glGetMaterialxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetMaterialxv");
        arg("face", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetMaterialxv(i, j, intbuffer);
        checkError();
    }

    public void glGetMaterialxv(int i, int j, int ai[], int k)
    {
        begin("glGetMaterialxv");
        arg("face", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetMaterialxv(i, j, ai, k);
        checkError();
    }

    public void glGetPointerv(int i, Buffer abuffer[])
    {
        begin("glGetPointerv");
        arg("pname", i);
        arg("params", abuffer.toString());
        end();
        mgl11.glGetPointerv(i, abuffer);
        checkError();
    }

    public void glGetRenderbufferParameterivOES(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetRenderbufferParameterivOES");
        arg("target", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11ExtensionPack.glGetRenderbufferParameterivOES(i, j, intbuffer);
        checkError();
    }

    public void glGetRenderbufferParameterivOES(int i, int j, int ai[], int k)
    {
        begin("glGetRenderbufferParameterivOES");
        arg("target", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glGetRenderbufferParameterivOES(i, j, ai, k);
        checkError();
    }

    public String glGetString(int i)
    {
        begin("glGetString");
        arg("name", i);
        String s = mgl.glGetString(i);
        returns(s);
        checkError();
        return s;
    }

    public void glGetTexEnviv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetTexEnviv");
        arg("env", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetTexEnviv(i, j, intbuffer);
        checkError();
    }

    public void glGetTexEnviv(int i, int j, int ai[], int k)
    {
        begin("glGetTexEnviv");
        arg("env", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetTexEnviv(i, j, ai, k);
        checkError();
    }

    public void glGetTexEnvxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetTexEnviv");
        arg("env", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetTexEnvxv(i, j, intbuffer);
        checkError();
    }

    public void glGetTexEnvxv(int i, int j, int ai[], int k)
    {
        begin("glGetTexEnviv");
        arg("env", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetTexEnviv(i, j, ai, k);
        checkError();
    }

    public void glGetTexGenfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glGetTexGenfv");
        arg("coord", i);
        arg("pname", j);
        arg("params", floatbuffer.toString());
        end();
        mgl11ExtensionPack.glGetTexGenfv(i, j, floatbuffer);
        checkError();
    }

    public void glGetTexGenfv(int i, int j, float af[], int k)
    {
        begin("glGetTexGenfv");
        arg("coord", i);
        arg("pname", j);
        arg("params", af.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glGetTexGenfv(i, j, af, k);
        checkError();
    }

    public void glGetTexGeniv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetTexGeniv");
        arg("coord", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11ExtensionPack.glGetTexGeniv(i, j, intbuffer);
        checkError();
    }

    public void glGetTexGeniv(int i, int j, int ai[], int k)
    {
        begin("glGetTexGeniv");
        arg("coord", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glGetTexGeniv(i, j, ai, k);
        checkError();
    }

    public void glGetTexGenxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetTexGenxv");
        arg("coord", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11ExtensionPack.glGetTexGenxv(i, j, intbuffer);
        checkError();
    }

    public void glGetTexGenxv(int i, int j, int ai[], int k)
    {
        begin("glGetTexGenxv");
        arg("coord", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glGetTexGenxv(i, j, ai, k);
        checkError();
    }

    public void glGetTexParameterfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glGetTexParameterfv");
        arg("target", i);
        arg("pname", j);
        arg("params", floatbuffer.toString());
        end();
        mgl11.glGetTexParameterfv(i, j, floatbuffer);
        checkError();
    }

    public void glGetTexParameterfv(int i, int j, float af[], int k)
    {
        begin("glGetTexParameterfv");
        arg("target", i);
        arg("pname", j);
        arg("params", af.toString());
        arg("offset", k);
        end();
        mgl11.glGetTexParameterfv(i, j, af, k);
        checkError();
    }

    public void glGetTexParameteriv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetTexParameteriv");
        arg("target", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetTexParameteriv(i, j, intbuffer);
        checkError();
    }

    public void glGetTexParameteriv(int i, int j, int ai[], int k)
    {
        begin("glGetTexParameteriv");
        arg("target", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetTexEnviv(i, j, ai, k);
        checkError();
    }

    public void glGetTexParameterxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glGetTexParameterxv");
        arg("target", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glGetTexParameterxv(i, j, intbuffer);
        checkError();
    }

    public void glGetTexParameterxv(int i, int j, int ai[], int k)
    {
        begin("glGetTexParameterxv");
        arg("target", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glGetTexParameterxv(i, j, ai, k);
        checkError();
    }

    public void glHint(int i, int j)
    {
        begin("glHint");
        arg("target", getHintTarget(i));
        arg("mode", getHintMode(j));
        end();
        mgl.glHint(i, j);
        checkError();
    }

    public boolean glIsBuffer(int i)
    {
        begin("glIsBuffer");
        arg("buffer", i);
        end();
        boolean flag = mgl11.glIsBuffer(i);
        checkError();
        return flag;
    }

    public boolean glIsEnabled(int i)
    {
        begin("glIsEnabled");
        arg("cap", i);
        end();
        boolean flag = mgl11.glIsEnabled(i);
        checkError();
        return flag;
    }

    public boolean glIsFramebufferOES(int i)
    {
        begin("glIsFramebufferOES");
        arg("framebuffer", i);
        end();
        boolean flag = mgl11ExtensionPack.glIsFramebufferOES(i);
        checkError();
        return flag;
    }

    public boolean glIsRenderbufferOES(int i)
    {
        begin("glIsRenderbufferOES");
        arg("renderbuffer", i);
        end();
        mgl11ExtensionPack.glIsRenderbufferOES(i);
        checkError();
        return false;
    }

    public boolean glIsTexture(int i)
    {
        begin("glIsTexture");
        arg("texture", i);
        end();
        boolean flag = mgl11.glIsTexture(i);
        checkError();
        return flag;
    }

    public void glLightModelf(int i, float f)
    {
        begin("glLightModelf");
        arg("pname", getLightModelPName(i));
        arg("param", f);
        end();
        mgl.glLightModelf(i, f);
        checkError();
    }

    public void glLightModelfv(int i, FloatBuffer floatbuffer)
    {
        begin("glLightModelfv");
        arg("pname", getLightModelPName(i));
        arg("params", getLightModelParamCount(i), floatbuffer);
        end();
        mgl.glLightModelfv(i, floatbuffer);
        checkError();
    }

    public void glLightModelfv(int i, float af[], int j)
    {
        begin("glLightModelfv");
        arg("pname", getLightModelPName(i));
        arg("params", getLightModelParamCount(i), af, j);
        arg("offset", j);
        end();
        mgl.glLightModelfv(i, af, j);
        checkError();
    }

    public void glLightModelx(int i, int j)
    {
        begin("glLightModelx");
        arg("pname", getLightModelPName(i));
        arg("param", j);
        end();
        mgl.glLightModelx(i, j);
        checkError();
    }

    public void glLightModelxv(int i, IntBuffer intbuffer)
    {
        begin("glLightModelfv");
        arg("pname", getLightModelPName(i));
        arg("params", getLightModelParamCount(i), intbuffer);
        end();
        mgl.glLightModelxv(i, intbuffer);
        checkError();
    }

    public void glLightModelxv(int i, int ai[], int j)
    {
        begin("glLightModelxv");
        arg("pname", getLightModelPName(i));
        arg("params", getLightModelParamCount(i), ai, j);
        arg("offset", j);
        end();
        mgl.glLightModelxv(i, ai, j);
        checkError();
    }

    public void glLightf(int i, int j, float f)
    {
        begin("glLightf");
        arg("light", getLightName(i));
        arg("pname", getLightPName(j));
        arg("param", f);
        end();
        mgl.glLightf(i, j, f);
        checkError();
    }

    public void glLightfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glLightfv");
        arg("light", getLightName(i));
        arg("pname", getLightPName(j));
        arg("params", getLightParamCount(j), floatbuffer);
        end();
        mgl.glLightfv(i, j, floatbuffer);
        checkError();
    }

    public void glLightfv(int i, int j, float af[], int k)
    {
        begin("glLightfv");
        arg("light", getLightName(i));
        arg("pname", getLightPName(j));
        arg("params", getLightParamCount(j), af, k);
        arg("offset", k);
        end();
        mgl.glLightfv(i, j, af, k);
        checkError();
    }

    public void glLightx(int i, int j, int k)
    {
        begin("glLightx");
        arg("light", getLightName(i));
        arg("pname", getLightPName(j));
        arg("param", k);
        end();
        mgl.glLightx(i, j, k);
        checkError();
    }

    public void glLightxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glLightxv");
        arg("light", getLightName(i));
        arg("pname", getLightPName(j));
        arg("params", getLightParamCount(j), intbuffer);
        end();
        mgl.glLightxv(i, j, intbuffer);
        checkError();
    }

    public void glLightxv(int i, int j, int ai[], int k)
    {
        begin("glLightxv");
        arg("light", getLightName(i));
        arg("pname", getLightPName(j));
        arg("params", getLightParamCount(j), ai, k);
        arg("offset", k);
        end();
        mgl.glLightxv(i, j, ai, k);
        checkError();
    }

    public void glLineWidth(float f)
    {
        begin("glLineWidth");
        arg("width", f);
        end();
        mgl.glLineWidth(f);
        checkError();
    }

    public void glLineWidthx(int i)
    {
        begin("glLineWidthx");
        arg("width", i);
        end();
        mgl.glLineWidthx(i);
        checkError();
    }

    public void glLoadIdentity()
    {
        begin("glLoadIdentity");
        end();
        mgl.glLoadIdentity();
        checkError();
    }

    public void glLoadMatrixf(FloatBuffer floatbuffer)
    {
        begin("glLoadMatrixf");
        arg("m", 16, floatbuffer);
        end();
        mgl.glLoadMatrixf(floatbuffer);
        checkError();
    }

    public void glLoadMatrixf(float af[], int i)
    {
        begin("glLoadMatrixf");
        arg("m", 16, af, i);
        arg("offset", i);
        end();
        mgl.glLoadMatrixf(af, i);
        checkError();
    }

    public void glLoadMatrixx(IntBuffer intbuffer)
    {
        begin("glLoadMatrixx");
        arg("m", 16, intbuffer);
        end();
        mgl.glLoadMatrixx(intbuffer);
        checkError();
    }

    public void glLoadMatrixx(int ai[], int i)
    {
        begin("glLoadMatrixx");
        arg("m", 16, ai, i);
        arg("offset", i);
        end();
        mgl.glLoadMatrixx(ai, i);
        checkError();
    }

    public void glLoadPaletteFromModelViewMatrixOES()
    {
        begin("glLoadPaletteFromModelViewMatrixOES");
        end();
        mgl11Ext.glLoadPaletteFromModelViewMatrixOES();
        checkError();
    }

    public void glLogicOp(int i)
    {
        begin("glLogicOp");
        arg("opcode", i);
        end();
        mgl.glLogicOp(i);
        checkError();
    }

    public void glMaterialf(int i, int j, float f)
    {
        begin("glMaterialf");
        arg("face", getFaceName(i));
        arg("pname", getMaterialPName(j));
        arg("param", f);
        end();
        mgl.glMaterialf(i, j, f);
        checkError();
    }

    public void glMaterialfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glMaterialfv");
        arg("face", getFaceName(i));
        arg("pname", getMaterialPName(j));
        arg("params", getMaterialParamCount(j), floatbuffer);
        end();
        mgl.glMaterialfv(i, j, floatbuffer);
        checkError();
    }

    public void glMaterialfv(int i, int j, float af[], int k)
    {
        begin("glMaterialfv");
        arg("face", getFaceName(i));
        arg("pname", getMaterialPName(j));
        arg("params", getMaterialParamCount(j), af, k);
        arg("offset", k);
        end();
        mgl.glMaterialfv(i, j, af, k);
        checkError();
    }

    public void glMaterialx(int i, int j, int k)
    {
        begin("glMaterialx");
        arg("face", getFaceName(i));
        arg("pname", getMaterialPName(j));
        arg("param", k);
        end();
        mgl.glMaterialx(i, j, k);
        checkError();
    }

    public void glMaterialxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glMaterialxv");
        arg("face", getFaceName(i));
        arg("pname", getMaterialPName(j));
        arg("params", getMaterialParamCount(j), intbuffer);
        end();
        mgl.glMaterialxv(i, j, intbuffer);
        checkError();
    }

    public void glMaterialxv(int i, int j, int ai[], int k)
    {
        begin("glMaterialxv");
        arg("face", getFaceName(i));
        arg("pname", getMaterialPName(j));
        arg("params", getMaterialParamCount(j), ai, k);
        arg("offset", k);
        end();
        mgl.glMaterialxv(i, j, ai, k);
        checkError();
    }

    public void glMatrixIndexPointerOES(int i, int j, int k, int l)
    {
        begin("glMatrixIndexPointerOES");
        arg("size", i);
        arg("type", j);
        arg("stride", k);
        arg("offset", l);
        end();
        mgl11Ext.glMatrixIndexPointerOES(i, j, k, l);
        checkError();
    }

    public void glMatrixIndexPointerOES(int i, int j, int k, Buffer buffer)
    {
        begin("glMatrixIndexPointerOES");
        argPointer(i, j, k, buffer);
        end();
        mgl11Ext.glMatrixIndexPointerOES(i, j, k, buffer);
        checkError();
    }

    public void glMatrixMode(int i)
    {
        begin("glMatrixMode");
        arg("mode", getMatrixMode(i));
        end();
        mgl.glMatrixMode(i);
        checkError();
    }

    public void glMultMatrixf(FloatBuffer floatbuffer)
    {
        begin("glMultMatrixf");
        arg("m", 16, floatbuffer);
        end();
        mgl.glMultMatrixf(floatbuffer);
        checkError();
    }

    public void glMultMatrixf(float af[], int i)
    {
        begin("glMultMatrixf");
        arg("m", 16, af, i);
        arg("offset", i);
        end();
        mgl.glMultMatrixf(af, i);
        checkError();
    }

    public void glMultMatrixx(IntBuffer intbuffer)
    {
        begin("glMultMatrixx");
        arg("m", 16, intbuffer);
        end();
        mgl.glMultMatrixx(intbuffer);
        checkError();
    }

    public void glMultMatrixx(int ai[], int i)
    {
        begin("glMultMatrixx");
        arg("m", 16, ai, i);
        arg("offset", i);
        end();
        mgl.glMultMatrixx(ai, i);
        checkError();
    }

    public void glMultiTexCoord4f(int i, float f, float f1, float f2, float f3)
    {
        begin("glMultiTexCoord4f");
        arg("target", i);
        arg("s", f);
        arg("t", f1);
        arg("r", f2);
        arg("q", f3);
        end();
        mgl.glMultiTexCoord4f(i, f, f1, f2, f3);
        checkError();
    }

    public void glMultiTexCoord4x(int i, int j, int k, int l, int i1)
    {
        begin("glMultiTexCoord4x");
        arg("target", i);
        arg("s", j);
        arg("t", k);
        arg("r", l);
        arg("q", i1);
        end();
        mgl.glMultiTexCoord4x(i, j, k, l, i1);
        checkError();
    }

    public void glNormal3f(float f, float f1, float f2)
    {
        begin("glNormal3f");
        arg("nx", f);
        arg("ny", f1);
        arg("nz", f2);
        end();
        mgl.glNormal3f(f, f1, f2);
        checkError();
    }

    public void glNormal3x(int i, int j, int k)
    {
        begin("glNormal3x");
        arg("nx", i);
        arg("ny", j);
        arg("nz", k);
        end();
        mgl.glNormal3x(i, j, k);
        checkError();
    }

    public void glNormalPointer(int i, int j, int k)
    {
        begin("glNormalPointer");
        arg("type", i);
        arg("stride", j);
        arg("offset", k);
        end();
        mgl11.glNormalPointer(i, j, k);
    }

    public void glNormalPointer(int i, int j, Buffer buffer)
    {
        begin("glNormalPointer");
        arg("type", i);
        arg("stride", j);
        arg("pointer", buffer.toString());
        end();
        mNormalPointer = new PointerInfo(3, i, j, buffer);
        mgl.glNormalPointer(i, j, buffer);
        checkError();
    }

    public void glOrthof(float f, float f1, float f2, float f3, float f4, float f5)
    {
        begin("glOrthof");
        arg("left", f);
        arg("right", f1);
        arg("bottom", f2);
        arg("top", f3);
        arg("near", f4);
        arg("far", f5);
        end();
        mgl.glOrthof(f, f1, f2, f3, f4, f5);
        checkError();
    }

    public void glOrthox(int i, int j, int k, int l, int i1, int j1)
    {
        begin("glOrthox");
        arg("left", i);
        arg("right", j);
        arg("bottom", k);
        arg("top", l);
        arg("near", i1);
        arg("far", j1);
        end();
        mgl.glOrthox(i, j, k, l, i1, j1);
        checkError();
    }

    public void glPixelStorei(int i, int j)
    {
        begin("glPixelStorei");
        arg("pname", i);
        arg("param", j);
        end();
        mgl.glPixelStorei(i, j);
        checkError();
    }

    public void glPointParameterf(int i, float f)
    {
        begin("glPointParameterf");
        arg("pname", i);
        arg("param", f);
        end();
        mgl11.glPointParameterf(i, f);
        checkError();
    }

    public void glPointParameterfv(int i, FloatBuffer floatbuffer)
    {
        begin("glPointParameterfv");
        arg("pname", i);
        arg("params", floatbuffer.toString());
        end();
        mgl11.glPointParameterfv(i, floatbuffer);
        checkError();
    }

    public void glPointParameterfv(int i, float af[], int j)
    {
        begin("glPointParameterfv");
        arg("pname", i);
        arg("params", af.toString());
        arg("offset", j);
        end();
        mgl11.glPointParameterfv(i, af, j);
        checkError();
    }

    public void glPointParameterx(int i, int j)
    {
        begin("glPointParameterfv");
        arg("pname", i);
        arg("param", j);
        end();
        mgl11.glPointParameterx(i, j);
        checkError();
    }

    public void glPointParameterxv(int i, IntBuffer intbuffer)
    {
        begin("glPointParameterxv");
        arg("pname", i);
        arg("params", intbuffer.toString());
        end();
        mgl11.glPointParameterxv(i, intbuffer);
        checkError();
    }

    public void glPointParameterxv(int i, int ai[], int j)
    {
        begin("glPointParameterxv");
        arg("pname", i);
        arg("params", ai.toString());
        arg("offset", j);
        end();
        mgl11.glPointParameterxv(i, ai, j);
        checkError();
    }

    public void glPointSize(float f)
    {
        begin("glPointSize");
        arg("size", f);
        end();
        mgl.glPointSize(f);
        checkError();
    }

    public void glPointSizePointerOES(int i, int j, Buffer buffer)
    {
        begin("glPointSizePointerOES");
        arg("type", i);
        arg("stride", j);
        arg("params", buffer.toString());
        end();
        mgl11.glPointSizePointerOES(i, j, buffer);
        checkError();
    }

    public void glPointSizex(int i)
    {
        begin("glPointSizex");
        arg("size", i);
        end();
        mgl.glPointSizex(i);
        checkError();
    }

    public void glPolygonOffset(float f, float f1)
    {
        begin("glPolygonOffset");
        arg("factor", f);
        arg("units", f1);
        end();
        mgl.glPolygonOffset(f, f1);
        checkError();
    }

    public void glPolygonOffsetx(int i, int j)
    {
        begin("glPolygonOffsetx");
        arg("factor", i);
        arg("units", j);
        end();
        mgl.glPolygonOffsetx(i, j);
        checkError();
    }

    public void glPopMatrix()
    {
        begin("glPopMatrix");
        end();
        mgl.glPopMatrix();
        checkError();
    }

    public void glPushMatrix()
    {
        begin("glPushMatrix");
        end();
        mgl.glPushMatrix();
        checkError();
    }

    public int glQueryMatrixxOES(IntBuffer intbuffer, IntBuffer intbuffer1)
    {
        begin("glQueryMatrixxOES");
        arg("mantissa", intbuffer.toString());
        arg("exponent", intbuffer1.toString());
        end();
        int i = mgl10Ext.glQueryMatrixxOES(intbuffer, intbuffer1);
        returns(toString(16, 2, intbuffer));
        returns(toString(16, 0, intbuffer1));
        checkError();
        return i;
    }

    public int glQueryMatrixxOES(int ai[], int i, int ai1[], int j)
    {
        begin("glQueryMatrixxOES");
        arg("mantissa", Arrays.toString(ai));
        arg("exponent", Arrays.toString(ai1));
        end();
        int k = mgl10Ext.glQueryMatrixxOES(ai, i, ai1, j);
        returns(toString(16, 2, ai, i));
        returns(toString(16, 0, ai1, j));
        checkError();
        return k;
    }

    public void glReadPixels(int i, int j, int k, int l, int i1, int j1, Buffer buffer)
    {
        begin("glReadPixels");
        arg("x", i);
        arg("y", j);
        arg("width", k);
        arg("height", l);
        arg("format", i1);
        arg("type", j1);
        arg("pixels", buffer.toString());
        end();
        mgl.glReadPixels(i, j, k, l, i1, j1, buffer);
        checkError();
    }

    public void glRenderbufferStorageOES(int i, int j, int k, int l)
    {
        begin("glRenderbufferStorageOES");
        arg("target", i);
        arg("internalformat", j);
        arg("width", k);
        arg("height", l);
        end();
        mgl11ExtensionPack.glRenderbufferStorageOES(i, j, k, l);
        checkError();
    }

    public void glRotatef(float f, float f1, float f2, float f3)
    {
        begin("glRotatef");
        arg("angle", f);
        arg("x", f1);
        arg("y", f2);
        arg("z", f3);
        end();
        mgl.glRotatef(f, f1, f2, f3);
        checkError();
    }

    public void glRotatex(int i, int j, int k, int l)
    {
        begin("glRotatex");
        arg("angle", i);
        arg("x", j);
        arg("y", k);
        arg("z", l);
        end();
        mgl.glRotatex(i, j, k, l);
        checkError();
    }

    public void glSampleCoverage(float f, boolean flag)
    {
        begin("glSampleCoveragex");
        arg("value", f);
        arg("invert", flag);
        end();
        mgl.glSampleCoverage(f, flag);
        checkError();
    }

    public void glSampleCoveragex(int i, boolean flag)
    {
        begin("glSampleCoveragex");
        arg("value", i);
        arg("invert", flag);
        end();
        mgl.glSampleCoveragex(i, flag);
        checkError();
    }

    public void glScalef(float f, float f1, float f2)
    {
        begin("glScalef");
        arg("x", f);
        arg("y", f1);
        arg("z", f2);
        end();
        mgl.glScalef(f, f1, f2);
        checkError();
    }

    public void glScalex(int i, int j, int k)
    {
        begin("glScalex");
        arg("x", i);
        arg("y", j);
        arg("z", k);
        end();
        mgl.glScalex(i, j, k);
        checkError();
    }

    public void glScissor(int i, int j, int k, int l)
    {
        begin("glScissor");
        arg("x", i);
        arg("y", j);
        arg("width", k);
        arg("height", l);
        end();
        mgl.glScissor(i, j, k, l);
        checkError();
    }

    public void glShadeModel(int i)
    {
        begin("glShadeModel");
        arg("mode", getShadeModel(i));
        end();
        mgl.glShadeModel(i);
        checkError();
    }

    public void glStencilFunc(int i, int j, int k)
    {
        begin("glStencilFunc");
        arg("func", i);
        arg("ref", j);
        arg("mask", k);
        end();
        mgl.glStencilFunc(i, j, k);
        checkError();
    }

    public void glStencilMask(int i)
    {
        begin("glStencilMask");
        arg("mask", i);
        end();
        mgl.glStencilMask(i);
        checkError();
    }

    public void glStencilOp(int i, int j, int k)
    {
        begin("glStencilOp");
        arg("fail", i);
        arg("zfail", j);
        arg("zpass", k);
        end();
        mgl.glStencilOp(i, j, k);
        checkError();
    }

    public void glTexCoordPointer(int i, int j, int k, int l)
    {
        begin("glTexCoordPointer");
        arg("size", i);
        arg("type", j);
        arg("stride", k);
        arg("offset", l);
        end();
        mgl11.glTexCoordPointer(i, j, k, l);
    }

    public void glTexCoordPointer(int i, int j, int k, Buffer buffer)
    {
        begin("glTexCoordPointer");
        argPointer(i, j, k, buffer);
        end();
        mTexCoordPointer = new PointerInfo(i, j, k, buffer);
        mgl.glTexCoordPointer(i, j, k, buffer);
        checkError();
    }

    public void glTexEnvf(int i, int j, float f)
    {
        begin("glTexEnvf");
        arg("target", getTextureEnvTarget(i));
        arg("pname", getTextureEnvPName(j));
        arg("param", getTextureEnvParamName(f));
        end();
        mgl.glTexEnvf(i, j, f);
        checkError();
    }

    public void glTexEnvfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glTexEnvfv");
        arg("target", getTextureEnvTarget(i));
        arg("pname", getTextureEnvPName(j));
        arg("params", getTextureEnvParamCount(j), floatbuffer);
        end();
        mgl.glTexEnvfv(i, j, floatbuffer);
        checkError();
    }

    public void glTexEnvfv(int i, int j, float af[], int k)
    {
        begin("glTexEnvfv");
        arg("target", getTextureEnvTarget(i));
        arg("pname", getTextureEnvPName(j));
        arg("params", getTextureEnvParamCount(j), af, k);
        arg("offset", k);
        end();
        mgl.glTexEnvfv(i, j, af, k);
        checkError();
    }

    public void glTexEnvi(int i, int j, int k)
    {
        begin("glTexEnvi");
        arg("target", i);
        arg("pname", j);
        arg("param", k);
        end();
        mgl11.glTexEnvi(i, j, k);
        checkError();
    }

    public void glTexEnviv(int i, int j, IntBuffer intbuffer)
    {
        begin("glTexEnviv");
        arg("target", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glTexEnviv(i, j, intbuffer);
        checkError();
    }

    public void glTexEnviv(int i, int j, int ai[], int k)
    {
        begin("glTexEnviv");
        arg("target", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glTexEnviv(i, j, ai, k);
        checkError();
    }

    public void glTexEnvx(int i, int j, int k)
    {
        begin("glTexEnvx");
        arg("target", getTextureEnvTarget(i));
        arg("pname", getTextureEnvPName(j));
        arg("param", k);
        end();
        mgl.glTexEnvx(i, j, k);
        checkError();
    }

    public void glTexEnvxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glTexEnvxv");
        arg("target", getTextureEnvTarget(i));
        arg("pname", getTextureEnvPName(j));
        arg("params", getTextureEnvParamCount(j), intbuffer);
        end();
        mgl.glTexEnvxv(i, j, intbuffer);
        checkError();
    }

    public void glTexEnvxv(int i, int j, int ai[], int k)
    {
        begin("glTexEnvxv");
        arg("target", getTextureEnvTarget(i));
        arg("pname", getTextureEnvPName(j));
        arg("params", getTextureEnvParamCount(j), ai, k);
        arg("offset", k);
        end();
        mgl.glTexEnvxv(i, j, ai, k);
        checkError();
    }

    public void glTexGenf(int i, int j, float f)
    {
        begin("glTexGenf");
        arg("coord", i);
        arg("pname", j);
        arg("param", f);
        end();
        mgl11ExtensionPack.glTexGenf(i, j, f);
        checkError();
    }

    public void glTexGenfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glTexGenfv");
        arg("coord", i);
        arg("pname", j);
        arg("params", floatbuffer.toString());
        end();
        mgl11ExtensionPack.glTexGenfv(i, j, floatbuffer);
        checkError();
    }

    public void glTexGenfv(int i, int j, float af[], int k)
    {
        begin("glTexGenfv");
        arg("coord", i);
        arg("pname", j);
        arg("params", af.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glTexGenfv(i, j, af, k);
        checkError();
    }

    public void glTexGeni(int i, int j, int k)
    {
        begin("glTexGeni");
        arg("coord", i);
        arg("pname", j);
        arg("param", k);
        end();
        mgl11ExtensionPack.glTexGeni(i, j, k);
        checkError();
    }

    public void glTexGeniv(int i, int j, IntBuffer intbuffer)
    {
        begin("glTexGeniv");
        arg("coord", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11ExtensionPack.glTexGeniv(i, j, intbuffer);
        checkError();
    }

    public void glTexGeniv(int i, int j, int ai[], int k)
    {
        begin("glTexGeniv");
        arg("coord", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glTexGeniv(i, j, ai, k);
        checkError();
    }

    public void glTexGenx(int i, int j, int k)
    {
        begin("glTexGenx");
        arg("coord", i);
        arg("pname", j);
        arg("param", k);
        end();
        mgl11ExtensionPack.glTexGenx(i, j, k);
        checkError();
    }

    public void glTexGenxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glTexGenxv");
        arg("coord", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11ExtensionPack.glTexGenxv(i, j, intbuffer);
        checkError();
    }

    public void glTexGenxv(int i, int j, int ai[], int k)
    {
        begin("glTexGenxv");
        arg("coord", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11ExtensionPack.glTexGenxv(i, j, ai, k);
        checkError();
    }

    public void glTexImage2D(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, Buffer buffer)
    {
        begin("glTexImage2D");
        arg("target", i);
        arg("level", j);
        arg("internalformat", k);
        arg("width", l);
        arg("height", i1);
        arg("border", j1);
        arg("format", k1);
        arg("type", l1);
        arg("pixels", buffer.toString());
        end();
        mgl.glTexImage2D(i, j, k, l, i1, j1, k1, l1, buffer);
        checkError();
    }

    public void glTexParameterf(int i, int j, float f)
    {
        begin("glTexParameterf");
        arg("target", getTextureTarget(i));
        arg("pname", getTexturePName(j));
        arg("param", getTextureParamName(f));
        end();
        mgl.glTexParameterf(i, j, f);
        checkError();
    }

    public void glTexParameterfv(int i, int j, FloatBuffer floatbuffer)
    {
        begin("glTexParameterfv");
        arg("target", i);
        arg("pname", j);
        arg("params", floatbuffer.toString());
        end();
        mgl11.glTexParameterfv(i, j, floatbuffer);
        checkError();
    }

    public void glTexParameterfv(int i, int j, float af[], int k)
    {
        begin("glTexParameterfv");
        arg("target", i);
        arg("pname", j);
        arg("params", af.toString());
        arg("offset", k);
        end();
        mgl11.glTexParameterfv(i, j, af, k);
        checkError();
    }

    public void glTexParameteri(int i, int j, int k)
    {
        begin("glTexParameterxv");
        arg("target", i);
        arg("pname", j);
        arg("param", k);
        end();
        mgl11.glTexParameteri(i, j, k);
        checkError();
    }

    public void glTexParameteriv(int i, int j, IntBuffer intbuffer)
    {
        begin("glTexParameteriv");
        arg("target", getTextureTarget(i));
        arg("pname", getTexturePName(j));
        arg("params", 4, intbuffer);
        end();
        mgl11.glTexParameteriv(i, j, intbuffer);
        checkError();
    }

    public void glTexParameteriv(int i, int j, int ai[], int k)
    {
        begin("glTexParameteriv");
        arg("target", getTextureTarget(i));
        arg("pname", getTexturePName(j));
        arg("params", 4, ai, k);
        end();
        mgl11.glTexParameteriv(i, j, ai, k);
        checkError();
    }

    public void glTexParameterx(int i, int j, int k)
    {
        begin("glTexParameterx");
        arg("target", getTextureTarget(i));
        arg("pname", getTexturePName(j));
        arg("param", k);
        end();
        mgl.glTexParameterx(i, j, k);
        checkError();
    }

    public void glTexParameterxv(int i, int j, IntBuffer intbuffer)
    {
        begin("glTexParameterxv");
        arg("target", i);
        arg("pname", j);
        arg("params", intbuffer.toString());
        end();
        mgl11.glTexParameterxv(i, j, intbuffer);
        checkError();
    }

    public void glTexParameterxv(int i, int j, int ai[], int k)
    {
        begin("glTexParameterxv");
        arg("target", i);
        arg("pname", j);
        arg("params", ai.toString());
        arg("offset", k);
        end();
        mgl11.glTexParameterxv(i, j, ai, k);
        checkError();
    }

    public void glTexSubImage2D(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, Buffer buffer)
    {
        begin("glTexSubImage2D");
        arg("target", getTextureTarget(i));
        arg("level", j);
        arg("xoffset", k);
        arg("yoffset", l);
        arg("width", i1);
        arg("height", j1);
        arg("format", k1);
        arg("type", l1);
        arg("pixels", buffer.toString());
        end();
        mgl.glTexSubImage2D(i, j, k, l, i1, j1, k1, l1, buffer);
        checkError();
    }

    public void glTranslatef(float f, float f1, float f2)
    {
        begin("glTranslatef");
        arg("x", f);
        arg("y", f1);
        arg("z", f2);
        end();
        mgl.glTranslatef(f, f1, f2);
        checkError();
    }

    public void glTranslatex(int i, int j, int k)
    {
        begin("glTranslatex");
        arg("x", i);
        arg("y", j);
        arg("z", k);
        end();
        mgl.glTranslatex(i, j, k);
        checkError();
    }

    public void glVertexPointer(int i, int j, int k, int l)
    {
        begin("glVertexPointer");
        arg("size", i);
        arg("type", j);
        arg("stride", k);
        arg("offset", l);
        end();
        mgl11.glVertexPointer(i, j, k, l);
    }

    public void glVertexPointer(int i, int j, int k, Buffer buffer)
    {
        begin("glVertexPointer");
        argPointer(i, j, k, buffer);
        end();
        mVertexPointer = new PointerInfo(i, j, k, buffer);
        mgl.glVertexPointer(i, j, k, buffer);
        checkError();
    }

    public void glViewport(int i, int j, int k, int l)
    {
        begin("glViewport");
        arg("x", i);
        arg("y", j);
        arg("width", k);
        arg("height", l);
        end();
        mgl.glViewport(i, j, k, l);
        checkError();
    }

    public void glWeightPointerOES(int i, int j, int k, int l)
    {
        begin("glWeightPointerOES");
        arg("size", i);
        arg("type", j);
        arg("stride", k);
        arg("offset", l);
        end();
        mgl11Ext.glWeightPointerOES(i, j, k, l);
        checkError();
    }

    public void glWeightPointerOES(int i, int j, int k, Buffer buffer)
    {
        begin("glWeightPointerOES");
        argPointer(i, j, k, buffer);
        end();
        mgl11Ext.glWeightPointerOES(i, j, k, buffer);
        checkError();
    }

    private static final int FORMAT_FIXED = 2;
    private static final int FORMAT_FLOAT = 1;
    private static final int FORMAT_INT = 0;
    private int mArgCount;
    boolean mColorArrayEnabled;
    private PointerInfo mColorPointer;
    private Writer mLog;
    private boolean mLogArgumentNames;
    boolean mNormalArrayEnabled;
    private PointerInfo mNormalPointer;
    StringBuilder mStringBuilder;
    private PointerInfo mTexCoordPointer;
    boolean mTextureCoordArrayEnabled;
    boolean mVertexArrayEnabled;
    private PointerInfo mVertexPointer;
}
