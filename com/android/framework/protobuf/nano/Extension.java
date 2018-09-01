// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.android.framework.protobuf.nano:
//            UnknownFieldData, CodedInputByteBufferNano, WireFormatNano, MessageNano, 
//            CodedOutputByteBufferNano

public class Extension
{
    private static class PrimitiveExtension extends Extension
    {

        private int computePackedDataSize(Object obj)
        {
            int i;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            int j;
            int i2;
            i = 0;
            flag = false;
            flag1 = false;
            flag2 = false;
            flag3 = false;
            flag4 = false;
            j = 0;
            i2 = Array.getLength(obj);
            type;
            JVM INSTR tableswitch 1 18: default 116
        //                       1 159
        //                       2 151
        //                       3 273
        //                       4 347
        //                       5 168
        //                       6 159
        //                       7 151
        //                       8 146
        //                       9 116
        //                       10 116
        //                       11 116
        //                       12 116
        //                       13 237
        //                       14 384
        //                       15 151
        //                       16 159
        //                       17 201
        //                       18 310;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L2 _L3 _L7 _L1 _L1 _L1 _L1 _L8 _L9 _L3 _L2 _L10 _L11
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected non-packable type ").append(type).toString());
_L7:
            i = i2;
_L12:
            return i;
_L3:
            i = i2 * 4;
            continue; /* Loop/switch isn't completed */
_L2:
            i = i2 * 8;
            if(true) goto _L12; else goto _L6
_L6:
            int j2 = 0;
            do
            {
                i = j;
                if(j2 >= i2)
                    continue;
                j += CodedOutputByteBufferNano.computeInt32SizeNoTag(Array.getInt(obj, j2));
                j2++;
            } while(true);
_L10:
            int k2 = 0;
            int k = i;
            do
            {
                i = k;
                if(k2 >= i2)
                    continue;
                k += CodedOutputByteBufferNano.computeSInt32SizeNoTag(Array.getInt(obj, k2));
                k2++;
            } while(true);
_L8:
            int l2 = 0;
            int l = ((flag) ? 1 : 0);
            do
            {
                i = l;
                if(l2 >= i2)
                    continue;
                l += CodedOutputByteBufferNano.computeUInt32SizeNoTag(Array.getInt(obj, l2));
                l2++;
            } while(true);
_L4:
            int i3 = 0;
            int i1 = ((flag1) ? 1 : 0);
            do
            {
                i = i1;
                if(i3 >= i2)
                    continue;
                i1 += CodedOutputByteBufferNano.computeInt64SizeNoTag(Array.getLong(obj, i3));
                i3++;
            } while(true);
_L11:
            int j3 = 0;
            int j1 = ((flag2) ? 1 : 0);
            do
            {
                i = j1;
                if(j3 >= i2)
                    continue;
                j1 += CodedOutputByteBufferNano.computeSInt64SizeNoTag(Array.getLong(obj, j3));
                j3++;
            } while(true);
_L5:
            int k3 = 0;
            int k1 = ((flag3) ? 1 : 0);
            do
            {
                i = k1;
                if(k3 >= i2)
                    continue;
                k1 += CodedOutputByteBufferNano.computeUInt64SizeNoTag(Array.getLong(obj, k3));
                k3++;
            } while(true);
_L9:
            int l3 = 0;
            int l1 = ((flag4) ? 1 : 0);
            do
            {
                i = l1;
                if(l3 >= i2)
                    continue;
                l1 += CodedOutputByteBufferNano.computeEnumSizeNoTag(Array.getInt(obj, l3));
                l3++;
            } while(true);
            if(true) goto _L12; else goto _L13
_L13:
        }

        protected int computeRepeatedSerializedSize(Object obj)
        {
            if(tag == nonPackedTag)
                return computeRepeatedSerializedSize(obj);
            if(tag == packedTag)
            {
                int i = computePackedDataSize(obj);
                int j = CodedOutputByteBufferNano.computeRawVarint32Size(i);
                return CodedOutputByteBufferNano.computeRawVarint32Size(tag) + (i + j);
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Unexpected repeated extension tag ").append(tag).append(", unequal to both non-packed variant ").append(nonPackedTag).append(" and packed variant ").append(packedTag).toString());
            }
        }

