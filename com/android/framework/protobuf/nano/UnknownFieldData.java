// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package com.android.framework.protobuf.nano:
//            CodedOutputByteBufferNano

final class UnknownFieldData
{

    UnknownFieldData(int i, byte abyte0[])
    {
        tag = i;
        bytes = abyte0;
    }

    int computeSerializedSize()
    {
        return CodedOutputByteBufferNano.computeRawVarint32Size(tag) + 0 + bytes.length;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof UnknownFieldData))
            return false;
        obj = (UnknownFieldData)obj;
        if(tag == ((UnknownFieldData) (obj)).tag)
            flag = Arrays.equals(bytes, ((UnknownFieldData) (obj)).bytes);
        return flag;
    }

    public int hashCode()
    {
        return (tag + 527) * 31 + Arrays.hashCode(bytes);
    }

    void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        codedoutputbytebuffernano.writeRawVarint32(tag);
        codedoutputbytebuffernano.writeRawBytes(bytes);
    }

    final byte bytes[];
    final int tag;
}
