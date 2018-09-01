// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.proto;

import android.util.Log;
import java.io.*;

// Referenced classes of package android.util.proto:
//            EncodedBuffer, ProtoParseException

public final class ProtoOutputStream
{

    public ProtoOutputStream()
    {
        this(0);
    }

    public ProtoOutputStream(int i)
    {
        mNextObjectId = -1;
        mBuffer = new EncodedBuffer(i);
    }

    public ProtoOutputStream(FileDescriptor filedescriptor)
    {
        this(((OutputStream) (new FileOutputStream(filedescriptor))));
    }

    public ProtoOutputStream(OutputStream outputstream)
    {
        this();
        mStream = outputstream;
    }

    private void assertNotCompacted()
    {
        if(mCompacted)
            throw new IllegalArgumentException("write called after compact");
        else
            return;
    }

    public static int checkFieldId(long l, long l1)
    {
        long l2 = l & 0xf0000000000L;
        long l3 = l & 0xff00000000L;
        long l4 = l1 & 0xf0000000000L;
        l1 &= 0xff00000000L;
        if((int)l == 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid proto field ").append((int)l).append(" fieldId=").append(Long.toHexString(l)).toString());
        if(l3 != l1 || l2 != l4 && (l2 != 0x50000000000L || l4 != 0x20000000000L))
        {
            String s = getFieldCountString(l2);
            Object obj = getFieldTypeString(l3);
            if(obj != null && s != null)
            {
                StringBuilder stringbuilder = new StringBuilder();
                if(l1 == 0x1100000000L)
                    stringbuilder.append("start");
                else
                    stringbuilder.append("write");
                stringbuilder.append(getFieldCountString(l4));
                stringbuilder.append(getFieldTypeString(l1));
                stringbuilder.append(" called for field ");
                stringbuilder.append((int)l);
                stringbuilder.append(" which should be used with ");
                if(l3 == 0x1100000000L)
                    stringbuilder.append("start");
                else
                    stringbuilder.append("write");
                stringbuilder.append(s);
                stringbuilder.append(((String) (obj)));
                if(l2 == 0x50000000000L)
                {
                    stringbuilder.append(" or writeRepeated");
                    stringbuilder.append(((String) (obj)));
                }
                stringbuilder.append('.');
                throw new IllegalArgumentException(stringbuilder.toString());
            }
            obj = new StringBuilder();
            if(l1 == 0x1100000000L)
                ((StringBuilder) (obj)).append("start");
            else
                ((StringBuilder) (obj)).append("write");
            ((StringBuilder) (obj)).append(getFieldCountString(l4));
            ((StringBuilder) (obj)).append(getFieldTypeString(l1));
            ((StringBuilder) (obj)).append(" called with an invalid fieldId: 0x");
            ((StringBuilder) (obj)).append(Long.toHexString(l));
            ((StringBuilder) (obj)).append(". The proto field ID might be ");
            ((StringBuilder) (obj)).append((int)l);
            ((StringBuilder) (obj)).append('.');
            throw new IllegalArgumentException(((StringBuilder) (obj)).toString());
        } else
        {
            return (int)l;
        }
    }

    private void compactIfNecessary()
    {
        if(!mCompacted)
        {
            if(mDepth != 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Trying to compact with ").append(mDepth).append(" missing calls to endObject").toString());
            mBuffer.startEditing();
            int i = mBuffer.getReadableSize();
            editEncodedSize(i);
            mBuffer.rewindRead();
            compactSizes(i);
            if(mCopyBegin < i)
                mBuffer.writeFromThisBuffer(mCopyBegin, i - mCopyBegin);
            mBuffer.startEditing();
            mCompacted = true;
        }
    }

    private void compactSizes(int i)
    {
        int j = mBuffer.getReadPos();
        do
        {
            int k = mBuffer.getReadPos();
            if(k >= j + i)
                break;
            int l = readRawTag();
            int i1 = l & 7;
            switch(i1)
            {
            default:
                throw new ProtoParseException((new StringBuilder()).append("compactSizes Bad tag tag=0x").append(Integer.toHexString(l)).append(" wireType=").append(i1).append(" -- ").append(mBuffer.getDebugString()).toString());

            case 0: // '\0'
                while((mBuffer.readRawByte() & 0x80) != 0) ;
                break;

            case 1: // '\001'
                mBuffer.skipRead(8);
                break;

            case 2: // '\002'
                mBuffer.writeFromThisBuffer(mCopyBegin, mBuffer.getReadPos() - mCopyBegin);
                int j1 = mBuffer.readRawFixed32();
                k = mBuffer.readRawFixed32();
                mBuffer.writeRawVarint32(k);
                mCopyBegin = mBuffer.getReadPos();
                if(j1 >= 0)
                    mBuffer.skipRead(k);
                else
                    compactSizes(-j1);
                break;

            case 3: // '\003'
            case 4: // '\004'
                throw new RuntimeException((new StringBuilder()).append("groups not supported at index ").append(k).toString());

            case 5: // '\005'
                mBuffer.skipRead(4);
                break;
            }
        } while(true);
    }

    public static int convertObjectIdToOrdinal(int i)
    {
        return 0x7ffff - i;
    }

    private int editEncodedSize(int i)
    {
        int j = mBuffer.getReadPos();
        int k = 0;
label0:
        do
        {
            int l = mBuffer.getReadPos();
            if(l >= j + i)
                break;
            int i1 = readRawTag();
            int k1 = k + EncodedBuffer.getRawVarint32Size(i1);
            k = i1 & 7;
            switch(k)
            {
            default:
                throw new ProtoParseException((new StringBuilder()).append("editEncodedSize Bad tag tag=0x").append(Integer.toHexString(i1)).append(" wireType=").append(k).append(" -- ").append(mBuffer.getDebugString()).toString());

            case 0: // '\0'
                k1++;
                do
                {
                    k = k1;
                    if((mBuffer.readRawByte() & 0x80) == 0)
                        continue label0;
                    k1++;
                } while(true);

            case 1: // '\001'
                k = k1 + 8;
                mBuffer.skipRead(8);
                break;

            case 2: // '\002'
                l = mBuffer.readRawFixed32();
                int j1 = mBuffer.getReadPos();
                k = mBuffer.readRawFixed32();
                if(l >= 0)
                {
                    if(k != l)
                        throw new RuntimeException((new StringBuilder()).append("Pre-computed size where the precomputed size and the raw size in the buffer don't match! childRawSize=").append(l).append(" childEncodedSize=").append(k).append(" childEncodedSizePos=").append(j1).toString());
                    mBuffer.skipRead(l);
                } else
                {
                    k = editEncodedSize(-l);
                    mBuffer.editRawFixed32(j1, k);
                }
                k = k1 + (EncodedBuffer.getRawVarint32Size(k) + k);
                break;

            case 3: // '\003'
            case 4: // '\004'
                throw new RuntimeException((new StringBuilder()).append("groups not supported at index ").append(l).toString());

            case 5: // '\005'
                k = k1 + 4;
                mBuffer.skipRead(4);
                break;
            }
        } while(true);
        return k;
    }

    private void endObjectImpl(long l, boolean flag)
    {
        int i = getDepthFromToken(l);
        boolean flag1 = getRepeatedFromToken(l);
        int j = getSizePosFromToken(l);
        int k = mBuffer.getWritePos() - j - 8;
        if(flag != flag1)
            if(flag)
                throw new IllegalArgumentException("endRepeatedObject called where endObject should have been");
            else
                throw new IllegalArgumentException("endObject called where endRepeatedObject should have been");
        if((mDepth & 0x1ff) != i || mExpectedObjectToken != l)
            throw new IllegalArgumentException((new StringBuilder()).append("Mismatched startObject/endObject calls. Current depth ").append(mDepth).append(" token=").append(token2String(l)).append(" expectedToken=").append(token2String(mExpectedObjectToken)).toString());
        mExpectedObjectToken = (long)mBuffer.getRawFixed32At(j) << 32 | (long)mBuffer.getRawFixed32At(j + 4) & 0xffffffffL;
        mDepth = mDepth - 1;
        if(k > 0)
        {
            mBuffer.editRawFixed32(j, -k);
            mBuffer.editRawFixed32(j + 4, -1);
        } else
        if(flag)
        {
            mBuffer.editRawFixed32(j, 0);
            mBuffer.editRawFixed32(j + 4, 0);
        } else
        {
            mBuffer.rewindWriteTo(j - getTagSizeFromToken(l));
        }
    }

    public static int getDepthFromToken(long l)
    {
        return (int)(l >> 51 & 511L);
    }

    private static String getFieldCountString(long l)
    {
        if(l == 0x10000000000L)
            return "";
        if(l == 0x20000000000L)
            return "Repeated";
        if(l == 0x50000000000L)
            return "Packed";
        else
            return null;
    }

    private String getFieldIdString(long l)
    {
        long l1 = l & 0xf0000000000L;
        if(getFieldCountString(l1) == null)
            (new StringBuilder()).append("fieldCount=").append(l1).toString();
        long l2 = l & 0xff00000000L;
        String s = getFieldTypeString(l2);
        String s1 = s;
        if(s == null)
            s1 = (new StringBuilder()).append("fieldType=").append(l2).toString();
        return (new StringBuilder()).append(l1).append(" ").append(s1).append(" tag=").append((int)l).append(" fieldId=0x").append(Long.toHexString(l)).toString();
    }

    private static String getFieldTypeString(long l)
    {
        int i = (int)((0xff00000000L & l) >>> 32) - 1;
        if(i >= 0 && i < FIELD_TYPE_NAMES.length)
            return FIELD_TYPE_NAMES[i];
        else
            return null;
    }

    public static int getObjectIdFromToken(long l)
    {
        return (int)(l >> 32 & 0x7ffffL);
    }

    public static boolean getRepeatedFromToken(long l)
    {
        boolean flag;
        if((l >> 60 & 1L) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int getSizePosFromToken(long l)
    {
        return (int)l;
    }

    private static int getTagSize(int i)
    {
        return EncodedBuffer.getRawVarint32Size(i << 3);
    }

    public static int getTagSizeFromToken(long l)
    {
        return (int)(l >> 61 & 7L);
    }

    public static long makeFieldId(int i, long l)
    {
        return (long)i & 0xffffffffL | l;
    }

    public static long makeToken(int i, boolean flag, int j, int k, int l)
    {
        long l1 = i;
        long l2;
        if(flag)
            l2 = 0x1000000000000000L;
        else
            l2 = 0L;
        return l2 | (l1 & 7L) << 61 | ((long)j & 511L) << 51 | ((long)k & 0x7ffffL) << 32 | (long)l & 0xffffffffL;
    }

    private int readRawTag()
    {
        if(mBuffer.getReadPos() == mBuffer.getReadableSize())
            return 0;
        else
            return (int)mBuffer.readRawUnsigned();
    }

    private long startObjectImpl(int i, boolean flag)
    {
        writeTag(i, 2);
        int j = mBuffer.getWritePos();
        mDepth = mDepth + 1;
        mNextObjectId = mNextObjectId - 1;
        mBuffer.writeRawFixed32((int)(mExpectedObjectToken >> 32));
        mBuffer.writeRawFixed32((int)mExpectedObjectToken);
        long l = mExpectedObjectToken;
        mExpectedObjectToken = makeToken(getTagSize(i), flag, mDepth, mNextObjectId, j);
        return mExpectedObjectToken;
    }

    public static String token2String(long l)
    {
        if(l == 0L)
            return "Token(0)";
        else
            return (new StringBuilder()).append("Token(val=0x").append(Long.toHexString(l)).append(" depth=").append(getDepthFromToken(l)).append(" object=").append(convertObjectIdToOrdinal(getObjectIdFromToken(l))).append(" tagSize=").append(getTagSizeFromToken(l)).append(" sizePos=").append(getSizePosFromToken(l)).append(')').toString();
    }

    private void writeBoolImpl(int i, boolean flag)
    {
        if(flag)
        {
            writeTag(i, 0);
            mBuffer.writeRawByte((byte)1);
        }
    }

    private void writeBytesImpl(int i, byte abyte0[])
    {
        if(abyte0 != null && abyte0.length > 0)
        {
            writeKnownLengthHeader(i, abyte0.length);
            mBuffer.writeRawBuffer(abyte0);
        }
    }

    private void writeDoubleImpl(int i, double d)
    {
        if(d != 0.0D)
        {
            writeTag(i, 1);
            mBuffer.writeRawFixed64(Double.doubleToLongBits(d));
        }
    }

    private void writeEnumImpl(int i, int j)
    {
        if(j != 0)
        {
            writeTag(i, 0);
            writeUnsignedVarintFromSignedInt(j);
        }
    }

    private void writeFixed32Impl(int i, int j)
    {
        if(j != 0)
        {
            writeTag(i, 5);
            mBuffer.writeRawFixed32(j);
        }
    }

    private void writeFixed64Impl(int i, long l)
    {
        if(l != 0L)
        {
            writeTag(i, 1);
            mBuffer.writeRawFixed64(l);
        }
    }

    private void writeFloatImpl(int i, float f)
    {
        if(f != 0.0F)
        {
            writeTag(i, 5);
            mBuffer.writeRawFixed32(Float.floatToIntBits(f));
        }
    }

    private void writeInt32Impl(int i, int j)
    {
        if(j != 0)
        {
            writeTag(i, 0);
            writeUnsignedVarintFromSignedInt(j);
        }
    }

    private void writeInt64Impl(int i, long l)
    {
        if(l != 0L)
        {
            writeTag(i, 0);
            mBuffer.writeRawVarint64(l);
        }
    }

    private void writeKnownLengthHeader(int i, int j)
    {
        writeTag(i, 2);
        mBuffer.writeRawFixed32(j);
        mBuffer.writeRawFixed32(j);
    }

    private void writeRepeatedBoolImpl(int i, boolean flag)
    {
        boolean flag1 = false;
        writeTag(i, 0);
        EncodedBuffer encodedbuffer = mBuffer;
        i = ((flag1) ? 1 : 0);
        if(flag)
            i = 1;
        encodedbuffer.writeRawByte((byte)i);
    }

    private void writeRepeatedBytesImpl(int i, byte abyte0[])
    {
        int j;
        if(abyte0 == null)
            j = 0;
        else
            j = abyte0.length;
        writeKnownLengthHeader(i, j);
        mBuffer.writeRawBuffer(abyte0);
    }

    private void writeRepeatedDoubleImpl(int i, double d)
    {
        writeTag(i, 1);
        mBuffer.writeRawFixed64(Double.doubleToLongBits(d));
    }

    private void writeRepeatedEnumImpl(int i, int j)
    {
        writeTag(i, 0);
        writeUnsignedVarintFromSignedInt(j);
    }

    private void writeRepeatedFixed32Impl(int i, int j)
    {
        writeTag(i, 5);
        mBuffer.writeRawFixed32(j);
    }

    private void writeRepeatedFixed64Impl(int i, long l)
    {
        writeTag(i, 1);
        mBuffer.writeRawFixed64(l);
    }

    private void writeRepeatedFloatImpl(int i, float f)
    {
        writeTag(i, 5);
        mBuffer.writeRawFixed32(Float.floatToIntBits(f));
    }

    private void writeRepeatedInt32Impl(int i, int j)
    {
        writeTag(i, 0);
        writeUnsignedVarintFromSignedInt(j);
    }

    private void writeRepeatedInt64Impl(int i, long l)
    {
        writeTag(i, 0);
        mBuffer.writeRawVarint64(l);
    }

    private void writeRepeatedSFixed32Impl(int i, int j)
    {
        writeTag(i, 5);
        mBuffer.writeRawFixed32(j);
    }

    private void writeRepeatedSFixed64Impl(int i, long l)
    {
        writeTag(i, 1);
        mBuffer.writeRawFixed64(l);
    }

    private void writeRepeatedSInt32Impl(int i, int j)
    {
        writeTag(i, 0);
        mBuffer.writeRawZigZag32(j);
    }

    private void writeRepeatedSInt64Impl(int i, long l)
    {
        writeTag(i, 0);
        mBuffer.writeRawZigZag64(l);
    }

    private void writeRepeatedStringImpl(int i, String s)
    {
        if(s == null || s.length() == 0)
            writeKnownLengthHeader(i, 0);
        else
            writeUtf8String(i, s);
    }

    private void writeRepeatedUInt32Impl(int i, int j)
    {
        writeTag(i, 0);
        mBuffer.writeRawVarint32(j);
    }

    private void writeRepeatedUInt64Impl(int i, long l)
    {
        writeTag(i, 0);
        mBuffer.writeRawVarint64(l);
    }

    private void writeSFixed32Impl(int i, int j)
    {
        if(j != 0)
        {
            writeTag(i, 5);
            mBuffer.writeRawFixed32(j);
        }
    }

    private void writeSFixed64Impl(int i, long l)
    {
        if(l != 0L)
        {
            writeTag(i, 1);
            mBuffer.writeRawFixed64(l);
        }
    }

    private void writeSInt32Impl(int i, int j)
    {
        if(j != 0)
        {
            writeTag(i, 0);
            mBuffer.writeRawZigZag32(j);
        }
    }

    private void writeSInt64Impl(int i, long l)
    {
        if(l != 0L)
        {
            writeTag(i, 0);
            mBuffer.writeRawZigZag64(l);
        }
    }

    private void writeStringImpl(int i, String s)
    {
        if(s != null && s.length() > 0)
            writeUtf8String(i, s);
    }

    private void writeUInt32Impl(int i, int j)
    {
        if(j != 0)
        {
            writeTag(i, 0);
            mBuffer.writeRawVarint32(j);
        }
    }

    private void writeUInt64Impl(int i, long l)
    {
        if(l != 0L)
        {
            writeTag(i, 0);
            mBuffer.writeRawVarint64(l);
        }
    }

    private void writeUnsignedVarintFromSignedInt(int i)
    {
        if(i >= 0)
            mBuffer.writeRawVarint32(i);
        else
            mBuffer.writeRawVarint64(i);
    }

    private void writeUtf8String(int i, String s)
    {
        try
        {
            s = s.getBytes("UTF-8");
            writeKnownLengthHeader(i, s.length);
            mBuffer.writeRawBuffer(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("not possible");
        }
    }

    public void dump(String s)
    {
        Log.d(s, mBuffer.getDebugString());
        mBuffer.dumpBuffers(s);
    }

    public void end(long l)
    {
        endObjectImpl(l, getRepeatedFromToken(l));
    }

    public void endObject(long l)
    {
        assertNotCompacted();
        endObjectImpl(l, false);
    }

    public void endRepeatedObject(long l)
    {
        assertNotCompacted();
        endObjectImpl(l, true);
    }

    public void flush()
    {
        if(mStream == null)
            return;
        if(mDepth != 0)
            return;
        if(mCompacted)
            return;
        compactIfNecessary();
        byte abyte0[] = mBuffer.getBytes(mBuffer.getReadableSize());
        try
        {
            mStream.write(abyte0);
            mStream.flush();
            return;
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException("Error flushing proto to stream", ioexception);
        }
    }

    public byte[] getBytes()
    {
        compactIfNecessary();
        return mBuffer.getBytes(mBuffer.getReadableSize());
    }

    public long start(long l)
    {
        assertNotCompacted();
        int i = (int)l;
        if((0xff00000000L & l) == 0x1100000000L)
        {
            long l1 = l & 0xf0000000000L;
            if(l1 == 0x10000000000L)
                return startObjectImpl(i, false);
            if(l1 == 0x20000000000L || l1 == 0x50000000000L)
                return startObjectImpl(i, true);
        }
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call start(long) with ").append(getFieldIdString(l)).toString());
    }

    public long startObject(long l)
    {
        assertNotCompacted();
        return startObjectImpl(checkFieldId(l, 0x11100000000L), false);
    }

    public long startRepeatedObject(long l)
    {
        assertNotCompacted();
        return startObjectImpl(checkFieldId(l, 0x21100000000L), true);
    }

    public void write(long l, double d)
    {
        boolean flag;
        boolean flag1;
        int i;
        flag = true;
        flag1 = true;
        assertNotCompacted();
        i = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 42: default 368
    //                   257: 400
    //                   258: 418
    //                   259: 440
    //                   260: 462
    //                   261: 484
    //                   262: 506
    //                   263: 528
    //                   264: 550
    //                   265: 572
    //                   266: 594
    //                   267: 616
    //                   268: 638
    //                   269: 660
    //                   272: 710
    //                   513: 408
    //                   514: 429
    //                   515: 451
    //                   516: 473
    //                   517: 495
    //                   518: 517
    //                   519: 539
    //                   520: 561
    //                   521: 583
    //                   522: 605
    //                   523: 627
    //                   524: 649
    //                   525: 683
    //                   528: 721
    //                   1281: 408
    //                   1282: 429
    //                   1283: 451
    //                   1284: 473
    //                   1285: 495
    //                   1286: 517
    //                   1287: 539
    //                   1288: 561
    //                   1289: 583
    //                   1290: 605
    //                   1291: 627
    //                   1292: 649
    //                   1293: 683
    //                   1296: 721;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, double) with ").append(getFieldIdString(l)).toString());
_L2:
        writeDoubleImpl(i, d);
_L31:
        return;
_L16:
        writeRepeatedDoubleImpl(i, d);
        continue; /* Loop/switch isn't completed */
_L3:
        writeFloatImpl(i, (float)d);
        continue; /* Loop/switch isn't completed */
_L17:
        writeRepeatedFloatImpl(i, (float)d);
        continue; /* Loop/switch isn't completed */
_L4:
        writeInt32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L18:
        writeRepeatedInt32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L5:
        writeInt64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L19:
        writeRepeatedInt64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L6:
        writeUInt32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L20:
        writeRepeatedUInt32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L7:
        writeUInt64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L21:
        writeRepeatedUInt64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L8:
        writeSInt32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L22:
        writeRepeatedSInt32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L9:
        writeSInt64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L23:
        writeRepeatedSInt64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L10:
        writeFixed32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L24:
        writeRepeatedFixed32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L11:
        writeFixed64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L25:
        writeRepeatedFixed64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L12:
        writeSFixed32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L26:
        writeRepeatedSFixed32Impl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L13:
        writeSFixed64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L27:
        writeRepeatedSFixed64Impl(i, (long)d);
        continue; /* Loop/switch isn't completed */
_L14:
        if(d == 0.0D)
            flag1 = false;
        writeBoolImpl(i, flag1);
        continue; /* Loop/switch isn't completed */
_L28:
        boolean flag2;
        if(d != 0.0D)
            flag2 = flag;
        else
            flag2 = false;
        writeRepeatedBoolImpl(i, flag2);
        continue; /* Loop/switch isn't completed */
_L15:
        writeEnumImpl(i, (int)d);
        continue; /* Loop/switch isn't completed */
_L29:
        writeRepeatedEnumImpl(i, (int)d);
        if(true) goto _L31; else goto _L30
_L30:
    }

    public void write(long l, float f)
    {
        boolean flag;
        boolean flag1;
        int i;
        flag = true;
        flag1 = true;
        assertNotCompacted();
        i = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 42: default 368
    //                   257: 400
    //                   258: 420
    //                   259: 440
    //                   260: 462
    //                   261: 484
    //                   262: 506
    //                   263: 528
    //                   264: 550
    //                   265: 572
    //                   266: 594
    //                   267: 616
    //                   268: 638
    //                   269: 660
    //                   272: 710
    //                   513: 409
    //                   514: 430
    //                   515: 451
    //                   516: 473
    //                   517: 495
    //                   518: 517
    //                   519: 539
    //                   520: 561
    //                   521: 583
    //                   522: 605
    //                   523: 627
    //                   524: 649
    //                   525: 683
    //                   528: 721
    //                   1281: 409
    //                   1282: 430
    //                   1283: 451
    //                   1284: 473
    //                   1285: 495
    //                   1286: 517
    //                   1287: 539
    //                   1288: 561
    //                   1289: 583
    //                   1290: 605
    //                   1291: 627
    //                   1292: 649
    //                   1293: 683
    //                   1296: 721;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, float) with ").append(getFieldIdString(l)).toString());
_L2:
        writeDoubleImpl(i, f);
_L31:
        return;
_L16:
        writeRepeatedDoubleImpl(i, f);
        continue; /* Loop/switch isn't completed */
_L3:
        writeFloatImpl(i, f);
        continue; /* Loop/switch isn't completed */
_L17:
        writeRepeatedFloatImpl(i, f);
        continue; /* Loop/switch isn't completed */
_L4:
        writeInt32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L18:
        writeRepeatedInt32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L5:
        writeInt64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L19:
        writeRepeatedInt64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L6:
        writeUInt32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L20:
        writeRepeatedUInt32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L7:
        writeUInt64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L21:
        writeRepeatedUInt64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L8:
        writeSInt32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L22:
        writeRepeatedSInt32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L9:
        writeSInt64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L23:
        writeRepeatedSInt64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L10:
        writeFixed32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L24:
        writeRepeatedFixed32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L11:
        writeFixed64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L25:
        writeRepeatedFixed64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L12:
        writeSFixed32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L26:
        writeRepeatedSFixed32Impl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L13:
        writeSFixed64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L27:
        writeRepeatedSFixed64Impl(i, (long)f);
        continue; /* Loop/switch isn't completed */
_L14:
        if(f == 0.0F)
            flag1 = false;
        writeBoolImpl(i, flag1);
        continue; /* Loop/switch isn't completed */
_L28:
        boolean flag2;
        if(f != 0.0F)
            flag2 = flag;
        else
            flag2 = false;
        writeRepeatedBoolImpl(i, flag2);
        continue; /* Loop/switch isn't completed */
_L15:
        writeEnumImpl(i, (int)f);
        continue; /* Loop/switch isn't completed */
_L29:
        writeRepeatedEnumImpl(i, (int)f);
        if(true) goto _L31; else goto _L30
_L30:
    }

    public void write(long l, int i)
    {
        boolean flag;
        boolean flag1;
        int j;
        flag = true;
        flag1 = true;
        assertNotCompacted();
        j = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 42: default 368
    //                   257: 400
    //                   258: 420
    //                   259: 442
    //                   260: 462
    //                   261: 484
    //                   262: 504
    //                   263: 526
    //                   264: 546
    //                   265: 568
    //                   266: 588
    //                   267: 610
    //                   268: 630
    //                   269: 652
    //                   272: 698
    //                   513: 409
    //                   514: 431
    //                   515: 452
    //                   516: 473
    //                   517: 494
    //                   518: 515
    //                   519: 536
    //                   520: 557
    //                   521: 578
    //                   522: 599
    //                   523: 620
    //                   524: 641
    //                   525: 673
    //                   528: 708
    //                   1281: 409
    //                   1282: 431
    //                   1283: 452
    //                   1284: 473
    //                   1285: 494
    //                   1286: 515
    //                   1287: 536
    //                   1288: 557
    //                   1289: 578
    //                   1290: 599
    //                   1291: 620
    //                   1292: 641
    //                   1293: 673
    //                   1296: 708;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, int) with ").append(getFieldIdString(l)).toString());
_L2:
        writeDoubleImpl(j, i);
_L31:
        return;
_L16:
        writeRepeatedDoubleImpl(j, i);
        continue; /* Loop/switch isn't completed */
_L3:
        writeFloatImpl(j, i);
        continue; /* Loop/switch isn't completed */
_L17:
        writeRepeatedFloatImpl(j, i);
        continue; /* Loop/switch isn't completed */
_L4:
        writeInt32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L18:
        writeRepeatedInt32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L5:
        writeInt64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L19:
        writeRepeatedInt64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L6:
        writeUInt32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L20:
        writeRepeatedUInt32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L7:
        writeUInt64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L21:
        writeRepeatedUInt64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L8:
        writeSInt32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L22:
        writeRepeatedSInt32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L9:
        writeSInt64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L23:
        writeRepeatedSInt64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L10:
        writeFixed32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L24:
        writeRepeatedFixed32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L11:
        writeFixed64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L25:
        writeRepeatedFixed64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L12:
        writeSFixed32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L26:
        writeRepeatedSFixed32Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L13:
        writeSFixed64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L27:
        writeRepeatedSFixed64Impl(j, i);
        continue; /* Loop/switch isn't completed */
_L14:
        if(i == 0)
            flag1 = false;
        writeBoolImpl(j, flag1);
        continue; /* Loop/switch isn't completed */
_L28:
        boolean flag2;
        if(i != 0)
            flag2 = flag;
        else
            flag2 = false;
        writeRepeatedBoolImpl(j, flag2);
        continue; /* Loop/switch isn't completed */
_L15:
        writeEnumImpl(j, i);
        continue; /* Loop/switch isn't completed */
_L29:
        writeRepeatedEnumImpl(j, i);
        if(true) goto _L31; else goto _L30
_L30:
    }

    public void write(long l, long l1)
    {
        boolean flag;
        boolean flag1;
        int i;
        flag = true;
        flag1 = true;
        assertNotCompacted();
        i = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 42: default 368
    //                   257: 400
    //                   258: 420
    //                   259: 442
    //                   260: 464
    //                   261: 484
    //                   262: 506
    //                   263: 526
    //                   264: 548
    //                   265: 568
    //                   266: 590
    //                   267: 610
    //                   268: 632
    //                   269: 652
    //                   272: 702
    //                   513: 409
    //                   514: 431
    //                   515: 453
    //                   516: 474
    //                   517: 495
    //                   518: 516
    //                   519: 537
    //                   520: 558
    //                   521: 579
    //                   522: 600
    //                   523: 621
    //                   524: 642
    //                   525: 675
    //                   528: 713
    //                   1281: 409
    //                   1282: 431
    //                   1283: 453
    //                   1284: 474
    //                   1285: 495
    //                   1286: 516
    //                   1287: 537
    //                   1288: 558
    //                   1289: 579
    //                   1290: 600
    //                   1291: 621
    //                   1292: 642
    //                   1293: 675
    //                   1296: 713;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, long) with ").append(getFieldIdString(l)).toString());
_L2:
        writeDoubleImpl(i, l1);
_L31:
        return;
_L16:
        writeRepeatedDoubleImpl(i, l1);
        continue; /* Loop/switch isn't completed */
_L3:
        writeFloatImpl(i, l1);
        continue; /* Loop/switch isn't completed */
_L17:
        writeRepeatedFloatImpl(i, l1);
        continue; /* Loop/switch isn't completed */
_L4:
        writeInt32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L18:
        writeRepeatedInt32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L5:
        writeInt64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L19:
        writeRepeatedInt64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L6:
        writeUInt32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L20:
        writeRepeatedUInt32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L7:
        writeUInt64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L21:
        writeRepeatedUInt64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L8:
        writeSInt32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L22:
        writeRepeatedSInt32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L9:
        writeSInt64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L23:
        writeRepeatedSInt64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L10:
        writeFixed32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L24:
        writeRepeatedFixed32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L11:
        writeFixed64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L25:
        writeRepeatedFixed64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L12:
        writeSFixed32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L26:
        writeRepeatedSFixed32Impl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L13:
        writeSFixed64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L27:
        writeRepeatedSFixed64Impl(i, l1);
        continue; /* Loop/switch isn't completed */
_L14:
        if(l1 == 0L)
            flag1 = false;
        writeBoolImpl(i, flag1);
        continue; /* Loop/switch isn't completed */
_L28:
        boolean flag2;
        if(l1 != 0L)
            flag2 = flag;
        else
            flag2 = false;
        writeRepeatedBoolImpl(i, flag2);
        continue; /* Loop/switch isn't completed */
_L15:
        writeEnumImpl(i, (int)l1);
        continue; /* Loop/switch isn't completed */
_L29:
        writeRepeatedEnumImpl(i, (int)l1);
        if(true) goto _L31; else goto _L30
_L30:
    }

    public void write(long l, String s)
    {
        int i;
        assertNotCompacted();
        i = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 3: default 52
    //                   270: 84
    //                   526: 92
    //                   1294: 92;
           goto _L1 _L2 _L3 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, String) with ").append(getFieldIdString(l)).toString());
_L2:
        writeStringImpl(i, s);
_L5:
        return;
_L3:
        writeRepeatedStringImpl(i, s);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void write(long l, boolean flag)
    {
        int i;
        assertNotCompacted();
        i = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 3: default 52
    //                   269: 84
    //                   525: 92
    //                   1293: 92;
           goto _L1 _L2 _L3 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, boolean) with ").append(getFieldIdString(l)).toString());
_L2:
        writeBoolImpl(i, flag);
_L5:
        return;
_L3:
        writeRepeatedBoolImpl(i, flag);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void write(long l, byte abyte0[])
    {
        int i;
        assertNotCompacted();
        i = (int)l;
        (int)((0xfff00000000L & l) >> 32);
        JVM INSTR lookupswitch 6: default 76
    //                   271: 108
    //                   273: 126
    //                   527: 116
    //                   529: 136
    //                   1295: 116
    //                   1297: 136;
           goto _L1 _L2 _L3 _L4 _L5 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Attempt to call write(long, byte[]) with ").append(getFieldIdString(l)).toString());
_L2:
        writeBytesImpl(i, abyte0);
_L7:
        return;
_L4:
        writeRepeatedBytesImpl(i, abyte0);
        continue; /* Loop/switch isn't completed */
_L3:
        writeObjectImpl(i, abyte0);
        continue; /* Loop/switch isn't completed */
_L5:
        writeRepeatedObjectImpl(i, abyte0);
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void writeBool(long l, boolean flag)
    {
        assertNotCompacted();
        writeBoolImpl(checkFieldId(l, 0x10d00000000L), flag);
    }

    public void writeBytes(long l, byte abyte0[])
    {
        assertNotCompacted();
        writeBytesImpl(checkFieldId(l, 0x10f00000000L), abyte0);
    }

    public void writeDouble(long l, double d)
    {
        assertNotCompacted();
        writeDoubleImpl(checkFieldId(l, 0x10100000000L), d);
    }

    public void writeEnum(long l, int i)
    {
        assertNotCompacted();
        writeEnumImpl(checkFieldId(l, 0x11000000000L), i);
    }

    public void writeFixed32(long l, int i)
    {
        assertNotCompacted();
        writeFixed32Impl(checkFieldId(l, 0x10900000000L), i);
    }

    public void writeFixed64(long l, long l1)
    {
        assertNotCompacted();
        writeFixed64Impl(checkFieldId(l, 0x10a00000000L), l1);
    }

    public void writeFloat(long l, float f)
    {
        assertNotCompacted();
        writeFloatImpl(checkFieldId(l, 0x10200000000L), f);
    }

    public void writeInt32(long l, int i)
    {
        assertNotCompacted();
        writeInt32Impl(checkFieldId(l, 0x10300000000L), i);
    }

    public void writeInt64(long l, long l1)
    {
        assertNotCompacted();
        writeInt64Impl(checkFieldId(l, 0x10400000000L), l1);
    }

    public void writeObject(long l, byte abyte0[])
    {
        assertNotCompacted();
        writeObjectImpl(checkFieldId(l, 0x11100000000L), abyte0);
    }

    void writeObjectImpl(int i, byte abyte0[])
    {
        if(abyte0 != null && abyte0.length != 0)
        {
            writeKnownLengthHeader(i, abyte0.length);
            mBuffer.writeRawBuffer(abyte0);
        }
    }

    public void writePackedBool(long l, boolean aflag[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50d00000000L);
        int j;
        if(aflag != null)
            j = aflag.length;
        else
            j = 0;
        if(j > 0)
        {
            writeKnownLengthHeader(i, j);
            i = 0;
            while(i < j) 
            {
                EncodedBuffer encodedbuffer = mBuffer;
                int k;
                if(aflag[i])
                    k = 1;
                else
                    k = 0;
                encodedbuffer.writeRawByte((byte)k);
                i++;
            }
        }
    }

    public void writePackedDouble(long l, double ad[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50100000000L);
        int k;
        if(ad != null)
            k = ad.length;
        else
            k = 0;
        if(k > 0)
        {
            writeKnownLengthHeader(i, k * 8);
            for(int j = 0; j < k; j++)
                mBuffer.writeRawFixed64(Double.doubleToLongBits(ad[j]));

        }
    }

    public void writePackedEnum(long l, int ai[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x51000000000L);
        int j;
        if(ai != null)
            j = ai.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            int i1 = 0;
            while(i1 < j) 
            {
                int k1 = ai[i1];
                if(k1 >= 0)
                    k1 = EncodedBuffer.getRawVarint32Size(k1);
                else
                    k1 = 10;
                k += k1;
                i1++;
            }
            writeKnownLengthHeader(i, k);
            for(int j1 = 0; j1 < j; j1++)
                writeUnsignedVarintFromSignedInt(ai[j1]);

        }
    }

    public void writePackedFixed32(long l, int ai[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50900000000L);
        int k;
        if(ai != null)
            k = ai.length;
        else
            k = 0;
        if(k > 0)
        {
            writeKnownLengthHeader(i, k * 4);
            for(int j = 0; j < k; j++)
                mBuffer.writeRawFixed32(ai[j]);

        }
    }

    public void writePackedFixed64(long l, long al[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50a00000000L);
        int k;
        if(al != null)
            k = al.length;
        else
            k = 0;
        if(k > 0)
        {
            writeKnownLengthHeader(i, k * 8);
            for(int j = 0; j < k; j++)
                mBuffer.writeRawFixed64(al[j]);

        }
    }

    public void writePackedFloat(long l, float af[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50200000000L);
        int k;
        if(af != null)
            k = af.length;
        else
            k = 0;
        if(k > 0)
        {
            writeKnownLengthHeader(i, k * 4);
            for(int j = 0; j < k; j++)
                mBuffer.writeRawFixed32(Float.floatToIntBits(af[j]));

        }
    }

    public void writePackedInt32(long l, int ai[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50300000000L);
        int j;
        if(ai != null)
            j = ai.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            int j1 = 0;
            while(j1 < j) 
            {
                int k1 = ai[j1];
                if(k1 >= 0)
                    k1 = EncodedBuffer.getRawVarint32Size(k1);
                else
                    k1 = 10;
                k += k1;
                j1++;
            }
            writeKnownLengthHeader(i, k);
            for(int i1 = 0; i1 < j; i1++)
                writeUnsignedVarintFromSignedInt(ai[i1]);

        }
    }

    public void writePackedInt64(long l, long al[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50400000000L);
        int j;
        if(al != null)
            j = al.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            for(int j1 = 0; j1 < j; j1++)
                k += EncodedBuffer.getRawVarint64Size(al[j1]);

            writeKnownLengthHeader(i, k);
            for(int i1 = 0; i1 < j; i1++)
                mBuffer.writeRawVarint64(al[i1]);

        }
    }

    public void writePackedSFixed32(long l, int ai[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50b00000000L);
        int k;
        if(ai != null)
            k = ai.length;
        else
            k = 0;
        if(k > 0)
        {
            writeKnownLengthHeader(i, k * 4);
            for(int j = 0; j < k; j++)
                mBuffer.writeRawFixed32(ai[j]);

        }
    }

    public void writePackedSFixed64(long l, long al[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50c00000000L);
        int k;
        if(al != null)
            k = al.length;
        else
            k = 0;
        if(k > 0)
        {
            writeKnownLengthHeader(i, k * 8);
            for(int j = 0; j < k; j++)
                mBuffer.writeRawFixed64(al[j]);

        }
    }

    public void writePackedSInt32(long l, int ai[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50700000000L);
        int j;
        if(ai != null)
            j = ai.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            for(int i1 = 0; i1 < j; i1++)
                k += EncodedBuffer.getRawZigZag32Size(ai[i1]);

            writeKnownLengthHeader(i, k);
            for(int j1 = 0; j1 < j; j1++)
                mBuffer.writeRawZigZag32(ai[j1]);

        }
    }

    public void writePackedSInt64(long l, long al[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50800000000L);
        int j;
        if(al != null)
            j = al.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            for(int i1 = 0; i1 < j; i1++)
                k += EncodedBuffer.getRawZigZag64Size(al[i1]);

            writeKnownLengthHeader(i, k);
            for(int j1 = 0; j1 < j; j1++)
                mBuffer.writeRawZigZag64(al[j1]);

        }
    }

    public void writePackedUInt32(long l, int ai[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50500000000L);
        int j;
        if(ai != null)
            j = ai.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            for(int i1 = 0; i1 < j; i1++)
                k += EncodedBuffer.getRawVarint32Size(ai[i1]);

            writeKnownLengthHeader(i, k);
            for(int j1 = 0; j1 < j; j1++)
                mBuffer.writeRawVarint32(ai[j1]);

        }
    }