        protected final int computeSingularSerializedSize(Object obj)
        {
            int i = WireFormatNano.getTagFieldNumber(tag);
            switch(type)
            {
            case 10: // '\n'
            case 11: // '\013'
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown type ").append(type).toString());

            case 1: // '\001'
                return CodedOutputByteBufferNano.computeDoubleSize(i, ((Double)obj).doubleValue());

            case 2: // '\002'
                return CodedOutputByteBufferNano.computeFloatSize(i, ((Float)obj).floatValue());

            case 3: // '\003'
                return CodedOutputByteBufferNano.computeInt64Size(i, ((Long)obj).longValue());

            case 4: // '\004'
                return CodedOutputByteBufferNano.computeUInt64Size(i, ((Long)obj).longValue());

            case 5: // '\005'
                return CodedOutputByteBufferNano.computeInt32Size(i, ((Integer)obj).intValue());

            case 6: // '\006'
                return CodedOutputByteBufferNano.computeFixed64Size(i, ((Long)obj).longValue());

            case 7: // '\007'
                return CodedOutputByteBufferNano.computeFixed32Size(i, ((Integer)obj).intValue());

            case 8: // '\b'
                return CodedOutputByteBufferNano.computeBoolSize(i, ((Boolean)obj).booleanValue());

            case 9: // '\t'
                return CodedOutputByteBufferNano.computeStringSize(i, (String)obj);

            case 12: // '\f'
                return CodedOutputByteBufferNano.computeBytesSize(i, (byte[])obj);

            case 13: // '\r'
                return CodedOutputByteBufferNano.computeUInt32Size(i, ((Integer)obj).intValue());

            case 14: // '\016'
                return CodedOutputByteBufferNano.computeEnumSize(i, ((Integer)obj).intValue());

            case 15: // '\017'
                return CodedOutputByteBufferNano.computeSFixed32Size(i, ((Integer)obj).intValue());

            case 16: // '\020'
                return CodedOutputByteBufferNano.computeSFixed64Size(i, ((Long)obj).longValue());

            case 17: // '\021'
                return CodedOutputByteBufferNano.computeSInt32Size(i, ((Integer)obj).intValue());

            case 18: // '\022'
                return CodedOutputByteBufferNano.computeSInt64Size(i, ((Long)obj).longValue());
            }
        }

        protected Object readData(CodedInputByteBufferNano codedinputbytebuffernano)
        {
            try
            {
                codedinputbytebuffernano = ((CodedInputByteBufferNano) (codedinputbytebuffernano.readPrimitiveField(type)));
            }
            // Misplaced declaration of an exception variable
            catch(CodedInputByteBufferNano codedinputbytebuffernano)
            {
                throw new IllegalArgumentException("Error reading extension field", codedinputbytebuffernano);
            }
            return codedinputbytebuffernano;
        }

        protected void readDataInto(UnknownFieldData unknownfielddata, List list)
        {
            if(unknownfielddata.tag == nonPackedTag)
            {
                list.add(readData(CodedInputByteBufferNano.newInstance(unknownfielddata.bytes)));
            } else
            {
                unknownfielddata = CodedInputByteBufferNano.newInstance(unknownfielddata.bytes);
                try
                {
                    unknownfielddata.pushLimit(unknownfielddata.readRawVarint32());
                }
                // Misplaced declaration of an exception variable
                catch(UnknownFieldData unknownfielddata)
                {
                    throw new IllegalArgumentException("Error reading extension field", unknownfielddata);
                }
                while(!unknownfielddata.isAtEnd()) 
                    list.add(readData(unknownfielddata));
            }
        }

