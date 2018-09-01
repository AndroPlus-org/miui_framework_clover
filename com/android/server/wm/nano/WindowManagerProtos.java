// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.wm.nano;

import com.android.framework.protobuf.nano.*;
import java.io.IOException;

public interface WindowManagerProtos
{
    public static final class TaskSnapshotProto extends MessageNano
    {

        public static TaskSnapshotProto[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new TaskSnapshotProto[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static TaskSnapshotProto parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new TaskSnapshotProto()).mergeFrom(codedinputbytebuffernano);
        }

        public static TaskSnapshotProto parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (TaskSnapshotProto)MessageNano.mergeFrom(new TaskSnapshotProto(), abyte0);
        }

        public TaskSnapshotProto clear()
        {
            orientation = 0;
            insetLeft = 0;
            insetTop = 0;
            insetRight = 0;
            insetBottom = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(orientation != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, orientation);
            i = j;
            if(insetLeft != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, insetLeft);
            j = i;
            if(insetTop != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, insetTop);
            i = j;
            if(insetRight != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, insetRight);
            j = i;
            if(insetBottom != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(5, insetBottom);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public TaskSnapshotProto mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            do
            {
                int i = codedinputbytebuffernano.readTag();
                switch(i)
                {
                default:
                    if(!WireFormatNano.parseUnknownField(codedinputbytebuffernano, i))
                        return this;
                    break;

                case 0: // '\0'
                    return this;

                case 8: // '\b'
                    orientation = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    insetLeft = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    insetTop = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    insetRight = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    insetBottom = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(orientation != 0)
                codedoutputbytebuffernano.writeInt32(1, orientation);
            if(insetLeft != 0)
                codedoutputbytebuffernano.writeInt32(2, insetLeft);
            if(insetTop != 0)
                codedoutputbytebuffernano.writeInt32(3, insetTop);
            if(insetRight != 0)
                codedoutputbytebuffernano.writeInt32(4, insetRight);
            if(insetBottom != 0)
                codedoutputbytebuffernano.writeInt32(5, insetBottom);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile TaskSnapshotProto _emptyArray[];
        public int insetBottom;
        public int insetLeft;
        public int insetRight;
        public int insetTop;
        public int orientation;

        public TaskSnapshotProto()
        {
            clear();
        }
    }

}