    public void writePackedUInt64(long l, long al[])
    {
        assertNotCompacted();
        int i = checkFieldId(l, 0x50600000000L);
        int j;
        if(al != null)
            j = al.length;
        else
            j = 0;
        if(j > 0)
        {
            int k = 0;
            for(int i1 = 0; i1 < j; i1++)
                k += EncodedBuffer.getRawVarint64Size(al[i1]);

            writeKnownLengthHeader(i, k);
            for(int j1 = 0; j1 < j; j1++)
                mBuffer.writeRawVarint64(al[j1]);

        }
    }

    public void writeRepeatedBool(long l, boolean flag)
    {
        assertNotCompacted();
        writeRepeatedBoolImpl(checkFieldId(l, 0x20d00000000L), flag);
    }

    public void writeRepeatedBytes(long l, byte abyte0[])
    {
        assertNotCompacted();
        writeRepeatedBytesImpl(checkFieldId(l, 0x20f00000000L), abyte0);
    }

    public void writeRepeatedDouble(long l, double d)
    {
        assertNotCompacted();
        writeRepeatedDoubleImpl(checkFieldId(l, 0x20100000000L), d);
    }

    public void writeRepeatedEnum(long l, int i)
    {
        assertNotCompacted();
        writeRepeatedEnumImpl(checkFieldId(l, 0x21000000000L), i);
    }

