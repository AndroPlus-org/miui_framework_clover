// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.util.Log;
import java.util.BitSet;

// Referenced classes of package android.renderscript:
//            Byte2, Byte3, Byte4, Short2, 
//            Short3, Short4, Int2, Int3, 
//            Int4, Long2, Long3, Long4, 
//            Float2, Float3, Float4, Double2, 
//            Double3, Double4, Matrix2f, Matrix3f, 
//            Matrix4f, BaseObj, RenderScript, RSIllegalArgumentException

public class FieldPacker
{

    public FieldPacker(int i)
    {
        mPos = 0;
        mLen = i;
        mData = new byte[i];
        mAlignment = new BitSet();
    }

    public FieldPacker(byte abyte0[])
    {
        mPos = abyte0.length;
        mLen = abyte0.length;
        mData = abyte0;
        mAlignment = new BitSet();
    }

    private void add(Object obj)
    {
        if(obj instanceof Boolean)
        {
            addBoolean(((Boolean)obj).booleanValue());
            return;
        }
        if(obj instanceof Byte)
        {
            addI8(((Byte)obj).byteValue());
            return;
        }
        if(obj instanceof Short)
        {
            addI16(((Short)obj).shortValue());
            return;
        }
        if(obj instanceof Integer)
        {
            addI32(((Integer)obj).intValue());
            return;
        }
        if(obj instanceof Long)
        {
            addI64(((Long)obj).longValue());
            return;
        }
        if(obj instanceof Float)
        {
            addF32(((Float)obj).floatValue());
            return;
        }
        if(obj instanceof Double)
        {
            addF64(((Double)obj).doubleValue());
            return;
        }
        if(obj instanceof Byte2)
        {
            addI8((Byte2)obj);
            return;
        }
        if(obj instanceof Byte3)
        {
            addI8((Byte3)obj);
            return;
        }
        if(obj instanceof Byte4)
        {
            addI8((Byte4)obj);
            return;
        }
        if(obj instanceof Short2)
        {
            addI16((Short2)obj);
            return;
        }
        if(obj instanceof Short3)
        {
            addI16((Short3)obj);
            return;
        }
        if(obj instanceof Short4)
        {
            addI16((Short4)obj);
            return;
        }
        if(obj instanceof Int2)
        {
            addI32((Int2)obj);
            return;
        }
        if(obj instanceof Int3)
        {
            addI32((Int3)obj);
            return;
        }
        if(obj instanceof Int4)
        {
            addI32((Int4)obj);
            return;
        }
        if(obj instanceof Long2)
        {
            addI64((Long2)obj);
            return;
        }
        if(obj instanceof Long3)
        {
            addI64((Long3)obj);
            return;
        }
        if(obj instanceof Long4)
        {
            addI64((Long4)obj);
            return;
        }
        if(obj instanceof Float2)
        {
            addF32((Float2)obj);
            return;
        }
        if(obj instanceof Float3)
        {
            addF32((Float3)obj);
            return;
        }
        if(obj instanceof Float4)
        {
            addF32((Float4)obj);
            return;
        }
        if(obj instanceof Double2)
        {
            addF64((Double2)obj);
            return;
        }
        if(obj instanceof Double3)
        {
            addF64((Double3)obj);
            return;
        }
        if(obj instanceof Double4)
        {
            addF64((Double4)obj);
            return;
        }
        if(obj instanceof Matrix2f)
        {
            addMatrix((Matrix2f)obj);
            return;
        }
        if(obj instanceof Matrix3f)
        {
            addMatrix((Matrix3f)obj);
            return;
        }
        if(obj instanceof Matrix4f)
        {
            addMatrix((Matrix4f)obj);
            return;
        }
        if(obj instanceof BaseObj)
        {
            addObj((BaseObj)obj);
            return;
        } else
        {
            return;
        }
    }

    private void addSafely(Object obj)
    {
        int i = mPos;
        do
        {
            boolean flag = false;
            try
            {
                add(obj);
            }
            catch(ArrayIndexOutOfBoundsException arrayindexoutofboundsexception)
            {
                mPos = i;
                resize(mLen * 2);
                flag = true;
            }
        } while(flag);
    }