        protected void writeRepeatedData(Object obj, CodedOutputByteBufferNano codedoutputbytebuffernano)
        {
            if(tag != nonPackedTag) goto _L2; else goto _L1
_L1:
            writeRepeatedData(obj, codedoutputbytebuffernano);
_L33:
            return;
_L2:
            int i;
            int j;
            if(tag != packedTag)
                break; /* Loop/switch isn't completed */
            i = Array.getLength(obj);
            j = computePackedDataSize(obj);
            codedoutputbytebuffernano.writeRawVarint32(tag);
            codedoutputbytebuffernano.writeRawVarint32(j);
            type;
            JVM INSTR tableswitch 1 18: default 144
        //                       1 339
        //                       2 264
        //                       3 439
        //                       4 489
        //                       5 364
        //                       6 289
        //                       7 214
        //                       8 189
        //                       9 144
        //                       10 144
        //                       11 144
        //                       12 144
        //                       13 414
        //                       14 514
        //                       15 239
        //                       16 314
        //                       17 389
        //                       18 464;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L3 _L3 _L3 _L3 _L12 _L13 _L14 _L15 _L16 _L17
_L3:
            codedoutputbytebuffernano = JVM INSTR new #33  <Class IllegalArgumentException>;
            obj = JVM INSTR new #35  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            codedoutputbytebuffernano.IllegalArgumentException(((StringBuilder) (obj)).append("Unpackable type ").append(type).toString());
            throw codedoutputbytebuffernano;
_L11:
            j = 0;
            while(j < i) 
            {
                try
                {
                    codedoutputbytebuffernano.writeBoolNoTag(Array.getBoolean(obj, j));
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    throw new IllegalStateException(((Throwable) (obj)));
                }
                j++;
            }
            continue; /* Loop/switch isn't completed */
_L10:
            j = 0;
_L18:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeFixed32NoTag(Array.getInt(obj, j));
            j++;
              goto _L18
_L14:
            j = 0;
_L19:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeSFixed32NoTag(Array.getInt(obj, j));
            j++;
              goto _L19
_L5:
            j = 0;
_L20:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeFloatNoTag(Array.getFloat(obj, j));
            j++;
              goto _L20
_L9:
            j = 0;
_L21:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeFixed64NoTag(Array.getLong(obj, j));
            j++;
              goto _L21
_L15:
            j = 0;
_L22:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeSFixed64NoTag(Array.getLong(obj, j));
            j++;
              goto _L22
_L4:
            j = 0;
_L23:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeDoubleNoTag(Array.getDouble(obj, j));
            j++;
              goto _L23
_L8:
            j = 0;
_L24:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeInt32NoTag(Array.getInt(obj, j));
            j++;
              goto _L24
_L16:
            j = 0;
_L25:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeSInt32NoTag(Array.getInt(obj, j));
            j++;
              goto _L25
_L12:
            j = 0;
_L26:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeUInt32NoTag(Array.getInt(obj, j));
            j++;
              goto _L26
_L6:
            j = 0;
_L27:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeInt64NoTag(Array.getLong(obj, j));
            j++;
              goto _L27
_L17:
            j = 0;
_L28:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeSInt64NoTag(Array.getLong(obj, j));
            j++;
              goto _L28
_L7:
            j = 0;
_L29:
            if(j >= i)
                continue; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeUInt64NoTag(Array.getLong(obj, j));
            j++;
              goto _L29
_L13:
            j = 0;
_L31:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            codedoutputbytebuffernano.writeEnumNoTag(Array.getInt(obj, j));
            j++;
            if(true) goto _L31; else goto _L30
_L30:
            if(true) goto _L33; else goto _L32
_L32:
            throw new IllegalArgumentException((new StringBuilder()).append("Unexpected repeated extension tag ").append(tag).append(", unequal to both non-packed variant ").append(nonPackedTag).append(" and packed variant ").append(packedTag).toString());
        }

        protected final void writeSingularData(Object obj, CodedOutputByteBufferNano codedoutputbytebuffernano)
        {
            codedoutputbytebuffernano.writeRawVarint32(tag);
            type;
            JVM INSTR tableswitch 1 18: default 100
        //                       1 144
        //                       2 156
        //                       3 170
        //                       4 184
        //                       5 198
        //                       6 212
        //                       7 226
        //                       8 240
        //                       9 254
        //                       10 100
        //                       11 100
        //                       12 265
        //                       13 276
        //                       14 290
        //                       15 304
        //                       16 318
        //                       17 332
        //                       18 346;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L1 _L1 _L11 _L12 _L13 _L14 _L15 _L16 _L17
_L17:
            break MISSING_BLOCK_LABEL_346;
_L1:
            obj = JVM INSTR new #33  <Class IllegalArgumentException>;
            codedoutputbytebuffernano = JVM INSTR new #35  <Class StringBuilder>;
            codedoutputbytebuffernano.StringBuilder();
            ((IllegalArgumentException) (obj)).IllegalArgumentException(codedoutputbytebuffernano.append("Unknown type ").append(type).toString());
            throw obj;
_L2:
            codedoutputbytebuffernano.writeDoubleNoTag(((Double)obj).doubleValue());
_L18:
            return;
_L3:
            try
            {
                codedoutputbytebuffernano.writeFloatNoTag(((Float)obj).floatValue());
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new IllegalStateException(((Throwable) (obj)));
            }
              goto _L18
_L4:
            codedoutputbytebuffernano.writeInt64NoTag(((Long)obj).longValue());
              goto _L18
_L5:
            codedoutputbytebuffernano.writeUInt64NoTag(((Long)obj).longValue());
              goto _L18
_L6:
            codedoutputbytebuffernano.writeInt32NoTag(((Integer)obj).intValue());
              goto _L18
_L7:
            codedoutputbytebuffernano.writeFixed64NoTag(((Long)obj).longValue());
              goto _L18
_L8:
            codedoutputbytebuffernano.writeFixed32NoTag(((Integer)obj).intValue());
              goto _L18
_L9:
            codedoutputbytebuffernano.writeBoolNoTag(((Boolean)obj).booleanValue());
              goto _L18
_L10:
            codedoutputbytebuffernano.writeStringNoTag((String)obj);
              goto _L18
_L11:
            codedoutputbytebuffernano.writeBytesNoTag((byte[])obj);
              goto _L18
_L12:
            codedoutputbytebuffernano.writeUInt32NoTag(((Integer)obj).intValue());
              goto _L18
_L13:
            codedoutputbytebuffernano.writeEnumNoTag(((Integer)obj).intValue());
              goto _L18
_L14:
            codedoutputbytebuffernano.writeSFixed32NoTag(((Integer)obj).intValue());
              goto _L18
_L15:
            codedoutputbytebuffernano.writeSFixed64NoTag(((Long)obj).longValue());
              goto _L18
_L16:
            codedoutputbytebuffernano.writeSInt32NoTag(((Integer)obj).intValue());
              goto _L18
            codedoutputbytebuffernano.writeSInt64NoTag(((Long)obj).longValue());
              goto _L18
        }