    public void writeRepeatedFixed32(long l, int i)
    {
        assertNotCompacted();
        writeRepeatedFixed32Impl(checkFieldId(l, 0x20900000000L), i);
    }

    public void writeRepeatedFixed64(long l, long l1)
    {
        assertNotCompacted();
        writeRepeatedFixed64Impl(checkFieldId(l, 0x20a00000000L), l1);
    }

    public void writeRepeatedFloat(long l, float f)
    {
        assertNotCompacted();
        writeRepeatedFloatImpl(checkFieldId(l, 0x20200000000L), f);
    }

    public void writeRepeatedInt32(long l, int i)
    {
        assertNotCompacted();
        writeRepeatedInt32Impl(checkFieldId(l, 0x20300000000L), i);
    }

    public void writeRepeatedInt64(long l, long l1)
    {
        assertNotCompacted();
        writeRepeatedInt64Impl(checkFieldId(l, 0x20400000000L), l1);
    }

    public void writeRepeatedObject(long l, byte abyte0[])
    {
        assertNotCompacted();
        writeRepeatedObjectImpl(checkFieldId(l, 0x21100000000L), abyte0);
    }

    void writeRepeatedObjectImpl(int i, byte abyte0[])
    {
        int j;
        if(abyte0 == null)
            j = 0;
        else
            j = abyte0.length;
        writeKnownLengthHeader(i, j);
        mBuffer.writeRawBuffer(abyte0);
    }

