// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.location.nano;

import com.android.framework.protobuf.nano.*;
import java.io.IOException;

public interface GnssLogsProto
{
    public static final class GnssLog extends MessageNano
    {

        public static GnssLog[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new GnssLog[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static GnssLog parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new GnssLog()).mergeFrom(codedinputbytebuffernano);
        }

        public static GnssLog parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (GnssLog)MessageNano.mergeFrom(new GnssLog(), abyte0);
        }

        public GnssLog clear()
        {
            numLocationReportProcessed = 0;
            percentageLocationFailure = 0;
            numTimeToFirstFixProcessed = 0;
            meanTimeToFirstFixSecs = 0;
            standardDeviationTimeToFirstFixSecs = 0;
            numPositionAccuracyProcessed = 0;
            meanPositionAccuracyMeters = 0;
            standardDeviationPositionAccuracyMeters = 0;
            numTopFourAverageCn0Processed = 0;
            meanTopFourAverageCn0DbHz = 0.0D;
            standardDeviationTopFourAverageCn0DbHz = 0.0D;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(numLocationReportProcessed != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, numLocationReportProcessed);
            int k = j;
            if(percentageLocationFailure != 0)
                k = j + CodedOutputByteBufferNano.computeInt32Size(2, percentageLocationFailure);
            i = k;
            if(numTimeToFirstFixProcessed != 0)
                i = k + CodedOutputByteBufferNano.computeInt32Size(3, numTimeToFirstFixProcessed);
            j = i;
            if(meanTimeToFirstFixSecs != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(4, meanTimeToFirstFixSecs);
            k = j;
            if(standardDeviationTimeToFirstFixSecs != 0)
                k = j + CodedOutputByteBufferNano.computeInt32Size(5, standardDeviationTimeToFirstFixSecs);
            i = k;
            if(numPositionAccuracyProcessed != 0)
                i = k + CodedOutputByteBufferNano.computeInt32Size(6, numPositionAccuracyProcessed);
            j = i;
            if(meanPositionAccuracyMeters != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(7, meanPositionAccuracyMeters);
            i = j;
            if(standardDeviationPositionAccuracyMeters != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(8, standardDeviationPositionAccuracyMeters);
            j = i;
            if(numTopFourAverageCn0Processed != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(9, numTopFourAverageCn0Processed);
            i = j;
            if(Double.doubleToLongBits(meanTopFourAverageCn0DbHz) != Double.doubleToLongBits(0.0D))
                i = j + CodedOutputByteBufferNano.computeDoubleSize(10, meanTopFourAverageCn0DbHz);
            j = i;
            if(Double.doubleToLongBits(standardDeviationTopFourAverageCn0DbHz) != Double.doubleToLongBits(0.0D))
                j = i + CodedOutputByteBufferNano.computeDoubleSize(11, standardDeviationTopFourAverageCn0DbHz);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public GnssLog mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    numLocationReportProcessed = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    percentageLocationFailure = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    numTimeToFirstFixProcessed = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    meanTimeToFirstFixSecs = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    standardDeviationTimeToFirstFixSecs = codedinputbytebuffernano.readInt32();
                    break;

                case 48: // '0'
                    numPositionAccuracyProcessed = codedinputbytebuffernano.readInt32();
                    break;

                case 56: // '8'
                    meanPositionAccuracyMeters = codedinputbytebuffernano.readInt32();
                    break;

                case 64: // '@'
                    standardDeviationPositionAccuracyMeters = codedinputbytebuffernano.readInt32();
                    break;

                case 72: // 'H'
                    numTopFourAverageCn0Processed = codedinputbytebuffernano.readInt32();
                    break;

                case 81: // 'Q'
                    meanTopFourAverageCn0DbHz = codedinputbytebuffernano.readDouble();
                    break;

                case 89: // 'Y'
                    standardDeviationTopFourAverageCn0DbHz = codedinputbytebuffernano.readDouble();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(numLocationReportProcessed != 0)
                codedoutputbytebuffernano.writeInt32(1, numLocationReportProcessed);
            if(percentageLocationFailure != 0)
                codedoutputbytebuffernano.writeInt32(2, percentageLocationFailure);
            if(numTimeToFirstFixProcessed != 0)
                codedoutputbytebuffernano.writeInt32(3, numTimeToFirstFixProcessed);
            if(meanTimeToFirstFixSecs != 0)
                codedoutputbytebuffernano.writeInt32(4, meanTimeToFirstFixSecs);
            if(standardDeviationTimeToFirstFixSecs != 0)
                codedoutputbytebuffernano.writeInt32(5, standardDeviationTimeToFirstFixSecs);
            if(numPositionAccuracyProcessed != 0)
                codedoutputbytebuffernano.writeInt32(6, numPositionAccuracyProcessed);
            if(meanPositionAccuracyMeters != 0)
                codedoutputbytebuffernano.writeInt32(7, meanPositionAccuracyMeters);
            if(standardDeviationPositionAccuracyMeters != 0)
                codedoutputbytebuffernano.writeInt32(8, standardDeviationPositionAccuracyMeters);
            if(numTopFourAverageCn0Processed != 0)
                codedoutputbytebuffernano.writeInt32(9, numTopFourAverageCn0Processed);
            if(Double.doubleToLongBits(meanTopFourAverageCn0DbHz) != Double.doubleToLongBits(0.0D))
                codedoutputbytebuffernano.writeDouble(10, meanTopFourAverageCn0DbHz);
            if(Double.doubleToLongBits(standardDeviationTopFourAverageCn0DbHz) != Double.doubleToLongBits(0.0D))
                codedoutputbytebuffernano.writeDouble(11, standardDeviationTopFourAverageCn0DbHz);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile GnssLog _emptyArray[];
        public int meanPositionAccuracyMeters;
        public int meanTimeToFirstFixSecs;
        public double meanTopFourAverageCn0DbHz;
        public int numLocationReportProcessed;
        public int numPositionAccuracyProcessed;
        public int numTimeToFirstFixProcessed;
        public int numTopFourAverageCn0Processed;
        public int percentageLocationFailure;
        public int standardDeviationPositionAccuracyMeters;
        public int standardDeviationTimeToFirstFixSecs;
        public double standardDeviationTopFourAverageCn0DbHz;

        public GnssLog()
        {
            clear();
        }
    }

}