    static FieldPacker createFromArray(Object aobj[])
    {
        FieldPacker fieldpacker = new FieldPacker(RenderScript.sPointerSize * 8);
        int i = 0;
        for(int j = aobj.length; i < j; i++)
            fieldpacker.addSafely(aobj[i]);

        fieldpacker.resize(fieldpacker.mPos);
        return fieldpacker;
    }

    private boolean resize(int i)
    {
        if(i == mLen)
        {
            return false;
        } else
        {
            byte abyte0[] = new byte[i];
            System.arraycopy(mData, 0, abyte0, 0, mPos);
            mData = abyte0;
            mLen = i;
            return true;
        }
    }

    public void addBoolean(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        addI8((byte)i);
    }

    public void addF32(float f)
    {
        addI32(Float.floatToRawIntBits(f));
    }

    public void addF32(Float2 float2)
    {
        addF32(float2.x);
        addF32(float2.y);
    }

    public void addF32(Float3 float3)
    {
        addF32(float3.x);
        addF32(float3.y);
        addF32(float3.z);
    }

    public void addF32(Float4 float4)
    {
        addF32(float4.x);
        addF32(float4.y);
        addF32(float4.z);
        addF32(float4.w);
    }

    public void addF64(double d)
    {
        addI64(Double.doubleToRawLongBits(d));
    }

    public void addF64(Double2 double2)
    {
        addF64(double2.x);
        addF64(double2.y);
    }

    public void addF64(Double3 double3)
    {
        addF64(double3.x);
        addF64(double3.y);
        addF64(double3.z);
    }

    public void addF64(Double4 double4)
    {
        addF64(double4.x);
        addF64(double4.y);
        addF64(double4.z);
        addF64(double4.w);
    }

    public void addI16(Short2 short2)
    {
        addI16(short2.x);
        addI16(short2.y);
    }

    public void addI16(Short3 short3)
    {
        addI16(short3.x);
        addI16(short3.y);
        addI16(short3.z);
    }

    public void addI16(Short4 short4)
    {
        addI16(short4.x);
        addI16(short4.y);
        addI16(short4.z);
        addI16(short4.w);
    }

    public void addI16(short word0)
    {
        align(2);
        byte abyte0[] = mData;
        int i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(word0 & 0xff);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(word0 >> 8);
    }

    public void addI32(int i)
    {
        align(4);
        byte abyte0[] = mData;
        int j = mPos;
        mPos = j + 1;
        abyte0[j] = (byte)(i & 0xff);
        abyte0 = mData;
        j = mPos;
        mPos = j + 1;
        abyte0[j] = (byte)(i >> 8 & 0xff);
        abyte0 = mData;
        j = mPos;
        mPos = j + 1;
        abyte0[j] = (byte)(i >> 16 & 0xff);
        abyte0 = mData;
        j = mPos;
        mPos = j + 1;
        abyte0[j] = (byte)(i >> 24 & 0xff);
    }

    public void addI32(Int2 int2)
    {
        addI32(int2.x);
        addI32(int2.y);
    }

    public void addI32(Int3 int3)
    {
        addI32(int3.x);
        addI32(int3.y);
        addI32(int3.z);
    }

    public void addI32(Int4 int4)
    {
        addI32(int4.x);
        addI32(int4.y);
        addI32(int4.z);
        addI32(int4.w);
    }

