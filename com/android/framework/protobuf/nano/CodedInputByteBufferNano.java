// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;

// Referenced classes of package com.android.framework.protobuf.nano:
//            InvalidProtocolBufferNanoException, WireFormatNano, MessageNano, InternalNano

public final class CodedInputByteBufferNano
{

    private CodedInputByteBufferNano(byte abyte0[], int i, int j)
    {
        currentLimit = 0x7fffffff;
        recursionLimit = 64;
        sizeLimit = 0x4000000;
        buffer = abyte0;
        bufferStart = i;
        bufferSize = i + j;
        bufferPos = i;
    }

    public static int decodeZigZag32(int i)
    {
        return i >>> 1 ^ -(i & 1);
    }

    public static long decodeZigZag64(long l)
    {
        return l >>> 1 ^ -(1L & l);
    }

    public static CodedInputByteBufferNano newInstance(byte abyte0[])
    {
        return newInstance(abyte0, 0, abyte0.length);
    }

    public static CodedInputByteBufferNano newInstance(byte abyte0[], int i, int j)
    {
        return new CodedInputByteBufferNano(abyte0, i, j);
    }

    private void recomputeBufferSizeAfterLimit()
    {
        bufferSize = bufferSize + bufferSizeAfterLimit;
        int i = bufferSize;
        if(i > currentLimit)
        {
            bufferSizeAfterLimit = i - currentLimit;
            bufferSize = bufferSize - bufferSizeAfterLimit;
        } else
        {
            bufferSizeAfterLimit = 0;
        }
    }

    public void checkLastTagWas(int i)
        throws InvalidProtocolBufferNanoException
    {
        if(lastTag != i)
            throw InvalidProtocolBufferNanoException.invalidEndTag();
        else
            return;
    }

    public int getAbsolutePosition()
    {
        return bufferPos;
    }

    public byte[] getBuffer()
    {
        return buffer;
    }

    public int getBytesUntilLimit()
    {
        if(currentLimit == 0x7fffffff)
        {
            return -1;
        } else
        {
            int i = bufferPos;
            return currentLimit - i;
        }
    }

    public byte[] getData(int i, int j)
    {
        if(j == 0)
        {
            return WireFormatNano.EMPTY_BYTES;
        } else
        {
            byte abyte0[] = new byte[j];
            int k = bufferStart;
            System.arraycopy(buffer, k + i, abyte0, 0, j);
            return abyte0;
        }
    }

    public int getPosition()
    {
        return bufferPos - bufferStart;
    }

