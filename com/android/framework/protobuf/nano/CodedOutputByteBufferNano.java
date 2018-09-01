// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;
import java.nio.*;

// Referenced classes of package com.android.framework.protobuf.nano:
//            MessageNano, WireFormatNano

public final class CodedOutputByteBufferNano
{
    public static class OutOfSpaceException extends IOException
    {

        private static final long serialVersionUID = 0x9f95917c52ce6e25L;

        OutOfSpaceException(int i, int j)
        {
            super((new StringBuilder()).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(i).append(" limit ").append(j).append(").").toString());
        }
    }


    private CodedOutputByteBufferNano(ByteBuffer bytebuffer)
    {
        buffer = bytebuffer;
        buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    private CodedOutputByteBufferNano(byte abyte0[], int i, int j)
    {
        this(ByteBuffer.wrap(abyte0, i, j));
    }

    public static int computeBoolSize(int i, boolean flag)
    {
        return computeTagSize(i) + computeBoolSizeNoTag(flag);
    }

    public static int computeBoolSizeNoTag(boolean flag)
    {
        return 1;
    }

    public static int computeBytesSize(int i, int j)
    {
        return computeTagSize(i) + computeBytesSizeNoTag(j);
    }

    public static int computeBytesSize(int i, byte abyte0[])
    {
        return computeTagSize(i) + computeBytesSizeNoTag(abyte0);
    }

    public static int computeBytesSizeNoTag(int i)
    {
        return computeRawVarint32Size(i) + i;
    }

    public static int computeBytesSizeNoTag(byte abyte0[])
    {
        return computeRawVarint32Size(abyte0.length) + abyte0.length;
    }

    public static int computeDoubleSize(int i, double d)
    {
        return computeTagSize(i) + computeDoubleSizeNoTag(d);
    }

    public static int computeDoubleSizeNoTag(double d)
    {
        return 8;
    }

    public static int computeEnumSize(int i, int j)
    {
        return computeTagSize(i) + computeEnumSizeNoTag(j);
    }

    public static int computeEnumSizeNoTag(int i)
    {
        return computeRawVarint32Size(i);
    }

    static int computeFieldSize(int i, int j, Object obj)
    {
        switch(j)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown type: ").append(j).toString());

        case 8: // '\b'
            return computeBoolSize(i, ((Boolean)obj).booleanValue());

        case 12: // '\f'
            return computeBytesSize(i, (byte[])obj);

        case 9: // '\t'
            return computeStringSize(i, (String)obj);

        case 2: // '\002'
            return computeFloatSize(i, ((Float)obj).floatValue());

        case 1: // '\001'
            return computeDoubleSize(i, ((Double)obj).doubleValue());

        case 14: // '\016'
            return computeEnumSize(i, ((Integer)obj).intValue());

        case 7: // '\007'
            return computeFixed32Size(i, ((Integer)obj).intValue());

        case 5: // '\005'
            return computeInt32Size(i, ((Integer)obj).intValue());

        case 13: // '\r'
            return computeUInt32Size(i, ((Integer)obj).intValue());

        case 17: // '\021'
            return computeSInt32Size(i, ((Integer)obj).intValue());

        case 15: // '\017'
            return computeSFixed32Size(i, ((Integer)obj).intValue());

        case 3: // '\003'
            return computeInt64Size(i, ((Long)obj).longValue());

        case 4: // '\004'
            return computeUInt64Size(i, ((Long)obj).longValue());

        case 18: // '\022'
            return computeSInt64Size(i, ((Long)obj).longValue());

        case 6: // '\006'
            return computeFixed64Size(i, ((Long)obj).longValue());

        case 16: // '\020'
            return computeSFixed64Size(i, ((Long)obj).longValue());

        case 11: // '\013'
            return computeMessageSize(i, (MessageNano)obj);

        case 10: // '\n'
            return computeGroupSize(i, (MessageNano)obj);
        }
    }

    public static int computeFixed32Size(int i, int j)
    {
        return computeTagSize(i) + computeFixed32SizeNoTag(j);
    }

    public static int computeFixed32SizeNoTag(int i)
    {
        return 4;
    }

    public static int computeFixed64Size(int i, long l)
    {
        return computeTagSize(i) + computeFixed64SizeNoTag(l);
    }

    public static int computeFixed64SizeNoTag(long l)
    {
        return 8;
    }

    public static int computeFloatSize(int i, float f)
    {
        return computeTagSize(i) + computeFloatSizeNoTag(f);
    }