    public void writeRepeatedSFixed32(long l, int i)
    {
        assertNotCompacted();
        writeRepeatedSFixed32Impl(checkFieldId(l, 0x20b00000000L), i);
    }

    public void writeRepeatedSFixed64(long l, long l1)
    {
        assertNotCompacted();
        writeRepeatedSFixed64Impl(checkFieldId(l, 0x20c00000000L), l1);
    }

    public void writeRepeatedSInt32(long l, int i)
    {
        assertNotCompacted();
        writeRepeatedSInt32Impl(checkFieldId(l, 0x20700000000L), i);
    }

    public void writeRepeatedSInt64(long l, long l1)
    {
        assertNotCompacted();
        writeRepeatedSInt64Impl(checkFieldId(l, 0x20800000000L), l1);
    }

    public void writeRepeatedString(long l, String s)
    {
        assertNotCompacted();
        writeRepeatedStringImpl(checkFieldId(l, 0x20e00000000L), s);
    }

    public void writeRepeatedUInt32(long l, int i)
    {
        assertNotCompacted();
        writeRepeatedUInt32Impl(checkFieldId(l, 0x20500000000L), i);
    }

    public void writeRepeatedUInt64(long l, long l1)
    {
        assertNotCompacted();
        writeRepeatedUInt64Impl(checkFieldId(l, 0x20600000000L), l1);
    }