        private final int nonPackedTag;
        private final int packedTag;

        public PrimitiveExtension(int i, Class class1, int j, boolean flag, int k, int l)
        {
            super(i, class1, j, flag, null);
            nonPackedTag = k;
            packedTag = l;
        }
    }


    private Extension(int i, Class class1, int j, boolean flag)
    {
        type = i;
        clazz = class1;
        tag = j;
        repeated = flag;
    }

    Extension(int i, Class class1, int j, boolean flag, Extension extension)
    {
        this(i, class1, j, flag);
    }

    public static Extension createMessageTyped(int i, Class class1, int j)
    {
        return new Extension(i, class1, j, false);
    }

    public static Extension createMessageTyped(int i, Class class1, long l)
    {
        return new Extension(i, class1, (int)l, false);
    }

    public static Extension createPrimitiveTyped(int i, Class class1, long l)
    {
        return new PrimitiveExtension(i, class1, (int)l, false, 0, 0);
    }

    public static Extension createRepeatedMessageTyped(int i, Class class1, long l)
    {
        return new Extension(i, class1, (int)l, true);
    }

    public static Extension createRepeatedPrimitiveTyped(int i, Class class1, long l, long l1, long l2)
    {
        return new PrimitiveExtension(i, class1, (int)l, true, (int)l1, (int)l2);
    }

    private Object getRepeatedValueFrom(List list)
    {
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < list.size(); i++)
        {
            UnknownFieldData unknownfielddata = (UnknownFieldData)list.get(i);
            if(unknownfielddata.bytes.length != 0)
                readDataInto(unknownfielddata, arraylist);
        }

        int k = arraylist.size();
        if(k == 0)
            return null;
        list = ((List) (clazz.cast(Array.newInstance(clazz.getComponentType(), k))));
        for(int j = 0; j < k; j++)
            Array.set(list, j, arraylist.get(j));