    public static int computeFloatSizeNoTag(float f)
    {
        return 4;
    }

    public static int computeGroupSize(int i, MessageNano messagenano)
    {
        return computeTagSize(i) * 2 + computeGroupSizeNoTag(messagenano);
    }

    public static int computeGroupSizeNoTag(MessageNano messagenano)
    {
        return messagenano.getSerializedSize();
    }

    public static int computeInt32Size(int i, int j)
    {
        return computeTagSize(i) + computeInt32SizeNoTag(j);
    }

    public static int computeInt32SizeNoTag(int i)
    {
        if(i >= 0)
            return computeRawVarint32Size(i);
        else
            return 10;
    }

    public static int computeInt64Size(int i, long l)
    {
        return computeTagSize(i) + computeInt64SizeNoTag(l);
    }

    public static int computeInt64SizeNoTag(long l)
    {
        return computeRawVarint64Size(l);
    }

    public static int computeMessageSize(int i, MessageNano messagenano)
    {
        return computeTagSize(i) + computeMessageSizeNoTag(messagenano);
    }

    public static int computeMessageSizeNoTag(MessageNano messagenano)
    {
        int i = messagenano.getSerializedSize();
        return computeRawVarint32Size(i) + i;
    }

    public static int computeRawVarint32Size(int i)
    {
        if((i & 0xffffff80) == 0)
            return 1;
        if((i & 0xffffc000) == 0)
            return 2;
        if((0xffe00000 & i) == 0)
            return 3;
        return (0xf0000000 & i) != 0 ? 5 : 4;
    }

    public static int computeRawVarint64Size(long l)
    {
        if((-128L & l) == 0L)
            return 1;
        if((-16384L & l) == 0L)
            return 2;
        if((0xffffffffffe00000L & l) == 0L)
            return 3;
        if((0xfffffffff0000000L & l) == 0L)
            return 4;
        if((0xfffffff800000000L & l) == 0L)
            return 5;
        if((0xfffffc0000000000L & l) == 0L)
            return 6;
        if((0xfffe000000000000L & l) == 0L)
            return 7;
        if((0xff00000000000000L & l) == 0L)
            return 8;
        return (0x8000000000000000L & l) != 0L ? 10 : 9;
    }

    public static int computeSFixed32Size(int i, int j)
    {
        return computeTagSize(i) + computeSFixed32SizeNoTag(j);
    }

    public static int computeSFixed32SizeNoTag(int i)
    {
        return 4;
    }

    public static int computeSFixed64Size(int i, long l)
    {
        return computeTagSize(i) + computeSFixed64SizeNoTag(l);
    }

    public static int computeSFixed64SizeNoTag(long l)
    {
        return 8;
    }

    public static int computeSInt32Size(int i, int j)
    {
        return computeTagSize(i) + computeSInt32SizeNoTag(j);
    }

    public static int computeSInt32SizeNoTag(int i)
    {
        return computeRawVarint32Size(encodeZigZag32(i));
    }

    public static int computeSInt64Size(int i, long l)
    {
        return computeTagSize(i) + computeSInt64SizeNoTag(l);
    }

    public static int computeSInt64SizeNoTag(long l)
    {
        return computeRawVarint64Size(encodeZigZag64(l));
    }

    public static int computeStringSize(int i, String s)
    {
        return computeTagSize(i) + computeStringSizeNoTag(s);
    }

    public static int computeStringSizeNoTag(String s)
    {
        int i = encodedLength(s);
        return computeRawVarint32Size(i) + i;
    }

    public static int computeTagSize(int i)
    {
        return computeRawVarint32Size(WireFormatNano.makeTag(i, 0));
    }

    public static int computeUInt32Size(int i, int j)
    {
        return computeTagSize(i) + computeUInt32SizeNoTag(j);
    }

    public static int computeUInt32SizeNoTag(int i)
    {
        return computeRawVarint32Size(i);
    }

    public static int computeUInt64Size(int i, long l)
    {
        return computeTagSize(i) + computeUInt64SizeNoTag(l);
    }

    public static int computeUInt64SizeNoTag(long l)
    {
        return computeRawVarint64Size(l);
    }