    public void writeSFixed32(long l, int i)
    {
        assertNotCompacted();
        writeSFixed32Impl(checkFieldId(l, 0x10b00000000L), i);
    }

    public void writeSFixed64(long l, long l1)
    {
        assertNotCompacted();
        writeSFixed64Impl(checkFieldId(l, 0x10c00000000L), l1);
    }

    public void writeSInt32(long l, int i)
    {
        assertNotCompacted();
        writeSInt32Impl(checkFieldId(l, 0x10700000000L), i);
    }

    public void writeSInt64(long l, long l1)
    {
        assertNotCompacted();
        writeSInt64Impl(checkFieldId(l, 0x10800000000L), l1);
    }

    public void writeString(long l, String s)
    {
        assertNotCompacted();
        writeStringImpl(checkFieldId(l, 0x10e00000000L), s);
    }

    public void writeTag(int i, int j)
    {
        mBuffer.writeRawVarint32(i << 3 | j);
    }

    public void writeUInt32(long l, int i)
    {
        assertNotCompacted();
        writeUInt32Impl(checkFieldId(l, 0x10500000000L), i);
    }

    public void writeUInt64(long l, long l1)
    {
        assertNotCompacted();
        writeUInt64Impl(checkFieldId(l, 0x10600000000L), l1);
    }