    public boolean isAtEnd()
    {
        boolean flag;
        if(bufferPos == bufferSize)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void popLimit(int i)
    {
        currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public int pushLimit(int i)
        throws InvalidProtocolBufferNanoException
    {
        if(i < 0)
            throw InvalidProtocolBufferNanoException.negativeSize();
        int j = i + bufferPos;
        i = currentLimit;
        if(j > i)
        {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        } else
        {
            currentLimit = j;
            recomputeBufferSizeAfterLimit();
            return i;
        }
    }

    public boolean readBool()
        throws IOException
    {
        boolean flag = false;
        if(readRawVarint32() != 0)
            flag = true;
        return flag;
    }

    public byte[] readBytes()
        throws IOException
    {
        int i = readRawVarint32();
        if(i <= bufferSize - bufferPos && i > 0)
        {
            byte abyte0[] = new byte[i];
            System.arraycopy(buffer, bufferPos, abyte0, 0, i);
            bufferPos = bufferPos + i;
            return abyte0;
        }
        if(i == 0)
            return WireFormatNano.EMPTY_BYTES;
        else
            return readRawBytes(i);
    }

    public double readDouble()
        throws IOException
    {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public int readEnum()
        throws IOException
    {
        return readRawVarint32();
    }

    public int readFixed32()
        throws IOException
    {
        return readRawLittleEndian32();
    }

    public long readFixed64()
        throws IOException
    {
        return readRawLittleEndian64();
    }

    public float readFloat()
        throws IOException
    {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public void readGroup(MessageNano messagenano, int i)
        throws IOException
    {
        if(recursionDepth >= recursionLimit)
        {
            throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
        } else
        {
            recursionDepth = recursionDepth + 1;
            messagenano.mergeFrom(this);
            checkLastTagWas(WireFormatNano.makeTag(i, 4));
            recursionDepth = recursionDepth - 1;
            return;
        }
    }

    public int readInt32()
        throws IOException
    {
        return readRawVarint32();
    }

    public long readInt64()
        throws IOException
    {
        return readRawVarint64();
    }

    public void readMessage(MessageNano messagenano)
        throws IOException
    {
        int i = readRawVarint32();
        if(recursionDepth >= recursionLimit)
        {
            throw InvalidProtocolBufferNanoException.recursionLimitExceeded();
        } else
        {
            i = pushLimit(i);
            recursionDepth = recursionDepth + 1;
            messagenano.mergeFrom(this);
            checkLastTagWas(0);
            recursionDepth = recursionDepth - 1;
            popLimit(i);
            return;
        }
    }

    Object readPrimitiveField(int i)
        throws IOException
    {
        switch(i)
        {
        case 10: // '\n'
        case 11: // '\013'
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown type ").append(i).toString());

        case 1: // '\001'
            return Double.valueOf(readDouble());

        case 2: // '\002'
            return Float.valueOf(readFloat());

        case 3: // '\003'
            return Long.valueOf(readInt64());

        case 4: // '\004'
            return Long.valueOf(readUInt64());

        case 5: // '\005'
            return Integer.valueOf(readInt32());

        case 6: // '\006'
            return Long.valueOf(readFixed64());

        case 7: // '\007'
            return Integer.valueOf(readFixed32());

        case 8: // '\b'
            return Boolean.valueOf(readBool());

        case 9: // '\t'
            return readString();

        case 12: // '\f'
            return readBytes();

        case 13: // '\r'
            return Integer.valueOf(readUInt32());

        case 14: // '\016'
            return Integer.valueOf(readEnum());

        case 15: // '\017'
            return Integer.valueOf(readSFixed32());

        case 16: // '\020'
            return Long.valueOf(readSFixed64());

        case 17: // '\021'
            return Integer.valueOf(readSInt32());

        case 18: // '\022'
            return Long.valueOf(readSInt64());
        }
    }

    public byte readRawByte()
        throws IOException
    {
        if(bufferPos == bufferSize)
        {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        } else
        {
            byte abyte0[] = buffer;
            int i = bufferPos;
            bufferPos = i + 1;
            return abyte0[i];
        }
    }

    public byte[] readRawBytes(int i)
        throws IOException
    {
        if(i < 0)
            throw InvalidProtocolBufferNanoException.negativeSize();
        if(bufferPos + i > currentLimit)
        {
            skipRawBytes(currentLimit - bufferPos);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if(i <= bufferSize - bufferPos)
        {
            byte abyte0[] = new byte[i];
            System.arraycopy(buffer, bufferPos, abyte0, 0, i);
            bufferPos = bufferPos + i;
            return abyte0;
        } else
        {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
    }

    public int readRawLittleEndian32()
        throws IOException
    {
        return readRawByte() & 0xff | (readRawByte() & 0xff) << 8 | (readRawByte() & 0xff) << 16 | (readRawByte() & 0xff) << 24;
    }

    public long readRawLittleEndian64()
        throws IOException
    {
        int i = readRawByte();
        int j = readRawByte();
        int k = readRawByte();
        int l = readRawByte();
        int i1 = readRawByte();
        int j1 = readRawByte();
        int k1 = readRawByte();
        int l1 = readRawByte();
        return (long)i & 255L | ((long)j & 255L) << 8 | ((long)k & 255L) << 16 | ((long)l & 255L) << 24 | ((long)i1 & 255L) << 32 | ((long)j1 & 255L) << 40 | ((long)k1 & 255L) << 48 | ((long)l1 & 255L) << 56;
    }

    public int readRawVarint32()
        throws IOException
    {
        int i;
        int k;
        i = readRawByte();
        if(i >= 0)
            return i;
        i &= 0x7f;
        k = readRawByte();
        if(k < 0) goto _L2; else goto _L1
_L1:
        i |= k << 7;
_L4:
        return i;
_L2:
        k = i | (k & 0x7f) << 7;
        i = readRawByte();
        if(i >= 0)
        {
            i = k | i << 14;
            continue; /* Loop/switch isn't completed */
        }
        k |= (i & 0x7f) << 14;
        i = readRawByte();
        if(i < 0)
            break; /* Loop/switch isn't completed */
        i = k | i << 21;
        if(true) goto _L4; else goto _L3
_L3:
        byte byte0 = readRawByte();
        k = k | (i & 0x7f) << 21 | byte0 << 28;
        i = k;
        if(byte0 < 0)
        {
            for(int j = 0; j < 5; j++)
                if(readRawByte() >= 0)
                    return k;

            throw InvalidProtocolBufferNanoException.malformedVarint();
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public long readRawVarint64()
        throws IOException
    {
        int i = 0;
        long l = 0L;
        for(; i < 64; i += 7)
        {
            byte byte0 = readRawByte();
            l |= (long)(byte0 & 0x7f) << i;
            if((byte0 & 0x80) == 0)
                return l;
        }

        throw InvalidProtocolBufferNanoException.malformedVarint();
    }

    public int readSFixed32()
        throws IOException
    {
        return readRawLittleEndian32();
    }

    public long readSFixed64()
        throws IOException
    {
        return readRawLittleEndian64();
    }

    public int readSInt32()
        throws IOException
    {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64()
        throws IOException
    {
        return decodeZigZag64(readRawVarint64());
    }

    public String readString()
        throws IOException
    {
        int i = readRawVarint32();
        if(i <= bufferSize - bufferPos && i > 0)
        {
            String s = new String(buffer, bufferPos, i, InternalNano.UTF_8);
            bufferPos = bufferPos + i;
            return s;
        } else
        {
            return new String(readRawBytes(i), InternalNano.UTF_8);
        }
    }

    public int readTag()
        throws IOException
    {
        if(isAtEnd())
        {
            lastTag = 0;
            return 0;
        }
        lastTag = readRawVarint32();
        if(lastTag == 0)
            throw InvalidProtocolBufferNanoException.invalidTag();
        else
            return lastTag;
    }

    public int readUInt32()
        throws IOException
    {
        return readRawVarint32();
    }

    public long readUInt64()
        throws IOException
    {
        return readRawVarint64();
    }

    public void resetSizeCounter()
    {
    }

    public void rewindToPosition(int i)
    {
        if(i > bufferPos - bufferStart)
            throw new IllegalArgumentException((new StringBuilder()).append("Position ").append(i).append(" is beyond current ").append(bufferPos - bufferStart).toString());
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad position ").append(i).toString());
        } else
        {
            bufferPos = bufferStart + i;
            return;
        }
    }

    public int setRecursionLimit(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Recursion limit cannot be negative: ").append(i).toString());
        } else
        {
            int j = recursionLimit;
            recursionLimit = i;
            return j;
        }
    }

    public int setSizeLimit(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Size limit cannot be negative: ").append(i).toString());
        } else
        {
            int j = sizeLimit;
            sizeLimit = i;
            return j;
        }
    }

    public boolean skipField(int i)
        throws IOException
    {
        switch(WireFormatNano.getTagWireType(i))
        {
        default:
            throw InvalidProtocolBufferNanoException.invalidWireType();

        case 0: // '\0'
            readInt32();
            return true;

        case 1: // '\001'
            readRawLittleEndian64();
            return true;

        case 2: // '\002'
            skipRawBytes(readRawVarint32());
            return true;

        case 3: // '\003'
            skipMessage();
            checkLastTagWas(WireFormatNano.makeTag(WireFormatNano.getTagFieldNumber(i), 4));
            return true;

        case 4: // '\004'
            return false;

        case 5: // '\005'
            readRawLittleEndian32();
            break;
        }
        return true;
    }

    public void skipMessage()
        throws IOException
    {
        int i;
        do
            i = readTag();
        while(i != 0 && !(skipField(i) ^ true));
    }

    public void skipRawBytes(int i)
        throws IOException
    {
        if(i < 0)
            throw InvalidProtocolBufferNanoException.negativeSize();
        if(bufferPos + i > currentLimit)
        {
            skipRawBytes(currentLimit - bufferPos);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if(i <= bufferSize - bufferPos)
        {
            bufferPos = bufferPos + i;
            return;
        } else
        {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
    }

    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 0x4000000;
    private final byte buffer[];
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int bufferStart;
    private int currentLimit;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private int sizeLimit;
}