    private static int encode(CharSequence charsequence, byte abyte0[], int i, int j)
    {
        int k;
        int i2;
        k = charsequence.length();
        boolean flag = false;
        i2 = i + j;
        j = ((flag) ? 1 : 0);
        do
        {
            if(j >= k || j + i >= i2)
                break;
            char c = charsequence.charAt(j);
            if(c >= '\200')
                break;
            abyte0[i + j] = (byte)c;
            j++;
        } while(true);
        if(j == k)
            return i + k;
        i += j;
_L2:
        char c1;
        if(j >= k)
            break MISSING_BLOCK_LABEL_494;
        c1 = charsequence.charAt(j);
        if(c1 >= '\200' || i >= i2)
            break; /* Loop/switch isn't completed */
        int l = i + 1;
        abyte0[i] = (byte)c1;
        i = l;
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(c1 < '\u0800' && i <= i2 - 2)
        {
            int i1 = i + 1;
            abyte0[i] = (byte)(c1 >>> 6 | 0x3c0);
            abyte0[i1] = (byte)(c1 & 0x3f | 0x80);
            i = i1 + 1;
        } else
        if((c1 < '\uD800' || '\uDFFF' < c1) && i <= i2 - 3)
        {
            int j2 = i + 1;
            abyte0[i] = (byte)(c1 >>> 12 | 0x1e0);
            int j1 = j2 + 1;
            abyte0[j2] = (byte)(c1 >>> 6 & 0x3f | 0x80);
            i = j1 + 1;
            abyte0[j1] = (byte)(c1 & 0x3f | 0x80);
        } else
        {
label0:
            {
                char c2;
label1:
                {
                    if(i > i2 - 4)
                        break label0;
                    int k1 = j;
                    if(j + 1 != charsequence.length())
                    {
                        j++;
                        c2 = charsequence.charAt(j);
                        if(!(Character.isSurrogatePair(c1, c2) ^ true))
                            break label1;
                        k1 = j;
                    }
                    throw new IllegalArgumentException((new StringBuilder()).append("Unpaired surrogate at index ").append(k1 - 1).toString());
                }
                int l1 = Character.toCodePoint(c1, c2);
                int k2 = i + 1;
                abyte0[i] = (byte)(l1 >>> 18 | 0xf0);
                i = k2 + 1;
                abyte0[k2] = (byte)(l1 >>> 12 & 0x3f | 0x80);
                k2 = i + 1;
                abyte0[i] = (byte)(l1 >>> 6 & 0x3f | 0x80);
                abyte0[k2] = (byte)(l1 & 0x3f | 0x80);
                i = k2 + 1;
            }
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
        throw new ArrayIndexOutOfBoundsException((new StringBuilder()).append("Failed writing ").append(c1).append(" at index ").append(i).toString());
        return i;
    }

    private static void encode(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        if(bytebuffer.isReadOnly())
            throw new ReadOnlyBufferException();
        if(!bytebuffer.hasArray())
            break MISSING_BLOCK_LABEL_71;
        bytebuffer.position(encode(charsequence, bytebuffer.array(), bytebuffer.arrayOffset() + bytebuffer.position(), bytebuffer.remaining()) - bytebuffer.arrayOffset());
_L1:
        return;
        bytebuffer;
        charsequence = new BufferOverflowException();
        charsequence.initCause(bytebuffer);
        throw charsequence;
        encodeDirect(charsequence, bytebuffer);
          goto _L1
    }

    private static void encodeDirect(CharSequence charsequence, ByteBuffer bytebuffer)
    {
        int i;
        int j;
        i = charsequence.length();
        j = 0;
_L2:
        char c;
        if(j >= i)
            break MISSING_BLOCK_LABEL_302;
        c = charsequence.charAt(j);
        if(c >= '\200')
            break; /* Loop/switch isn't completed */
        bytebuffer.put((byte)c);
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(c < '\u0800')
        {
            bytebuffer.put((byte)(c >>> 6 | 0x3c0));
            bytebuffer.put((byte)(c & 0x3f | 0x80));
        } else
        if(c < '\uD800' || '\uDFFF' < c)
        {
            bytebuffer.put((byte)(c >>> 12 | 0x1e0));
            bytebuffer.put((byte)(c >>> 6 & 0x3f | 0x80));
            bytebuffer.put((byte)(c & 0x3f | 0x80));
        } else
        {
            int k;
            char c1;
label0:
            {
                k = j;
                if(j + 1 != charsequence.length())
                {
                    k = j + 1;
                    c1 = charsequence.charAt(k);
                    if(!(Character.isSurrogatePair(c, c1) ^ true))
                        break label0;
                }
                throw new IllegalArgumentException((new StringBuilder()).append("Unpaired surrogate at index ").append(k - 1).toString());
            }
            j = Character.toCodePoint(c, c1);
            bytebuffer.put((byte)(j >>> 18 | 0xf0));
            bytebuffer.put((byte)(j >>> 12 & 0x3f | 0x80));
            bytebuffer.put((byte)(j >>> 6 & 0x3f | 0x80));
            bytebuffer.put((byte)(j & 0x3f | 0x80));
            j = k;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public static int encodeZigZag32(int i)
    {
        return i << 1 ^ i >> 31;
    }

    public static long encodeZigZag64(long l)
    {
        return l << 1 ^ l >> 63;
    }

    private static int encodedLength(CharSequence charsequence)
    {
        int i;
        int k;
label0:
        {
            i = charsequence.length();
            int j = i;
            k = 0;
            int l;
            int i1;
            do
            {
                l = k;
                i1 = j;
                if(k >= i)
                    break;
                l = k;
                i1 = j;
                if(charsequence.charAt(k) >= '\200')
                    break;
                k++;
            } while(true);
            do
            {
                k = i1;
                if(l >= i)
                    break label0;
                k = charsequence.charAt(l);
                if(k >= 2048)
                    break;
                i1 += 127 - k >>> 31;
                l++;
            } while(true);
            k = i1 + encodedLengthGeneral(charsequence, l);
        }
        if(k < i)
            throw new IllegalArgumentException((new StringBuilder()).append("UTF-8 length does not fit in int: ").append((long)k + 0x100000000L).toString());
        else
            return k;
    }

    private static int encodedLengthGeneral(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        int k = 0;
        while(i < j) 
        {
            char c = charsequence.charAt(i);
            int l;
            if(c < '\u0800')
            {
                k += 127 - c >>> 31;
                l = i;
            } else
            {
                int i1 = k + 2;
                l = i;
                k = i1;
                if('\uD800' <= c)
                {
                    l = i;
                    k = i1;
                    if(c <= '\uDFFF')
                    {
                        if(Character.codePointAt(charsequence, i) < 0x10000)
                            throw new IllegalArgumentException((new StringBuilder()).append("Unpaired surrogate at index ").append(i).toString());
                        l = i + 1;
                        k = i1;
                    }
                }
            }
            i = l + 1;
        }
        return k;
    }

    public static CodedOutputByteBufferNano newInstance(byte abyte0[])
    {
        return newInstance(abyte0, 0, abyte0.length);
    }

    public static CodedOutputByteBufferNano newInstance(byte abyte0[], int i, int j)
    {
        return new CodedOutputByteBufferNano(abyte0, i, j);
    }

    public void checkNoSpaceLeft()
    {
        if(spaceLeft() != 0)
            throw new IllegalStateException("Did not write as much data as expected.");
        else
            return;
    }

    public int position()
    {
        return buffer.position();
    }

    public void reset()
    {
        buffer.clear();
    }

    public int spaceLeft()
    {
        return buffer.remaining();
    }

    public void writeBool(int i, boolean flag)
        throws IOException
    {
        writeTag(i, 0);
        writeBoolNoTag(flag);
    }

    public void writeBoolNoTag(boolean flag)
        throws IOException
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        writeRawByte(i);
    }

    public void writeBytes(int i, byte abyte0[])
        throws IOException
    {
        writeTag(i, 2);
        writeBytesNoTag(abyte0);
    }

    public void writeBytes(int i, byte abyte0[], int j, int k)
        throws IOException
    {
        writeTag(i, 2);
        writeBytesNoTag(abyte0, j, k);
    }

    public void writeBytesNoTag(byte abyte0[])
        throws IOException
    {
        writeRawVarint32(abyte0.length);
        writeRawBytes(abyte0);
    }

    public void writeBytesNoTag(byte abyte0[], int i, int j)
        throws IOException
    {
        writeRawVarint32(j);
        writeRawBytes(abyte0, i, j);
    }

    public void writeDouble(int i, double d)
        throws IOException
    {
        writeTag(i, 1);
        writeDoubleNoTag(d);
    }

    public void writeDoubleNoTag(double d)
        throws IOException
    {
        writeRawLittleEndian64(Double.doubleToLongBits(d));
    }

    public void writeEnum(int i, int j)
        throws IOException
    {
        writeTag(i, 0);
        writeEnumNoTag(j);
    }

    public void writeEnumNoTag(int i)
        throws IOException
    {
        writeRawVarint32(i);
    }

    void writeField(int i, int j, Object obj)
        throws IOException
    {
        j;
        JVM INSTR tableswitch 1 18: default 88
    //                   1 115
    //                   2 128
    //                   3 143
    //                   4 158
    //                   5 173
    //                   6 188
    //                   7 203
    //                   8 218
    //                   9 233
    //                   10 359
    //                   11 347
    //                   12 245
    //                   13 257
    //                   14 272
    //                   15 287
    //                   16 302
    //                   17 317
    //                   18 332;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19
_L1:
        throw new IOException((new StringBuilder()).append("Unknown type: ").append(j).toString());
_L2:
        writeDouble(i, ((Double)obj).doubleValue());
_L21:
        return;
_L3:
        writeFloat(i, ((Float)obj).floatValue());
        continue; /* Loop/switch isn't completed */
_L4:
        writeInt64(i, ((Long)obj).longValue());
        continue; /* Loop/switch isn't completed */
_L5:
        writeUInt64(i, ((Long)obj).longValue());
        continue; /* Loop/switch isn't completed */
_L6:
        writeInt32(i, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L7:
        writeFixed64(i, ((Long)obj).longValue());
        continue; /* Loop/switch isn't completed */
_L8:
        writeFixed32(i, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L9:
        writeBool(i, ((Boolean)obj).booleanValue());
        continue; /* Loop/switch isn't completed */
_L10:
        writeString(i, (String)obj);
        continue; /* Loop/switch isn't completed */
_L13:
        writeBytes(i, (byte[])obj);
        continue; /* Loop/switch isn't completed */
_L14:
        writeUInt32(i, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L15:
        writeEnum(i, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L16:
        writeSFixed32(i, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L17:
        writeSFixed64(i, ((Long)obj).longValue());
        continue; /* Loop/switch isn't completed */
_L18:
        writeSInt32(i, ((Integer)obj).intValue());
        continue; /* Loop/switch isn't completed */
_L19:
        writeSInt64(i, ((Long)obj).longValue());
        continue; /* Loop/switch isn't completed */
_L12:
        writeMessage(i, (MessageNano)obj);
        continue; /* Loop/switch isn't completed */
_L11:
        writeGroup(i, (MessageNano)obj);
        if(true) goto _L21; else goto _L20
_L20:
    }

    public void writeFixed32(int i, int j)
        throws IOException
    {
        writeTag(i, 5);
        writeFixed32NoTag(j);
    }

    public void writeFixed32NoTag(int i)
        throws IOException
    {
        writeRawLittleEndian32(i);
    }

    public void writeFixed64(int i, long l)
        throws IOException
    {
        writeTag(i, 1);
        writeFixed64NoTag(l);
    }

    public void writeFixed64NoTag(long l)
        throws IOException
    {
        writeRawLittleEndian64(l);
    }

    public void writeFloat(int i, float f)
        throws IOException
    {
        writeTag(i, 5);
        writeFloatNoTag(f);
    }

    public void writeFloatNoTag(float f)
        throws IOException
    {
        writeRawLittleEndian32(Float.floatToIntBits(f));
    }

    public void writeGroup(int i, MessageNano messagenano)
        throws IOException
    {
        writeTag(i, 3);
        writeGroupNoTag(messagenano);
        writeTag(i, 4);
    }

    public void writeGroupNoTag(MessageNano messagenano)
        throws IOException
    {
        messagenano.writeTo(this);
    }

    public void writeInt32(int i, int j)
        throws IOException
    {
        writeTag(i, 0);
        writeInt32NoTag(j);
    }

    public void writeInt32NoTag(int i)
        throws IOException
    {
        if(i >= 0)
            writeRawVarint32(i);
        else
            writeRawVarint64(i);
    }

    public void writeInt64(int i, long l)
        throws IOException
    {
        writeTag(i, 0);
        writeInt64NoTag(l);
    }

    public void writeInt64NoTag(long l)
        throws IOException
    {
        writeRawVarint64(l);
    }

    public void writeMessage(int i, MessageNano messagenano)
        throws IOException
    {
        writeTag(i, 2);
        writeMessageNoTag(messagenano);
    }

    public void writeMessageNoTag(MessageNano messagenano)
        throws IOException
    {
        writeRawVarint32(messagenano.getCachedSize());
        messagenano.writeTo(this);
    }

    public void writeRawByte(byte byte0)
        throws IOException
    {
        if(!buffer.hasRemaining())
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        } else
        {
            buffer.put(byte0);
            return;
        }
    }

    public void writeRawByte(int i)
        throws IOException
    {
        writeRawByte((byte)i);
    }

    public void writeRawBytes(byte abyte0[])
        throws IOException
    {
        writeRawBytes(abyte0, 0, abyte0.length);
    }

    public void writeRawBytes(byte abyte0[], int i, int j)
        throws IOException
    {
        if(buffer.remaining() >= j)
        {
            buffer.put(abyte0, i, j);
            return;
        } else
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        }
    }

    public void writeRawLittleEndian32(int i)
        throws IOException
    {
        if(buffer.remaining() < 4)
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        } else
        {
            buffer.putInt(i);
            return;
        }
    }

    public void writeRawLittleEndian64(long l)
        throws IOException
    {
        if(buffer.remaining() < 8)
        {
            throw new OutOfSpaceException(buffer.position(), buffer.limit());
        } else
        {
            buffer.putLong(l);
            return;
        }
    }

    public void writeRawVarint32(int i)
        throws IOException
    {
        do
        {
            if((i & 0xffffff80) == 0)
            {
                writeRawByte(i);
                return;
            }
            writeRawByte(i & 0x7f | 0x80);
            i >>>= 7;
        } while(true);
    }

    public void writeRawVarint64(long l)
        throws IOException
    {
        do
        {
            if((-128L & l) == 0L)
            {
                writeRawByte((int)l);
                return;
            }
            writeRawByte((int)l & 0x7f | 0x80);
            l >>>= 7;
        } while(true);
    }

    public void writeSFixed32(int i, int j)
        throws IOException
    {
        writeTag(i, 5);
        writeSFixed32NoTag(j);
    }

    public void writeSFixed32NoTag(int i)
        throws IOException
    {
        writeRawLittleEndian32(i);
    }

    public void writeSFixed64(int i, long l)
        throws IOException
    {
        writeTag(i, 1);
        writeSFixed64NoTag(l);
    }

    public void writeSFixed64NoTag(long l)
        throws IOException
    {
        writeRawLittleEndian64(l);
    }

    public void writeSInt32(int i, int j)
        throws IOException
    {
        writeTag(i, 0);
        writeSInt32NoTag(j);
    }

    public void writeSInt32NoTag(int i)
        throws IOException
    {
        writeRawVarint32(encodeZigZag32(i));
    }

    public void writeSInt64(int i, long l)
        throws IOException
    {
        writeTag(i, 0);
        writeSInt64NoTag(l);
    }

    public void writeSInt64NoTag(long l)
        throws IOException
    {
        writeRawVarint64(encodeZigZag64(l));
    }

    public void writeString(int i, String s)
        throws IOException
    {
        writeTag(i, 2);
        writeStringNoTag(s);
    }

    public void writeStringNoTag(String s)
        throws IOException
    {
        int i;
        int j;
        BufferOverflowException bufferoverflowexception;
        i = computeRawVarint32Size(s.length());
        if(i != computeRawVarint32Size(s.length() * 3))
            break MISSING_BLOCK_LABEL_151;
        j = buffer.position();
        if(buffer.remaining() < i)
        {
            s = JVM INSTR new #6   <Class CodedOutputByteBufferNano$OutOfSpaceException>;
            s.OutOfSpaceException(j + i, buffer.limit());
            throw s;
        }
        buffer.position(j + i);
        encode(s, buffer);
        int k = buffer.position();
        buffer.position(j);
        writeRawVarint32(k - j - i);
        buffer.position(k);
_L1:
        return;
        try
        {
            writeRawVarint32(encodedLength(s));
            encode(s, buffer);
        }
        // Misplaced declaration of an exception variable
        catch(BufferOverflowException bufferoverflowexception)
        {
            s = new OutOfSpaceException(buffer.position(), buffer.limit());
            s.initCause(bufferoverflowexception);
            throw s;
        }
          goto _L1
    }

    public void writeTag(int i, int j)
        throws IOException
    {
        writeRawVarint32(WireFormatNano.makeTag(i, j));
    }

    public void writeUInt32(int i, int j)
        throws IOException
    {
        writeTag(i, 0);
        writeUInt32NoTag(j);
    }

    public void writeUInt32NoTag(int i)
        throws IOException
    {
        writeRawVarint32(i);
    }

    public void writeUInt64(int i, long l)
        throws IOException
    {
        writeTag(i, 0);
        writeUInt64NoTag(l);
    }

    public void writeUInt64NoTag(long l)
        throws IOException
    {
        writeRawVarint64(l);
    }

    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    public static final int LITTLE_ENDIAN_64_SIZE = 8;
    private static final int MAX_UTF8_EXPANSION = 3;
    private final ByteBuffer buffer;
}