    public void addI64(long l)
    {
        align(8);
        byte abyte0[] = mData;
        int i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 8 & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 16 & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 24 & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 32 & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 40 & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 48 & 255L);
        abyte0 = mData;
        i = mPos;
        mPos = i + 1;
        abyte0[i] = (byte)(int)(l >> 56 & 255L);
    }

    public void addI64(Long2 long2)
    {
        addI64(long2.x);
        addI64(long2.y);
    }

    public void addI64(Long3 long3)
    {
        addI64(long3.x);
        addI64(long3.y);
        addI64(long3.z);
    }

    public void addI64(Long4 long4)
    {
        addI64(long4.x);
        addI64(long4.y);
        addI64(long4.z);
        addI64(long4.w);
    }

    public void addI8(byte byte0)
    {
        byte abyte0[] = mData;
        int i = mPos;
        mPos = i + 1;
        abyte0[i] = byte0;
    }

    public void addI8(Byte2 byte2)
    {
        addI8(byte2.x);
        addI8(byte2.y);
    }

    public void addI8(Byte3 byte3)
    {
        addI8(byte3.x);
        addI8(byte3.y);
        addI8(byte3.z);
    }

    public void addI8(Byte4 byte4)
    {
        addI8(byte4.x);
        addI8(byte4.y);
        addI8(byte4.z);
        addI8(byte4.w);
    }

    public void addMatrix(Matrix2f matrix2f)
    {
        for(int i = 0; i < matrix2f.mMat.length; i++)
            addF32(matrix2f.mMat[i]);

    }

    public void addMatrix(Matrix3f matrix3f)
    {
        for(int i = 0; i < matrix3f.mMat.length; i++)
            addF32(matrix3f.mMat[i]);

    }

    public void addMatrix(Matrix4f matrix4f)
    {
        for(int i = 0; i < matrix4f.mMat.length; i++)
            addF32(matrix4f.mMat[i]);

    }

    public void addObj(BaseObj baseobj)
    {
        if(baseobj != null)
        {
            if(RenderScript.sPointerSize == 8)
            {
                addI64(baseobj.getID(null));
                addI64(0L);
                addI64(0L);
                addI64(0L);
            } else
            {
                addI32((int)baseobj.getID(null));
            }
        } else
        if(RenderScript.sPointerSize == 8)
        {
            addI64(0L);
            addI64(0L);
            addI64(0L);
            addI64(0L);
        } else
        {
            addI32(0);
        }
    }

    public void addU16(int i)
    {
        if(i < 0 || i > 65535)
        {
            Log.e("rs", (new StringBuilder()).append("FieldPacker.addU16( ").append(i).append(" )").toString());
            throw new IllegalArgumentException("Saving value out of range for type");
        } else
        {
            align(2);
            byte abyte0[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte0[j] = (byte)(i & 0xff);
            abyte0 = mData;
            j = mPos;
            mPos = j + 1;
            abyte0[j] = (byte)(i >> 8);
            return;
        }
    }

    public void addU16(Int2 int2)
    {
        addU16(int2.x);
        addU16(int2.y);
    }

    public void addU16(Int3 int3)
    {
        addU16(int3.x);
        addU16(int3.y);
        addU16(int3.z);
    }

    public void addU16(Int4 int4)
    {
        addU16(int4.x);
        addU16(int4.y);
        addU16(int4.z);
        addU16(int4.w);
    }

    public void addU32(long l)
    {
        if(l < 0L || l > 0xffffffffL)
        {
            Log.e("rs", (new StringBuilder()).append("FieldPacker.addU32( ").append(l).append(" )").toString());
            throw new IllegalArgumentException("Saving value out of range for type");
        } else
        {
            align(4);
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 8 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 16 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 24 & 255L);
            return;
        }
    }

    public void addU32(Long2 long2)
    {
        addU32(long2.x);
        addU32(long2.y);
    }

    public void addU32(Long3 long3)
    {
        addU32(long3.x);
        addU32(long3.y);
        addU32(long3.z);
    }

    public void addU32(Long4 long4)
    {
        addU32(long4.x);
        addU32(long4.y);
        addU32(long4.z);
        addU32(long4.w);
    }

    public void addU64(long l)
    {
        if(l < 0L)
        {
            Log.e("rs", (new StringBuilder()).append("FieldPacker.addU64( ").append(l).append(" )").toString());
            throw new IllegalArgumentException("Saving value out of range for type");
        } else
        {
            align(8);
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 8 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 16 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 24 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 32 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 40 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 48 & 255L);
            abyte0 = mData;
            i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)(int)(l >> 56 & 255L);
            return;
        }
    }

    public void addU64(Long2 long2)
    {
        addU64(long2.x);
        addU64(long2.y);
    }

    public void addU64(Long3 long3)
    {
        addU64(long3.x);
        addU64(long3.y);
        addU64(long3.z);
    }

    public void addU64(Long4 long4)
    {
        addU64(long4.x);
        addU64(long4.y);
        addU64(long4.z);
        addU64(long4.w);
    }

    public void addU8(Short2 short2)
    {
        addU8(short2.x);
        addU8(short2.y);
    }

    public void addU8(Short3 short3)
    {
        addU8(short3.x);
        addU8(short3.y);
        addU8(short3.z);
    }

    public void addU8(Short4 short4)
    {
        addU8(short4.x);
        addU8(short4.y);
        addU8(short4.z);
        addU8(short4.w);
    }

    public void addU8(short word0)
    {
        if(word0 < 0 || word0 > 255)
        {
            Log.e("rs", (new StringBuilder()).append("FieldPacker.addU8( ").append(word0).append(" )").toString());
            throw new IllegalArgumentException("Saving value out of range for type");
        } else
        {
            byte abyte0[] = mData;
            int i = mPos;
            mPos = i + 1;
            abyte0[i] = (byte)word0;
            return;
        }
    }

    public void align(int i)
    {
        if(i <= 0 || (i - 1 & i) != 0)
            throw new RSIllegalArgumentException((new StringBuilder()).append("argument must be a non-negative non-zero power of 2: ").append(i).toString());
        while((mPos & i - 1) != 0) 
        {
            mAlignment.flip(mPos);
            byte abyte0[] = mData;
            int j = mPos;
            mPos = j + 1;
            abyte0[j] = (byte)0;
        }
    }

    public final byte[] getData()
    {
        return mData;
    }

    public int getPos()
    {
        return mPos;
    }

    public void reset()
    {
        mPos = 0;
    }

    public void reset(int i)
    {
        if(i < 0 || i > mLen)
        {
            throw new RSIllegalArgumentException((new StringBuilder()).append("out of range argument: ").append(i).toString());
        } else
        {
            mPos = i;
            return;
        }
    }

    public void skip(int i)
    {
        int j = mPos + i;
        if(j < 0 || j > mLen)
        {
            throw new RSIllegalArgumentException((new StringBuilder()).append("out of range argument: ").append(i).toString());
        } else
        {
            mPos = j;
            return;
        }
    }

    public boolean subBoolean()
    {
        return subI8() == 1;
    }

    public Byte2 subByte2()
    {
        Byte2 byte2 = new Byte2();
        byte2.y = subI8();
        byte2.x = subI8();
        return byte2;
    }

    public Byte3 subByte3()
    {
        Byte3 byte3 = new Byte3();
        byte3.z = subI8();
        byte3.y = subI8();
        byte3.x = subI8();
        return byte3;
    }

    public Byte4 subByte4()
    {
        Byte4 byte4 = new Byte4();
        byte4.w = subI8();
        byte4.z = subI8();
        byte4.y = subI8();
        byte4.x = subI8();
        return byte4;
    }

    public Double2 subDouble2()
    {
        Double2 double2 = new Double2();
        double2.y = subF64();
        double2.x = subF64();
        return double2;
    }

    public Double3 subDouble3()
    {
        Double3 double3 = new Double3();
        double3.z = subF64();
        double3.y = subF64();
        double3.x = subF64();
        return double3;
    }

    public Double4 subDouble4()
    {
        Double4 double4 = new Double4();
        double4.w = subF64();
        double4.z = subF64();
        double4.y = subF64();
        double4.x = subF64();
        return double4;
    }

    public float subF32()
    {
        return Float.intBitsToFloat(subI32());
    }

    public double subF64()
    {
        return Double.longBitsToDouble(subI64());
    }

    public Float2 subFloat2()
    {
        Float2 float2 = new Float2();
        float2.y = subF32();
        float2.x = subF32();
        return float2;
    }

    public Float3 subFloat3()
    {
        Float3 float3 = new Float3();
        float3.z = subF32();
        float3.y = subF32();
        float3.x = subF32();
        return float3;
    }

    public Float4 subFloat4()
    {
        Float4 float4 = new Float4();
        float4.w = subF32();
        float4.z = subF32();
        float4.y = subF32();
        float4.x = subF32();
        return float4;
    }

    public short subI16()
    {
        subalign(2);
        byte abyte0[] = mData;
        int i = mPos - 1;
        mPos = i;
        i = (short)((abyte0[i] & 0xff) << 8);
        abyte0 = mData;
        int j = mPos - 1;
        mPos = j;
        return (short)((short)(abyte0[j] & 0xff) | i);
    }

    public int subI32()
    {
        subalign(4);
        byte abyte0[] = mData;
        int i = mPos - 1;
        mPos = i;
        i = abyte0[i];
        abyte0 = mData;
        int j = mPos - 1;
        mPos = j;
        j = abyte0[j];
        abyte0 = mData;
        int k = mPos - 1;
        mPos = k;
        byte byte0 = abyte0[k];
        abyte0 = mData;
        k = mPos - 1;
        mPos = k;
        return (i & 0xff) << 24 | (j & 0xff) << 16 | (byte0 & 0xff) << 8 | abyte0[k] & 0xff;
    }

    public long subI64()
    {
        subalign(8);
        byte abyte0[] = mData;
        int i = mPos - 1;
        mPos = i;
        long l = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        long l1 = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        long l2 = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        long l3 = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        long l4 = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        long l5 = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        long l6 = abyte0[i];
        abyte0 = mData;
        i = mPos - 1;
        mPos = i;
        return 0L | (l & 255L) << 56 | (l1 & 255L) << 48 | (l2 & 255L) << 40 | (l3 & 255L) << 32 | (l4 & 255L) << 24 | (l5 & 255L) << 16 | (l6 & 255L) << 8 | (long)abyte0[i] & 255L;
    }

    public byte subI8()
    {
        subalign(1);
        byte abyte0[] = mData;
        int i = mPos - 1;
        mPos = i;
        return abyte0[i];
    }

    public Int2 subInt2()
    {
        Int2 int2 = new Int2();
        int2.y = subI32();
        int2.x = subI32();
        return int2;
    }

    public Int3 subInt3()
    {
        Int3 int3 = new Int3();
        int3.z = subI32();
        int3.y = subI32();
        int3.x = subI32();
        return int3;
    }

    public Int4 subInt4()
    {
        Int4 int4 = new Int4();
        int4.w = subI32();
        int4.z = subI32();
        int4.y = subI32();
        int4.x = subI32();
        return int4;
    }

    public Long2 subLong2()
    {
        Long2 long2 = new Long2();
        long2.y = subI64();
        long2.x = subI64();
        return long2;
    }

    public Long3 subLong3()
    {
        Long3 long3 = new Long3();
        long3.z = subI64();
        long3.y = subI64();
        long3.x = subI64();
        return long3;
    }

    public Long4 subLong4()
    {
        Long4 long4 = new Long4();
        long4.w = subI64();
        long4.z = subI64();
        long4.y = subI64();
        long4.x = subI64();
        return long4;
    }

    public Matrix2f subMatrix2f()
    {
        Matrix2f matrix2f = new Matrix2f();
        for(int i = matrix2f.mMat.length - 1; i >= 0; i--)
            matrix2f.mMat[i] = subF32();

        return matrix2f;
    }

    public Matrix3f subMatrix3f()
    {
        Matrix3f matrix3f = new Matrix3f();
        for(int i = matrix3f.mMat.length - 1; i >= 0; i--)
            matrix3f.mMat[i] = subF32();

        return matrix3f;
    }

    public Matrix4f subMatrix4f()
    {
        Matrix4f matrix4f = new Matrix4f();
        for(int i = matrix4f.mMat.length - 1; i >= 0; i--)
            matrix4f.mMat[i] = subF32();

        return matrix4f;
    }

    public Short2 subShort2()
    {
        Short2 short2 = new Short2();
        short2.y = subI16();
        short2.x = subI16();
        return short2;
    }

    public Short3 subShort3()
    {
        Short3 short3 = new Short3();
        short3.z = subI16();
        short3.y = subI16();
        short3.x = subI16();
        return short3;
    }

    public Short4 subShort4()
    {
        Short4 short4 = new Short4();
        short4.w = subI16();
        short4.z = subI16();
        short4.y = subI16();
        short4.x = subI16();
        return short4;
    }

    public void subalign(int i)
    {
        if((i - 1 & i) != 0)
            throw new RSIllegalArgumentException((new StringBuilder()).append("argument must be a non-negative non-zero power of 2: ").append(i).toString());
        for(; (mPos & i - 1) != 0; mPos = mPos - 1);
        if(mPos > 0)
            for(; mAlignment.get(mPos - 1); mAlignment.flip(mPos))
                mPos = mPos - 1;

    }

    private BitSet mAlignment;
    private byte mData[];
    private int mLen;
    private int mPos;
}
