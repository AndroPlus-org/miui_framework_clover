// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;

// Referenced classes of package com.android.framework.protobuf.nano:
//            CodedInputByteBufferNano

public final class WireFormatNano
{

    private WireFormatNano()
    {
    }

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano codedinputbytebuffernano, int i)
        throws IOException
    {
        int j = 1;
        int k = codedinputbytebuffernano.getPosition();
        codedinputbytebuffernano.skipField(i);
        while(codedinputbytebuffernano.readTag() == i) 
        {
            codedinputbytebuffernano.skipField(i);
            j++;
        }
        codedinputbytebuffernano.rewindToPosition(k);
        return j;
    }

    public static int getTagFieldNumber(int i)
    {
        return i >>> 3;
    }

    static int getTagWireType(int i)
    {
        return i & 7;
    }

    static int makeTag(int i, int j)
    {
        return i << 3 | j;
    }

    public static boolean parseUnknownField(CodedInputByteBufferNano codedinputbytebuffernano, int i)
        throws IOException
    {
        return codedinputbytebuffernano.skipField(i);
    }

    public static final boolean EMPTY_BOOLEAN_ARRAY[] = new boolean[0];
    public static final byte EMPTY_BYTES[] = new byte[0];
    public static final byte EMPTY_BYTES_ARRAY[][] = new byte[0][];
    public static final double EMPTY_DOUBLE_ARRAY[] = new double[0];
    public static final float EMPTY_FLOAT_ARRAY[] = new float[0];
    public static final int EMPTY_INT_ARRAY[] = new int[0];
    public static final long EMPTY_LONG_ARRAY[] = new long[0];
    public static final String EMPTY_STRING_ARRAY[] = new String[0];
    static final int TAG_TYPE_BITS = 3;
    static final int TAG_TYPE_MASK = 7;
    static final int WIRETYPE_END_GROUP = 4;
    static final int WIRETYPE_FIXED32 = 5;
    static final int WIRETYPE_FIXED64 = 1;
    static final int WIRETYPE_LENGTH_DELIMITED = 2;
    static final int WIRETYPE_START_GROUP = 3;
    static final int WIRETYPE_VARINT = 0;

}