    public static final long FIELD_COUNT_MASK = 0xf0000000000L;
    public static final long FIELD_COUNT_PACKED = 0x50000000000L;
    public static final long FIELD_COUNT_REPEATED = 0x20000000000L;
    public static final int FIELD_COUNT_SHIFT = 40;
    public static final long FIELD_COUNT_SINGLE = 0x10000000000L;
    public static final long FIELD_COUNT_UNKNOWN = 0L;
    public static final int FIELD_ID_MASK = -8;
    public static final int FIELD_ID_SHIFT = 3;
    public static final long FIELD_TYPE_BOOL = 0xd00000000L;
    public static final long FIELD_TYPE_BYTES = 0xf00000000L;
    public static final long FIELD_TYPE_DOUBLE = 0x100000000L;
    public static final long FIELD_TYPE_ENUM = 0x1000000000L;
    public static final long FIELD_TYPE_FIXED32 = 0x900000000L;
    public static final long FIELD_TYPE_FIXED64 = 0xa00000000L;
    public static final long FIELD_TYPE_FLOAT = 0x200000000L;
    public static final long FIELD_TYPE_INT32 = 0x300000000L;
    public static final long FIELD_TYPE_INT64 = 0x400000000L;
    public static final long FIELD_TYPE_MASK = 0xff00000000L;
    private static final String FIELD_TYPE_NAMES[] = {
        "Double", "Float", "Int32", "Int64", "UInt32", "UInt64", "SInt32", "SInt64", "Fixed32", "Fixed64", 
        "SFixed32", "SFixed64", "Bool", "String", "Bytes", "Enum", "Object"
    };
    public static final long FIELD_TYPE_OBJECT = 0x1100000000L;
    public static final long FIELD_TYPE_SFIXED32 = 0xb00000000L;
    public static final long FIELD_TYPE_SFIXED64 = 0xc00000000L;
    public static final int FIELD_TYPE_SHIFT = 32;
    public static final long FIELD_TYPE_SINT32 = 0x700000000L;
    public static final long FIELD_TYPE_SINT64 = 0x800000000L;
    public static final long FIELD_TYPE_STRING = 0xe00000000L;
    public static final long FIELD_TYPE_UINT32 = 0x500000000L;
    public static final long FIELD_TYPE_UINT64 = 0x600000000L;
    public static final long FIELD_TYPE_UNKNOWN = 0L;
    public static final String TAG = "ProtoOutputStream";
    public static final int WIRE_TYPE_END_GROUP = 4;
    public static final int WIRE_TYPE_FIXED32 = 5;
    public static final int WIRE_TYPE_FIXED64 = 1;
    public static final int WIRE_TYPE_LENGTH_DELIMITED = 2;
    public static final int WIRE_TYPE_MASK = 7;
    public static final int WIRE_TYPE_START_GROUP = 3;
    public static final int WIRE_TYPE_VARINT = 0;
    private EncodedBuffer mBuffer;
    private boolean mCompacted;
    private int mCopyBegin;
    private int mDepth;
    private long mExpectedObjectToken;
    private int mNextObjectId;
    private OutputStream mStream;

}