        return list;
    }

    private Object getSingularValueFrom(List list)
    {
        if(list.isEmpty())
        {
            return null;
        } else
        {
            list = (UnknownFieldData)list.get(list.size() - 1);
            return clazz.cast(readData(CodedInputByteBufferNano.newInstance(((UnknownFieldData) (list)).bytes)));
        }
    }

    protected int computeRepeatedSerializedSize(Object obj)
    {
        int i = 0;
        int j = Array.getLength(obj);
        for(int k = 0; k < j;)
        {
            int l = i;
            if(Array.get(obj, k) != null)
                l = i + computeSingularSerializedSize(Array.get(obj, k));
            k++;
            i = l;
        }

        return i;
    }

    int computeSerializedSize(Object obj)
    {
        if(repeated)
            return computeRepeatedSerializedSize(obj);
        else
            return computeSingularSerializedSize(obj);
    }

    protected int computeSingularSerializedSize(Object obj)
    {
        int i = WireFormatNano.getTagFieldNumber(tag);
        switch(type)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown type ").append(type).toString());

        case 10: // '\n'
            return CodedOutputByteBufferNano.computeGroupSize(i, (MessageNano)obj);

        case 11: // '\013'
            return CodedOutputByteBufferNano.computeMessageSize(i, (MessageNano)obj);
        }
    }

    final Object getValueFrom(List list)
    {
        if(list == null)
            return null;
        if(repeated)
            list = ((List) (getRepeatedValueFrom(list)));
        else
            list = ((List) (getSingularValueFrom(list)));
        return list;
    }

    protected Object readData(CodedInputByteBufferNano codedinputbytebuffernano)
    {
        Class class1;
        if(repeated)
            class1 = clazz.getComponentType();
        else
            class1 = clazz;
        type;
        JVM INSTR tableswitch 10 11: default 40
    //                   10 111
    //                   11 133;
           goto _L1 _L2 _L3
_L1:
        codedinputbytebuffernano = JVM INSTR new #170 <Class IllegalArgumentException>;
        StringBuilder stringbuilder = JVM INSTR new #172 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        codedinputbytebuffernano.IllegalArgumentException(stringbuilder.append("Unknown type ").append(type).toString());
        throw codedinputbytebuffernano;
_L2:
        MessageNano messagenano;
        try
        {
            messagenano = (MessageNano)class1.newInstance();
            codedinputbytebuffernano.readGroup(messagenano, WireFormatNano.getTagFieldNumber(tag));
        }
        // Misplaced declaration of an exception variable
        catch(CodedInputByteBufferNano codedinputbytebuffernano)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Error creating instance of class ").append(class1).toString(), codedinputbytebuffernano);
        }
        // Misplaced declaration of an exception variable
        catch(CodedInputByteBufferNano codedinputbytebuffernano)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Error creating instance of class ").append(class1).toString(), codedinputbytebuffernano);
        }
        // Misplaced declaration of an exception variable
        catch(CodedInputByteBufferNano codedinputbytebuffernano)
        {
            throw new IllegalArgumentException("Error reading extension field", codedinputbytebuffernano);
        }
        return messagenano;
_L3:
        messagenano = (MessageNano)class1.newInstance();
        codedinputbytebuffernano.readMessage(messagenano);
        return messagenano;
    }

    protected void readDataInto(UnknownFieldData unknownfielddata, List list)
    {
        list.add(readData(CodedInputByteBufferNano.newInstance(unknownfielddata.bytes)));
    }

    protected void writeRepeatedData(Object obj, CodedOutputByteBufferNano codedoutputbytebuffernano)
    {
        int i = Array.getLength(obj);
        for(int j = 0; j < i; j++)
        {
            Object obj1 = Array.get(obj, j);
            if(obj1 != null)
                writeSingularData(obj1, codedoutputbytebuffernano);
        }

    }

    protected void writeSingularData(Object obj, CodedOutputByteBufferNano codedoutputbytebuffernano)
    {
        codedoutputbytebuffernano.writeRawVarint32(tag);
        switch(type)
        {
        default:
            codedoutputbytebuffernano = JVM INSTR new #170 <Class IllegalArgumentException>;
            obj = JVM INSTR new #172 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            codedoutputbytebuffernano.IllegalArgumentException(((StringBuilder) (obj)).append("Unknown type ").append(type).toString());
            throw codedoutputbytebuffernano;

        case 11: // '\013'
            break MISSING_BLOCK_LABEL_105;

        case 10: // '\n'
            break;
        }
        obj = (MessageNano)obj;
        int i = WireFormatNano.getTagFieldNumber(tag);
        codedoutputbytebuffernano.writeGroupNoTag(((MessageNano) (obj)));
        codedoutputbytebuffernano.writeTag(i, 4);
_L1:
        return;
        try
        {
            codedoutputbytebuffernano.writeMessageNoTag((MessageNano)obj);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new IllegalStateException(((Throwable) (obj)));
        }
          goto _L1
    }

    void writeTo(Object obj, CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if(repeated)
            writeRepeatedData(obj, codedoutputbytebuffernano);
        else
            writeSingularData(obj, codedoutputbytebuffernano);
    }

    public static final int TYPE_BOOL = 8;
    public static final int TYPE_BYTES = 12;
    public static final int TYPE_DOUBLE = 1;
    public static final int TYPE_ENUM = 14;
    public static final int TYPE_FIXED32 = 7;
    public static final int TYPE_FIXED64 = 6;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_GROUP = 10;
    public static final int TYPE_INT32 = 5;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_MESSAGE = 11;
    public static final int TYPE_SFIXED32 = 15;
    public static final int TYPE_SFIXED64 = 16;
    public static final int TYPE_SINT32 = 17;
    public static final int TYPE_SINT64 = 18;
    public static final int TYPE_STRING = 9;
    public static final int TYPE_UINT32 = 13;
    public static final int TYPE_UINT64 = 4;
    protected final Class clazz;
    protected final boolean repeated;
    public final int tag;
    protected final int type;
}
