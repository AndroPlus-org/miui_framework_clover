// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.connectivity.metrics.nano;

import com.android.framework.protobuf.nano.*;
import java.io.IOException;

public interface IpConnectivityLogClass
{
    public static final class ApfProgramEvent extends MessageNano
    {

        public static ApfProgramEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new ApfProgramEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static ApfProgramEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new ApfProgramEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static ApfProgramEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (ApfProgramEvent)MessageNano.mergeFrom(new ApfProgramEvent(), abyte0);
        }

        public ApfProgramEvent clear()
        {
            lifetime = 0L;
            effectiveLifetime = 0L;
            filteredRas = 0;
            currentRas = 0;
            programLength = 0;
            dropMulticast = false;
            hasIpv4Addr = false;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(lifetime != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(1, lifetime);
            i = j;
            if(filteredRas != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, filteredRas);
            j = i;
            if(currentRas != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, currentRas);
            int k = j;
            if(programLength != 0)
                k = j + CodedOutputByteBufferNano.computeInt32Size(4, programLength);
            i = k;
            if(dropMulticast)
                i = k + CodedOutputByteBufferNano.computeBoolSize(5, dropMulticast);
            j = i;
            if(hasIpv4Addr)
                j = i + CodedOutputByteBufferNano.computeBoolSize(6, hasIpv4Addr);
            i = j;
            if(effectiveLifetime != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(7, effectiveLifetime);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public ApfProgramEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    lifetime = codedinputbytebuffernano.readInt64();
                    break;

                case 16: // '\020'
                    filteredRas = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    currentRas = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    programLength = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    dropMulticast = codedinputbytebuffernano.readBool();
                    break;

                case 48: // '0'
                    hasIpv4Addr = codedinputbytebuffernano.readBool();
                    break;

                case 56: // '8'
                    effectiveLifetime = codedinputbytebuffernano.readInt64();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(lifetime != 0L)
                codedoutputbytebuffernano.writeInt64(1, lifetime);
            if(filteredRas != 0)
                codedoutputbytebuffernano.writeInt32(2, filteredRas);
            if(currentRas != 0)
                codedoutputbytebuffernano.writeInt32(3, currentRas);
            if(programLength != 0)
                codedoutputbytebuffernano.writeInt32(4, programLength);
            if(dropMulticast)
                codedoutputbytebuffernano.writeBool(5, dropMulticast);
            if(hasIpv4Addr)
                codedoutputbytebuffernano.writeBool(6, hasIpv4Addr);
            if(effectiveLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(7, effectiveLifetime);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile ApfProgramEvent _emptyArray[];
        public int currentRas;
        public boolean dropMulticast;
        public long effectiveLifetime;
        public int filteredRas;
        public boolean hasIpv4Addr;
        public long lifetime;
        public int programLength;

        public ApfProgramEvent()
        {
            clear();
        }
    }

    public static final class ApfStatistics extends MessageNano
    {

        public static ApfStatistics[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new ApfStatistics[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static ApfStatistics parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new ApfStatistics()).mergeFrom(codedinputbytebuffernano);
        }

        public static ApfStatistics parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (ApfStatistics)MessageNano.mergeFrom(new ApfStatistics(), abyte0);
        }

        public ApfStatistics clear()
        {
            durationMs = 0L;
            receivedRas = 0;
            matchingRas = 0;
            droppedRas = 0;
            zeroLifetimeRas = 0;
            parseErrors = 0;
            programUpdates = 0;
            maxProgramSize = 0;
            programUpdatesAll = 0;
            programUpdatesAllowingMulticast = 0;
            totalPacketProcessed = 0;
            totalPacketDropped = 0;
            hardwareCounters = Pair.emptyArray();
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int k = i;
            if(durationMs != 0L)
                k = i + CodedOutputByteBufferNano.computeInt64Size(1, durationMs);
            int l = k;
            if(receivedRas != 0)
                l = k + CodedOutputByteBufferNano.computeInt32Size(2, receivedRas);
            i = l;
            if(matchingRas != 0)
                i = l + CodedOutputByteBufferNano.computeInt32Size(3, matchingRas);
            k = i;
            if(droppedRas != 0)
                k = i + CodedOutputByteBufferNano.computeInt32Size(5, droppedRas);
            i = k;
            if(zeroLifetimeRas != 0)
                i = k + CodedOutputByteBufferNano.computeInt32Size(6, zeroLifetimeRas);
            k = i;
            if(parseErrors != 0)
                k = i + CodedOutputByteBufferNano.computeInt32Size(7, parseErrors);
            l = k;
            if(programUpdates != 0)
                l = k + CodedOutputByteBufferNano.computeInt32Size(8, programUpdates);
            i = l;
            if(maxProgramSize != 0)
                i = l + CodedOutputByteBufferNano.computeInt32Size(9, maxProgramSize);
            k = i;
            if(programUpdatesAll != 0)
                k = i + CodedOutputByteBufferNano.computeInt32Size(10, programUpdatesAll);
            l = k;
            if(programUpdatesAllowingMulticast != 0)
                l = k + CodedOutputByteBufferNano.computeInt32Size(11, programUpdatesAllowingMulticast);
            i = l;
            if(totalPacketProcessed != 0)
                i = l + CodedOutputByteBufferNano.computeInt32Size(12, totalPacketProcessed);
            k = i;
            if(totalPacketDropped != 0)
                k = i + CodedOutputByteBufferNano.computeInt32Size(13, totalPacketDropped);
            l = k;
            if(hardwareCounters != null)
            {
                l = k;
                if(hardwareCounters.length > 0)
                {
                    int j = 0;
                    do
                    {
                        l = k;
                        if(j >= hardwareCounters.length)
                            break;
                        Pair pair = hardwareCounters[j];
                        l = k;
                        if(pair != null)
                            l = k + CodedOutputByteBufferNano.computeMessageSize(14, pair);
                        j++;
                        k = l;
                    } while(true);
                }
            }
            return l;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public ApfStatistics mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    durationMs = codedinputbytebuffernano.readInt64();
                    break;

                case 16: // '\020'
                    receivedRas = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    matchingRas = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    droppedRas = codedinputbytebuffernano.readInt32();
                    break;

                case 48: // '0'
                    zeroLifetimeRas = codedinputbytebuffernano.readInt32();
                    break;

                case 56: // '8'
                    parseErrors = codedinputbytebuffernano.readInt32();
                    break;

                case 64: // '@'
                    programUpdates = codedinputbytebuffernano.readInt32();
                    break;

                case 72: // 'H'
                    maxProgramSize = codedinputbytebuffernano.readInt32();
                    break;

                case 80: // 'P'
                    programUpdatesAll = codedinputbytebuffernano.readInt32();
                    break;

                case 88: // 'X'
                    programUpdatesAllowingMulticast = codedinputbytebuffernano.readInt32();
                    break;

                case 96: // '`'
                    totalPacketProcessed = codedinputbytebuffernano.readInt32();
                    break;

                case 104: // 'h'
                    totalPacketDropped = codedinputbytebuffernano.readInt32();
                    break;

                case 114: // 'r'
                    int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 114);
                    int j;
                    Pair apair[];
                    if(hardwareCounters == null)
                        j = 0;
                    else
                        j = hardwareCounters.length;
                    apair = new Pair[j + k];
                    k = j;
                    if(j != 0)
                    {
                        System.arraycopy(hardwareCounters, 0, apair, 0, j);
                        k = j;
                    }
                    for(; k < apair.length - 1; k++)
                    {
                        apair[k] = new Pair();
                        codedinputbytebuffernano.readMessage(apair[k]);
                        codedinputbytebuffernano.readTag();
                    }

                    apair[k] = new Pair();
                    codedinputbytebuffernano.readMessage(apair[k]);
                    hardwareCounters = apair;
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(durationMs != 0L)
                codedoutputbytebuffernano.writeInt64(1, durationMs);
            if(receivedRas != 0)
                codedoutputbytebuffernano.writeInt32(2, receivedRas);
            if(matchingRas != 0)
                codedoutputbytebuffernano.writeInt32(3, matchingRas);
            if(droppedRas != 0)
                codedoutputbytebuffernano.writeInt32(5, droppedRas);
            if(zeroLifetimeRas != 0)
                codedoutputbytebuffernano.writeInt32(6, zeroLifetimeRas);
            if(parseErrors != 0)
                codedoutputbytebuffernano.writeInt32(7, parseErrors);
            if(programUpdates != 0)
                codedoutputbytebuffernano.writeInt32(8, programUpdates);
            if(maxProgramSize != 0)
                codedoutputbytebuffernano.writeInt32(9, maxProgramSize);
            if(programUpdatesAll != 0)
                codedoutputbytebuffernano.writeInt32(10, programUpdatesAll);
            if(programUpdatesAllowingMulticast != 0)
                codedoutputbytebuffernano.writeInt32(11, programUpdatesAllowingMulticast);
            if(totalPacketProcessed != 0)
                codedoutputbytebuffernano.writeInt32(12, totalPacketProcessed);
            if(totalPacketDropped != 0)
                codedoutputbytebuffernano.writeInt32(13, totalPacketDropped);
            if(hardwareCounters != null && hardwareCounters.length > 0)
            {
                for(int i = 0; i < hardwareCounters.length; i++)
                {
                    Pair pair = hardwareCounters[i];
                    if(pair != null)
                        codedoutputbytebuffernano.writeMessage(14, pair);
                }

            }
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile ApfStatistics _emptyArray[];
        public int droppedRas;
        public long durationMs;
        public Pair hardwareCounters[];
        public int matchingRas;
        public int maxProgramSize;
        public int parseErrors;
        public int programUpdates;
        public int programUpdatesAll;
        public int programUpdatesAllowingMulticast;
        public int receivedRas;
        public int totalPacketDropped;
        public int totalPacketProcessed;
        public int zeroLifetimeRas;

        public ApfStatistics()
        {
            clear();
        }
    }

    public static final class ConnectStatistics extends MessageNano
    {

        public static ConnectStatistics[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new ConnectStatistics[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static ConnectStatistics parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new ConnectStatistics()).mergeFrom(codedinputbytebuffernano);
        }

        public static ConnectStatistics parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (ConnectStatistics)MessageNano.mergeFrom(new ConnectStatistics(), abyte0);
        }

        public ConnectStatistics clear()
        {
            connectCount = 0;
            connectBlockingCount = 0;
            ipv6AddrCount = 0;
            latenciesMs = WireFormatNano.EMPTY_INT_ARRAY;
            nonBlockingLatenciesMs = WireFormatNano.EMPTY_INT_ARRAY;
            errnosCounters = Pair.emptyArray();
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(connectCount != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, connectCount);
            i = j;
            if(ipv6AddrCount != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, ipv6AddrCount);
            j = i;
            if(latenciesMs != null)
            {
                j = i;
                if(latenciesMs.length > 0)
                {
                    int k = 0;
                    for(j = 0; j < latenciesMs.length; j++)
                        k += CodedOutputByteBufferNano.computeInt32SizeNoTag(latenciesMs[j]);

                    j = i + k + latenciesMs.length * 1;
                }
            }
            i = j;
            if(errnosCounters != null)
            {
                i = j;
                if(errnosCounters.length > 0)
                {
                    int l = 0;
                    do
                    {
                        i = j;
                        if(l >= errnosCounters.length)
                            break;
                        Pair pair = errnosCounters[l];
                        i = j;
                        if(pair != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(4, pair);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(connectBlockingCount != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(5, connectBlockingCount);
            i = j;
            if(nonBlockingLatenciesMs != null)
            {
                i = j;
                if(nonBlockingLatenciesMs.length > 0)
                {
                    int i1 = 0;
                    for(i = 0; i < nonBlockingLatenciesMs.length; i++)
                        i1 += CodedOutputByteBufferNano.computeInt32SizeNoTag(nonBlockingLatenciesMs[i]);

                    i = j + i1 + nonBlockingLatenciesMs.length * 1;
                }
            }
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public ConnectStatistics mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    connectCount = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    ipv6AddrCount = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    int k1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 24);
                    int j;
                    int ai[];
                    if(latenciesMs == null)
                        j = 0;
                    else
                        j = latenciesMs.length;
                    ai = new int[j + k1];
                    k1 = j;
                    if(j != 0)
                    {
                        System.arraycopy(latenciesMs, 0, ai, 0, j);
                        k1 = j;
                    }
                    for(; k1 < ai.length - 1; k1++)
                    {
                        ai[k1] = codedinputbytebuffernano.readInt32();
                        codedinputbytebuffernano.readTag();
                    }

                    ai[k1] = codedinputbytebuffernano.readInt32();
                    latenciesMs = ai;
                    break;

                case 26: // '\032'
                    int l2 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                    int l1 = 0;
                    int k = codedinputbytebuffernano.getPosition();
                    while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
                    {
                        codedinputbytebuffernano.readInt32();
                        l1++;
                    }
                    codedinputbytebuffernano.rewindToPosition(k);
                    int ai1[];
                    if(latenciesMs == null)
                        k = 0;
                    else
                        k = latenciesMs.length;
                    ai1 = new int[k + l1];
                    l1 = k;
                    if(k != 0)
                    {
                        System.arraycopy(latenciesMs, 0, ai1, 0, k);
                        l1 = k;
                    }
                    for(; l1 < ai1.length; l1++)
                        ai1[l1] = codedinputbytebuffernano.readInt32();

                    latenciesMs = ai1;
                    codedinputbytebuffernano.popLimit(l2);
                    break;

                case 34: // '"'
                    int i2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 34);
                    int l;
                    Pair apair[];
                    if(errnosCounters == null)
                        l = 0;
                    else
                        l = errnosCounters.length;
                    apair = new Pair[l + i2];
                    i2 = l;
                    if(l != 0)
                    {
                        System.arraycopy(errnosCounters, 0, apair, 0, l);
                        i2 = l;
                    }
                    for(; i2 < apair.length - 1; i2++)
                    {
                        apair[i2] = new Pair();
                        codedinputbytebuffernano.readMessage(apair[i2]);
                        codedinputbytebuffernano.readTag();
                    }

                    apair[i2] = new Pair();
                    codedinputbytebuffernano.readMessage(apair[i2]);
                    errnosCounters = apair;
                    break;

                case 40: // '('
                    connectBlockingCount = codedinputbytebuffernano.readInt32();
                    break;

                case 48: // '0'
                    int j2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 48);
                    int i1;
                    int ai2[];
                    if(nonBlockingLatenciesMs == null)
                        i1 = 0;
                    else
                        i1 = nonBlockingLatenciesMs.length;
                    ai2 = new int[i1 + j2];
                    j2 = i1;
                    if(i1 != 0)
                    {
                        System.arraycopy(nonBlockingLatenciesMs, 0, ai2, 0, i1);
                        j2 = i1;
                    }
                    for(; j2 < ai2.length - 1; j2++)
                    {
                        ai2[j2] = codedinputbytebuffernano.readInt32();
                        codedinputbytebuffernano.readTag();
                    }

                    ai2[j2] = codedinputbytebuffernano.readInt32();
                    nonBlockingLatenciesMs = ai2;
                    break;

                case 50: // '2'
                    int i3 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                    int k2 = 0;
                    int j1 = codedinputbytebuffernano.getPosition();
                    while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
                    {
                        codedinputbytebuffernano.readInt32();
                        k2++;
                    }
                    codedinputbytebuffernano.rewindToPosition(j1);
                    int ai3[];
                    if(nonBlockingLatenciesMs == null)
                        j1 = 0;
                    else
                        j1 = nonBlockingLatenciesMs.length;
                    ai3 = new int[j1 + k2];
                    k2 = j1;
                    if(j1 != 0)
                    {
                        System.arraycopy(nonBlockingLatenciesMs, 0, ai3, 0, j1);
                        k2 = j1;
                    }
                    for(; k2 < ai3.length; k2++)
                        ai3[k2] = codedinputbytebuffernano.readInt32();

                    nonBlockingLatenciesMs = ai3;
                    codedinputbytebuffernano.popLimit(i3);
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(connectCount != 0)
                codedoutputbytebuffernano.writeInt32(1, connectCount);
            if(ipv6AddrCount != 0)
                codedoutputbytebuffernano.writeInt32(2, ipv6AddrCount);
            if(latenciesMs != null && latenciesMs.length > 0)
            {
                for(int i = 0; i < latenciesMs.length; i++)
                    codedoutputbytebuffernano.writeInt32(3, latenciesMs[i]);

            }
            if(errnosCounters != null && errnosCounters.length > 0)
            {
                for(int j = 0; j < errnosCounters.length; j++)
                {
                    Pair pair = errnosCounters[j];
                    if(pair != null)
                        codedoutputbytebuffernano.writeMessage(4, pair);
                }

            }
            if(connectBlockingCount != 0)
                codedoutputbytebuffernano.writeInt32(5, connectBlockingCount);
            if(nonBlockingLatenciesMs != null && nonBlockingLatenciesMs.length > 0)
            {
                for(int k = 0; k < nonBlockingLatenciesMs.length; k++)
                    codedoutputbytebuffernano.writeInt32(6, nonBlockingLatenciesMs[k]);

            }
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile ConnectStatistics _emptyArray[];
        public int connectBlockingCount;
        public int connectCount;
        public Pair errnosCounters[];
        public int ipv6AddrCount;
        public int latenciesMs[];
        public int nonBlockingLatenciesMs[];

        public ConnectStatistics()
        {
            clear();
        }
    }

    public static final class DHCPEvent extends MessageNano
    {

        public static DHCPEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new DHCPEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static DHCPEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new DHCPEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static DHCPEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (DHCPEvent)MessageNano.mergeFrom(new DHCPEvent(), abyte0);
        }

        public DHCPEvent clear()
        {
            ifName = "";
            durationMs = 0;
            clearValue();
            cachedSize = -1;
            return this;
        }

        public DHCPEvent clearValue()
        {
            valueCase_ = 0;
            value_ = null;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(!ifName.equals(""))
                j = i + CodedOutputByteBufferNano.computeStringSize(1, ifName);
            i = j;
            if(valueCase_ == 2)
                i = j + CodedOutputByteBufferNano.computeStringSize(2, (String)value_);
            j = i;
            if(valueCase_ == 3)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, ((Integer)value_).intValue());
            i = j;
            if(durationMs != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, durationMs);
            return i;
        }

        public int getErrorCode()
        {
            if(valueCase_ == 3)
                return ((Integer)value_).intValue();
            else
                return 0;
        }

        public String getStateTransition()
        {
            if(valueCase_ == 2)
                return (String)value_;
            else
                return "";
        }

        public int getValueCase()
        {
            return valueCase_;
        }

        public boolean hasErrorCode()
        {
            boolean flag;
            if(valueCase_ == 3)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasStateTransition()
        {
            boolean flag;
            if(valueCase_ == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public DHCPEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    ifName = codedinputbytebuffernano.readString();
                    break;

                case 18: // '\022'
                    value_ = codedinputbytebuffernano.readString();
                    valueCase_ = 2;
                    break;

                case 24: // '\030'
                    value_ = Integer.valueOf(codedinputbytebuffernano.readInt32());
                    valueCase_ = 3;
                    break;

                case 32: // ' '
                    durationMs = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public DHCPEvent setErrorCode(int i)
        {
            valueCase_ = 3;
            value_ = Integer.valueOf(i);
            return this;
        }

        public DHCPEvent setStateTransition(String s)
        {
            valueCase_ = 2;
            value_ = s;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(!ifName.equals(""))
                codedoutputbytebuffernano.writeString(1, ifName);
            if(valueCase_ == 2)
                codedoutputbytebuffernano.writeString(2, (String)value_);
            if(valueCase_ == 3)
                codedoutputbytebuffernano.writeInt32(3, ((Integer)value_).intValue());
            if(durationMs != 0)
                codedoutputbytebuffernano.writeInt32(4, durationMs);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int ERROR_CODE_FIELD_NUMBER = 3;
        public static final int STATE_TRANSITION_FIELD_NUMBER = 2;
        private static volatile DHCPEvent _emptyArray[];
        public int durationMs;
        public String ifName;
        private int valueCase_;
        private Object value_;

        public DHCPEvent()
        {
            valueCase_ = 0;
            clear();
        }
    }

    public static final class DNSLatencies extends MessageNano
    {

        public static DNSLatencies[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new DNSLatencies[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static DNSLatencies parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new DNSLatencies()).mergeFrom(codedinputbytebuffernano);
        }

        public static DNSLatencies parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (DNSLatencies)MessageNano.mergeFrom(new DNSLatencies(), abyte0);
        }

        public DNSLatencies clear()
        {
            type = 0;
            returnCode = 0;
            queryCount = 0;
            aCount = 0;
            aaaaCount = 0;
            latenciesMs = WireFormatNano.EMPTY_INT_ARRAY;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(type != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, type);
            i = j;
            if(returnCode != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, returnCode);
            j = i;
            if(queryCount != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, queryCount);
            i = j;
            if(aCount != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, aCount);
            j = i;
            if(aaaaCount != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(5, aaaaCount);
            i = j;
            if(latenciesMs != null)
            {
                i = j;
                if(latenciesMs.length > 0)
                {
                    int k = 0;
                    for(i = 0; i < latenciesMs.length; i++)
                        k += CodedOutputByteBufferNano.computeInt32SizeNoTag(latenciesMs[i]);

                    i = j + k + latenciesMs.length * 1;
                }
            }
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public DNSLatencies mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    type = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    returnCode = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    queryCount = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    aCount = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    aaaaCount = codedinputbytebuffernano.readInt32();
                    break;

                case 48: // '0'
                    int l = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 48);
                    int j;
                    int ai[];
                    if(latenciesMs == null)
                        j = 0;
                    else
                        j = latenciesMs.length;
                    ai = new int[j + l];
                    l = j;
                    if(j != 0)
                    {
                        System.arraycopy(latenciesMs, 0, ai, 0, j);
                        l = j;
                    }
                    for(; l < ai.length - 1; l++)
                    {
                        ai[l] = codedinputbytebuffernano.readInt32();
                        codedinputbytebuffernano.readTag();
                    }

                    ai[l] = codedinputbytebuffernano.readInt32();
                    latenciesMs = ai;
                    break;

                case 50: // '2'
                    int j1 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                    int i1 = 0;
                    int k = codedinputbytebuffernano.getPosition();
                    while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
                    {
                        codedinputbytebuffernano.readInt32();
                        i1++;
                    }
                    codedinputbytebuffernano.rewindToPosition(k);
                    int ai1[];
                    if(latenciesMs == null)
                        k = 0;
                    else
                        k = latenciesMs.length;
                    ai1 = new int[k + i1];
                    i1 = k;
                    if(k != 0)
                    {
                        System.arraycopy(latenciesMs, 0, ai1, 0, k);
                        i1 = k;
                    }
                    for(; i1 < ai1.length; i1++)
                        ai1[i1] = codedinputbytebuffernano.readInt32();

                    latenciesMs = ai1;
                    codedinputbytebuffernano.popLimit(j1);
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(type != 0)
                codedoutputbytebuffernano.writeInt32(1, type);
            if(returnCode != 0)
                codedoutputbytebuffernano.writeInt32(2, returnCode);
            if(queryCount != 0)
                codedoutputbytebuffernano.writeInt32(3, queryCount);
            if(aCount != 0)
                codedoutputbytebuffernano.writeInt32(4, aCount);
            if(aaaaCount != 0)
                codedoutputbytebuffernano.writeInt32(5, aaaaCount);
            if(latenciesMs != null && latenciesMs.length > 0)
            {
                for(int i = 0; i < latenciesMs.length; i++)
                    codedoutputbytebuffernano.writeInt32(6, latenciesMs[i]);

            }
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile DNSLatencies _emptyArray[];
        public int aCount;
        public int aaaaCount;
        public int latenciesMs[];
        public int queryCount;
        public int returnCode;
        public int type;

        public DNSLatencies()
        {
            clear();
        }
    }

    public static final class DNSLookupBatch extends MessageNano
    {

        public static DNSLookupBatch[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new DNSLookupBatch[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static DNSLookupBatch parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new DNSLookupBatch()).mergeFrom(codedinputbytebuffernano);
        }

        public static DNSLookupBatch parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (DNSLookupBatch)MessageNano.mergeFrom(new DNSLookupBatch(), abyte0);
        }

        public DNSLookupBatch clear()
        {
            latenciesMs = WireFormatNano.EMPTY_INT_ARRAY;
            getaddrinfoQueryCount = 0L;
            gethostbynameQueryCount = 0L;
            getaddrinfoErrorCount = 0L;
            gethostbynameErrorCount = 0L;
            getaddrinfoErrors = Pair.emptyArray();
            gethostbynameErrors = Pair.emptyArray();
            networkId = null;
            eventTypes = WireFormatNano.EMPTY_INT_ARRAY;
            returnCodes = WireFormatNano.EMPTY_INT_ARRAY;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(networkId != null)
                j = i + CodedOutputByteBufferNano.computeMessageSize(1, networkId);
            i = j;
            if(eventTypes != null)
            {
                i = j;
                if(eventTypes.length > 0)
                {
                    int l = 0;
                    for(i = 0; i < eventTypes.length; i++)
                        l += CodedOutputByteBufferNano.computeInt32SizeNoTag(eventTypes[i]);

                    i = j + l + eventTypes.length * 1;
                }
            }
            j = i;
            if(returnCodes != null)
            {
                j = i;
                if(returnCodes.length > 0)
                {
                    int i1 = 0;
                    for(j = 0; j < returnCodes.length; j++)
                        i1 += CodedOutputByteBufferNano.computeInt32SizeNoTag(returnCodes[j]);

                    j = i + i1 + returnCodes.length * 1;
                }
            }
            int j1 = j;
            if(latenciesMs != null)
            {
                j1 = j;
                if(latenciesMs.length > 0)
                {
                    j1 = 0;
                    for(i = 0; i < latenciesMs.length; i++)
                        j1 += CodedOutputByteBufferNano.computeInt32SizeNoTag(latenciesMs[i]);

                    j1 = j + j1 + latenciesMs.length * 1;
                }
            }
            i = j1;
            if(getaddrinfoQueryCount != 0L)
                i = j1 + CodedOutputByteBufferNano.computeInt64Size(5, getaddrinfoQueryCount);
            j = i;
            if(gethostbynameQueryCount != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(6, gethostbynameQueryCount);
            i = j;
            if(getaddrinfoErrorCount != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(7, getaddrinfoErrorCount);
            j = i;
            if(gethostbynameErrorCount != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(8, gethostbynameErrorCount);
            i = j;
            if(getaddrinfoErrors != null)
            {
                i = j;
                if(getaddrinfoErrors.length > 0)
                {
                    j1 = 0;
                    do
                    {
                        i = j;
                        if(j1 >= getaddrinfoErrors.length)
                            break;
                        Pair pair = getaddrinfoErrors[j1];
                        i = j;
                        if(pair != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(9, pair);
                        j1++;
                        j = i;
                    } while(true);
                }
            }
            j1 = i;
            if(gethostbynameErrors != null)
            {
                j1 = i;
                if(gethostbynameErrors.length > 0)
                {
                    int k = 0;
                    do
                    {
                        j1 = i;
                        if(k >= gethostbynameErrors.length)
                            break;
                        Pair pair1 = gethostbynameErrors[k];
                        j1 = i;
                        if(pair1 != null)
                            j1 = i + CodedOutputByteBufferNano.computeMessageSize(10, pair1);
                        k++;
                        i = j1;
                    } while(true);
                }
            }
            return j1;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public DNSLookupBatch mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    if(networkId == null)
                        networkId = new NetworkId();
                    codedinputbytebuffernano.readMessage(networkId);
                    break;

                case 16: // '\020'
                    int j2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 16);
                    int j;
                    int ai[];
                    if(eventTypes == null)
                        j = 0;
                    else
                        j = eventTypes.length;
                    ai = new int[j + j2];
                    j2 = j;
                    if(j != 0)
                    {
                        System.arraycopy(eventTypes, 0, ai, 0, j);
                        j2 = j;
                    }
                    for(; j2 < ai.length - 1; j2++)
                    {
                        ai[j2] = codedinputbytebuffernano.readInt32();
                        codedinputbytebuffernano.readTag();
                    }

                    ai[j2] = codedinputbytebuffernano.readInt32();
                    eventTypes = ai;
                    break;

                case 18: // '\022'
                    int j4 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                    int k2 = 0;
                    int k = codedinputbytebuffernano.getPosition();
                    while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
                    {
                        codedinputbytebuffernano.readInt32();
                        k2++;
                    }
                    codedinputbytebuffernano.rewindToPosition(k);
                    int ai1[];
                    if(eventTypes == null)
                        k = 0;
                    else
                        k = eventTypes.length;
                    ai1 = new int[k + k2];
                    k2 = k;
                    if(k != 0)
                    {
                        System.arraycopy(eventTypes, 0, ai1, 0, k);
                        k2 = k;
                    }
                    for(; k2 < ai1.length; k2++)
                        ai1[k2] = codedinputbytebuffernano.readInt32();

                    eventTypes = ai1;
                    codedinputbytebuffernano.popLimit(j4);
                    break;

                case 24: // '\030'
                    int l2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 24);
                    int l;
                    int ai2[];
                    if(returnCodes == null)
                        l = 0;
                    else
                        l = returnCodes.length;
                    ai2 = new int[l + l2];
                    l2 = l;
                    if(l != 0)
                    {
                        System.arraycopy(returnCodes, 0, ai2, 0, l);
                        l2 = l;
                    }
                    for(; l2 < ai2.length - 1; l2++)
                    {
                        ai2[l2] = codedinputbytebuffernano.readInt32();
                        codedinputbytebuffernano.readTag();
                    }

                    ai2[l2] = codedinputbytebuffernano.readInt32();
                    returnCodes = ai2;
                    break;

                case 26: // '\032'
                    int k4 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                    int i3 = 0;
                    int i1 = codedinputbytebuffernano.getPosition();
                    while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
                    {
                        codedinputbytebuffernano.readInt32();
                        i3++;
                    }
                    codedinputbytebuffernano.rewindToPosition(i1);
                    int ai3[];
                    if(returnCodes == null)
                        i1 = 0;
                    else
                        i1 = returnCodes.length;
                    ai3 = new int[i1 + i3];
                    i3 = i1;
                    if(i1 != 0)
                    {
                        System.arraycopy(returnCodes, 0, ai3, 0, i1);
                        i3 = i1;
                    }
                    for(; i3 < ai3.length; i3++)
                        ai3[i3] = codedinputbytebuffernano.readInt32();

                    returnCodes = ai3;
                    codedinputbytebuffernano.popLimit(k4);
                    break;

                case 32: // ' '
                    int j3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 32);
                    int j1;
                    int ai4[];
                    if(latenciesMs == null)
                        j1 = 0;
                    else
                        j1 = latenciesMs.length;
                    ai4 = new int[j1 + j3];
                    j3 = j1;
                    if(j1 != 0)
                    {
                        System.arraycopy(latenciesMs, 0, ai4, 0, j1);
                        j3 = j1;
                    }
                    for(; j3 < ai4.length - 1; j3++)
                    {
                        ai4[j3] = codedinputbytebuffernano.readInt32();
                        codedinputbytebuffernano.readTag();
                    }

                    ai4[j3] = codedinputbytebuffernano.readInt32();
                    latenciesMs = ai4;
                    break;

                case 34: // '"'
                    int l4 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
                    int k3 = 0;
                    int k1 = codedinputbytebuffernano.getPosition();
                    while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
                    {
                        codedinputbytebuffernano.readInt32();
                        k3++;
                    }
                    codedinputbytebuffernano.rewindToPosition(k1);
                    int ai5[];
                    if(latenciesMs == null)
                        k1 = 0;
                    else
                        k1 = latenciesMs.length;
                    ai5 = new int[k1 + k3];
                    k3 = k1;
                    if(k1 != 0)
                    {
                        System.arraycopy(latenciesMs, 0, ai5, 0, k1);
                        k3 = k1;
                    }
                    for(; k3 < ai5.length; k3++)
                        ai5[k3] = codedinputbytebuffernano.readInt32();

                    latenciesMs = ai5;
                    codedinputbytebuffernano.popLimit(l4);
                    break;

                case 40: // '('
                    getaddrinfoQueryCount = codedinputbytebuffernano.readInt64();
                    break;

                case 48: // '0'
                    gethostbynameQueryCount = codedinputbytebuffernano.readInt64();
                    break;

                case 56: // '8'
                    getaddrinfoErrorCount = codedinputbytebuffernano.readInt64();
                    break;

                case 64: // '@'
                    gethostbynameErrorCount = codedinputbytebuffernano.readInt64();
                    break;

                case 74: // 'J'
                    int l3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 74);
                    int l1;
                    Pair apair[];
                    if(getaddrinfoErrors == null)
                        l1 = 0;
                    else
                        l1 = getaddrinfoErrors.length;
                    apair = new Pair[l1 + l3];
                    l3 = l1;
                    if(l1 != 0)
                    {
                        System.arraycopy(getaddrinfoErrors, 0, apair, 0, l1);
                        l3 = l1;
                    }
                    for(; l3 < apair.length - 1; l3++)
                    {
                        apair[l3] = new Pair();
                        codedinputbytebuffernano.readMessage(apair[l3]);
                        codedinputbytebuffernano.readTag();
                    }

                    apair[l3] = new Pair();
                    codedinputbytebuffernano.readMessage(apair[l3]);
                    getaddrinfoErrors = apair;
                    break;

                case 82: // 'R'
                    int i4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 82);
                    int i2;
                    Pair apair1[];
                    if(gethostbynameErrors == null)
                        i2 = 0;
                    else
                        i2 = gethostbynameErrors.length;
                    apair1 = new Pair[i2 + i4];
                    i4 = i2;
                    if(i2 != 0)
                    {
                        System.arraycopy(gethostbynameErrors, 0, apair1, 0, i2);
                        i4 = i2;
                    }
                    for(; i4 < apair1.length - 1; i4++)
                    {
                        apair1[i4] = new Pair();
                        codedinputbytebuffernano.readMessage(apair1[i4]);
                        codedinputbytebuffernano.readTag();
                    }

                    apair1[i4] = new Pair();
                    codedinputbytebuffernano.readMessage(apair1[i4]);
                    gethostbynameErrors = apair1;
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(networkId != null)
                codedoutputbytebuffernano.writeMessage(1, networkId);
            if(eventTypes != null && eventTypes.length > 0)
            {
                for(int i = 0; i < eventTypes.length; i++)
                    codedoutputbytebuffernano.writeInt32(2, eventTypes[i]);

            }
            if(returnCodes != null && returnCodes.length > 0)
            {
                for(int j = 0; j < returnCodes.length; j++)
                    codedoutputbytebuffernano.writeInt32(3, returnCodes[j]);

            }
            if(latenciesMs != null && latenciesMs.length > 0)
            {
                for(int k = 0; k < latenciesMs.length; k++)
                    codedoutputbytebuffernano.writeInt32(4, latenciesMs[k]);

            }
            if(getaddrinfoQueryCount != 0L)
                codedoutputbytebuffernano.writeInt64(5, getaddrinfoQueryCount);
            if(gethostbynameQueryCount != 0L)
                codedoutputbytebuffernano.writeInt64(6, gethostbynameQueryCount);
            if(getaddrinfoErrorCount != 0L)
                codedoutputbytebuffernano.writeInt64(7, getaddrinfoErrorCount);
            if(gethostbynameErrorCount != 0L)
                codedoutputbytebuffernano.writeInt64(8, gethostbynameErrorCount);
            if(getaddrinfoErrors != null && getaddrinfoErrors.length > 0)
            {
                for(int l = 0; l < getaddrinfoErrors.length; l++)
                {
                    Pair pair = getaddrinfoErrors[l];
                    if(pair != null)
                        codedoutputbytebuffernano.writeMessage(9, pair);
                }

            }
            if(gethostbynameErrors != null && gethostbynameErrors.length > 0)
            {
                for(int i1 = 0; i1 < gethostbynameErrors.length; i1++)
                {
                    Pair pair1 = gethostbynameErrors[i1];
                    if(pair1 != null)
                        codedoutputbytebuffernano.writeMessage(10, pair1);
                }

            }
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile DNSLookupBatch _emptyArray[];
        public int eventTypes[];
        public long getaddrinfoErrorCount;
        public Pair getaddrinfoErrors[];
        public long getaddrinfoQueryCount;
        public long gethostbynameErrorCount;
        public Pair gethostbynameErrors[];
        public long gethostbynameQueryCount;
        public int latenciesMs[];
        public NetworkId networkId;
        public int returnCodes[];

        public DNSLookupBatch()
        {
            clear();
        }
    }

    public static final class DefaultNetworkEvent extends MessageNano
    {

        public static DefaultNetworkEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new DefaultNetworkEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static DefaultNetworkEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new DefaultNetworkEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static DefaultNetworkEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (DefaultNetworkEvent)MessageNano.mergeFrom(new DefaultNetworkEvent(), abyte0);
        }

        public DefaultNetworkEvent clear()
        {
            defaultNetworkDurationMs = 0L;
            noDefaultNetworkDurationMs = 0L;
            initialScore = 0L;
            finalScore = 0L;
            ipSupport = 0;
            networkId = null;
            previousNetworkId = null;
            previousNetworkIpSupport = 0;
            transportTypes = WireFormatNano.EMPTY_INT_ARRAY;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(networkId != null)
                j = i + CodedOutputByteBufferNano.computeMessageSize(1, networkId);
            i = j;
            if(previousNetworkId != null)
                i = j + CodedOutputByteBufferNano.computeMessageSize(2, previousNetworkId);
            j = i;
            if(previousNetworkIpSupport != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, previousNetworkIpSupport);
            i = j;
            if(transportTypes != null)
            {
                i = j;
                if(transportTypes.length > 0)
                {
                    int k = 0;
                    for(i = 0; i < transportTypes.length; i++)
                        k += CodedOutputByteBufferNano.computeInt32SizeNoTag(transportTypes[i]);

                    i = j + k + transportTypes.length * 1;
                }
            }
            int l = i;
            if(defaultNetworkDurationMs != 0L)
                l = i + CodedOutputByteBufferNano.computeInt64Size(5, defaultNetworkDurationMs);
            j = l;
            if(noDefaultNetworkDurationMs != 0L)
                j = l + CodedOutputByteBufferNano.computeInt64Size(6, noDefaultNetworkDurationMs);
            i = j;
            if(initialScore != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(7, initialScore);
            j = i;
            if(finalScore != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(8, finalScore);
            i = j;
            if(ipSupport != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(9, ipSupport);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public DefaultNetworkEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L14:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 11: default 104
        //                       0: 114
        //                       10: 116
        //                       18: 145
        //                       24: 174
        //                       32: 223
        //                       34: 320
        //                       40: 441
        //                       48: 452
        //                       56: 463
        //                       64: 474
        //                       72: 485;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L14; else goto _L13
_L13:
            return this;
_L2:
            return this;
_L3:
            if(networkId == null)
                networkId = new NetworkId();
            codedinputbytebuffernano.readMessage(networkId);
              goto _L14
_L4:
            if(previousNetworkId == null)
                previousNetworkId = new NetworkId();
            codedinputbytebuffernano.readMessage(previousNetworkId);
              goto _L14
_L5:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                previousNetworkIpSupport = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            int j1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 32);
            int k;
            int ai[];
            if(transportTypes == null)
                k = 0;
            else
                k = transportTypes.length;
            ai = new int[k + j1];
            j1 = k;
            if(k != 0)
            {
                System.arraycopy(transportTypes, 0, ai, 0, k);
                j1 = k;
            }
            for(; j1 < ai.length - 1; j1++)
            {
                ai[j1] = codedinputbytebuffernano.readInt32();
                codedinputbytebuffernano.readTag();
            }

            ai[j1] = codedinputbytebuffernano.readInt32();
            transportTypes = ai;
            continue; /* Loop/switch isn't completed */
_L7:
            int l1 = codedinputbytebuffernano.pushLimit(codedinputbytebuffernano.readRawVarint32());
            int k1 = 0;
            int l = codedinputbytebuffernano.getPosition();
            while(codedinputbytebuffernano.getBytesUntilLimit() > 0) 
            {
                codedinputbytebuffernano.readInt32();
                k1++;
            }
            codedinputbytebuffernano.rewindToPosition(l);
            int ai1[];
            if(transportTypes == null)
                l = 0;
            else
                l = transportTypes.length;
            ai1 = new int[l + k1];
            k1 = l;
            if(l != 0)
            {
                System.arraycopy(transportTypes, 0, ai1, 0, l);
                k1 = l;
            }
            for(; k1 < ai1.length; k1++)
                ai1[k1] = codedinputbytebuffernano.readInt32();

            transportTypes = ai1;
            codedinputbytebuffernano.popLimit(l1);
            continue; /* Loop/switch isn't completed */
_L8:
            defaultNetworkDurationMs = codedinputbytebuffernano.readInt64();
            continue; /* Loop/switch isn't completed */
_L9:
            noDefaultNetworkDurationMs = codedinputbytebuffernano.readInt64();
            continue; /* Loop/switch isn't completed */
_L10:
            initialScore = codedinputbytebuffernano.readInt64();
            continue; /* Loop/switch isn't completed */
_L11:
            finalScore = codedinputbytebuffernano.readInt64();
            continue; /* Loop/switch isn't completed */
_L12:
            int i1 = codedinputbytebuffernano.readInt32();
            switch(i1)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                ipSupport = i1;
                break;
            }
            if(true) goto _L14; else goto _L15
_L15:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(networkId != null)
                codedoutputbytebuffernano.writeMessage(1, networkId);
            if(previousNetworkId != null)
                codedoutputbytebuffernano.writeMessage(2, previousNetworkId);
            if(previousNetworkIpSupport != 0)
                codedoutputbytebuffernano.writeInt32(3, previousNetworkIpSupport);
            if(transportTypes != null && transportTypes.length > 0)
            {
                for(int i = 0; i < transportTypes.length; i++)
                    codedoutputbytebuffernano.writeInt32(4, transportTypes[i]);

            }
            if(defaultNetworkDurationMs != 0L)
                codedoutputbytebuffernano.writeInt64(5, defaultNetworkDurationMs);
            if(noDefaultNetworkDurationMs != 0L)
                codedoutputbytebuffernano.writeInt64(6, noDefaultNetworkDurationMs);
            if(initialScore != 0L)
                codedoutputbytebuffernano.writeInt64(7, initialScore);
            if(finalScore != 0L)
                codedoutputbytebuffernano.writeInt64(8, finalScore);
            if(ipSupport != 0)
                codedoutputbytebuffernano.writeInt32(9, ipSupport);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int DISCONNECT = 3;
        public static final int DUAL = 3;
        public static final int INVALIDATION = 2;
        public static final int IPV4 = 1;
        public static final int IPV6 = 2;
        public static final int NONE = 0;
        public static final int OUTSCORED = 1;
        public static final int UNKNOWN = 0;
        private static volatile DefaultNetworkEvent _emptyArray[];
        public long defaultNetworkDurationMs;
        public long finalScore;
        public long initialScore;
        public int ipSupport;
        public NetworkId networkId;
        public long noDefaultNetworkDurationMs;
        public NetworkId previousNetworkId;
        public int previousNetworkIpSupport;
        public int transportTypes[];

        public DefaultNetworkEvent()
        {
            clear();
        }
    }

    public static final class IpConnectivityEvent extends MessageNano
    {

        public static IpConnectivityEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new IpConnectivityEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static IpConnectivityEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new IpConnectivityEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static IpConnectivityEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (IpConnectivityEvent)MessageNano.mergeFrom(new IpConnectivityEvent(), abyte0);
        }

        public IpConnectivityEvent clear()
        {
            timeMs = 0L;
            linkLayer = 0;
            networkId = 0;
            ifName = "";
            transports = 0L;
            clearEvent();
            cachedSize = -1;
            return this;
        }

        public IpConnectivityEvent clearEvent()
        {
            eventCase_ = 0;
            event_ = null;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(timeMs != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(1, timeMs);
            i = j;
            if(eventCase_ == 2)
                i = j + CodedOutputByteBufferNano.computeMessageSize(2, (MessageNano)event_);
            j = i;
            if(eventCase_ == 3)
                j = i + CodedOutputByteBufferNano.computeMessageSize(3, (MessageNano)event_);
            i = j;
            if(eventCase_ == 4)
                i = j + CodedOutputByteBufferNano.computeMessageSize(4, (MessageNano)event_);
            int k = i;
            if(eventCase_ == 5)
                k = i + CodedOutputByteBufferNano.computeMessageSize(5, (MessageNano)event_);
            j = k;
            if(eventCase_ == 6)
                j = k + CodedOutputByteBufferNano.computeMessageSize(6, (MessageNano)event_);
            i = j;
            if(eventCase_ == 7)
                i = j + CodedOutputByteBufferNano.computeMessageSize(7, (MessageNano)event_);
            j = i;
            if(eventCase_ == 8)
                j = i + CodedOutputByteBufferNano.computeMessageSize(8, (MessageNano)event_);
            i = j;
            if(eventCase_ == 9)
                i = j + CodedOutputByteBufferNano.computeMessageSize(9, (MessageNano)event_);
            j = i;
            if(eventCase_ == 10)
                j = i + CodedOutputByteBufferNano.computeMessageSize(10, (MessageNano)event_);
            k = j;
            if(eventCase_ == 11)
                k = j + CodedOutputByteBufferNano.computeMessageSize(11, (MessageNano)event_);
            i = k;
            if(eventCase_ == 13)
                i = k + CodedOutputByteBufferNano.computeMessageSize(13, (MessageNano)event_);
            j = i;
            if(eventCase_ == 14)
                j = i + CodedOutputByteBufferNano.computeMessageSize(14, (MessageNano)event_);
            i = j;
            if(linkLayer != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(15, linkLayer);
            j = i;
            if(networkId != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(16, networkId);
            i = j;
            if(!ifName.equals(""))
                i = j + CodedOutputByteBufferNano.computeStringSize(17, ifName);
            j = i;
            if(transports != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(18, transports);
            i = j;
            if(eventCase_ == 19)
                i = j + CodedOutputByteBufferNano.computeMessageSize(19, (MessageNano)event_);
            j = i;
            if(eventCase_ == 20)
                j = i + CodedOutputByteBufferNano.computeMessageSize(20, (MessageNano)event_);
            return j;
        }

        public ApfProgramEvent getApfProgramEvent()
        {
            if(eventCase_ == 9)
                return (ApfProgramEvent)event_;
            else
                return null;
        }

        public ApfStatistics getApfStatistics()
        {
            if(eventCase_ == 10)
                return (ApfStatistics)event_;
            else
                return null;
        }

        public ConnectStatistics getConnectStatistics()
        {
            if(eventCase_ == 14)
                return (ConnectStatistics)event_;
            else
                return null;
        }

        public DefaultNetworkEvent getDefaultNetworkEvent()
        {
            if(eventCase_ == 2)
                return (DefaultNetworkEvent)event_;
            else
                return null;
        }

        public DHCPEvent getDhcpEvent()
        {
            if(eventCase_ == 6)
                return (DHCPEvent)event_;
            else
                return null;
        }

        public DNSLatencies getDnsLatencies()
        {
            if(eventCase_ == 13)
                return (DNSLatencies)event_;
            else
                return null;
        }

        public DNSLookupBatch getDnsLookupBatch()
        {
            if(eventCase_ == 5)
                return (DNSLookupBatch)event_;
            else
                return null;
        }

        public int getEventCase()
        {
            return eventCase_;
        }

        public IpProvisioningEvent getIpProvisioningEvent()
        {
            if(eventCase_ == 7)
                return (IpProvisioningEvent)event_;
            else
                return null;
        }

        public IpReachabilityEvent getIpReachabilityEvent()
        {
            if(eventCase_ == 3)
                return (IpReachabilityEvent)event_;
            else
                return null;
        }

        public NetworkEvent getNetworkEvent()
        {
            if(eventCase_ == 4)
                return (NetworkEvent)event_;
            else
                return null;
        }

        public NetworkStats getNetworkStats()
        {
            if(eventCase_ == 19)
                return (NetworkStats)event_;
            else
                return null;
        }

        public RaEvent getRaEvent()
        {
            if(eventCase_ == 11)
                return (RaEvent)event_;
            else
                return null;
        }

        public ValidationProbeEvent getValidationProbeEvent()
        {
            if(eventCase_ == 8)
                return (ValidationProbeEvent)event_;
            else
                return null;
        }

        public WakeupStats getWakeupStats()
        {
            if(eventCase_ == 20)
                return (WakeupStats)event_;
            else
                return null;
        }

        public boolean hasApfProgramEvent()
        {
            boolean flag;
            if(eventCase_ == 9)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasApfStatistics()
        {
            boolean flag;
            if(eventCase_ == 10)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasConnectStatistics()
        {
            boolean flag;
            if(eventCase_ == 14)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasDefaultNetworkEvent()
        {
            boolean flag;
            if(eventCase_ == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasDhcpEvent()
        {
            boolean flag;
            if(eventCase_ == 6)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasDnsLatencies()
        {
            boolean flag;
            if(eventCase_ == 13)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasDnsLookupBatch()
        {
            boolean flag;
            if(eventCase_ == 5)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasIpProvisioningEvent()
        {
            boolean flag;
            if(eventCase_ == 7)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasIpReachabilityEvent()
        {
            boolean flag;
            if(eventCase_ == 3)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasNetworkEvent()
        {
            boolean flag;
            if(eventCase_ == 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasNetworkStats()
        {
            boolean flag;
            if(eventCase_ == 19)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasRaEvent()
        {
            boolean flag;
            if(eventCase_ == 11)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasValidationProbeEvent()
        {
            boolean flag;
            if(eventCase_ == 8)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasWakeupStats()
        {
            boolean flag;
            if(eventCase_ == 20)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public IpConnectivityEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L23:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 20: default 176
        //                       0: 186
        //                       8: 188
        //                       18: 199
        //                       26: 237
        //                       34: 275
        //                       42: 313
        //                       50: 351
        //                       58: 391
        //                       66: 431
        //                       74: 471
        //                       82: 511
        //                       90: 551
        //                       106: 591
        //                       114: 631
        //                       120: 671
        //                       128: 743
        //                       138: 754
        //                       144: 765
        //                       154: 776
        //                       162: 816;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L23; else goto _L22
_L22:
            return this;
_L2:
            return this;
_L3:
            timeMs = codedinputbytebuffernano.readInt64();
              goto _L23
_L4:
            if(eventCase_ != 2)
                event_ = new DefaultNetworkEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 2;
              goto _L23
_L5:
            if(eventCase_ != 3)
                event_ = new IpReachabilityEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 3;
              goto _L23
_L6:
            if(eventCase_ != 4)
                event_ = new NetworkEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 4;
              goto _L23
_L7:
            if(eventCase_ != 5)
                event_ = new DNSLookupBatch();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 5;
              goto _L23
_L8:
            if(eventCase_ != 6)
                event_ = new DHCPEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 6;
              goto _L23
_L9:
            if(eventCase_ != 7)
                event_ = new IpProvisioningEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 7;
              goto _L23
_L10:
            if(eventCase_ != 8)
                event_ = new ValidationProbeEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 8;
              goto _L23
_L11:
            if(eventCase_ != 9)
                event_ = new ApfProgramEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 9;
              goto _L23
_L12:
            if(eventCase_ != 10)
                event_ = new ApfStatistics();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 10;
              goto _L23
_L13:
            if(eventCase_ != 11)
                event_ = new RaEvent();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 11;
              goto _L23
_L14:
            if(eventCase_ != 13)
                event_ = new DNSLatencies();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 13;
              goto _L23
_L15:
            if(eventCase_ != 14)
                event_ = new ConnectStatistics();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 14;
              goto _L23
_L16:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
                linkLayer = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L17:
            networkId = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L18:
            ifName = codedinputbytebuffernano.readString();
            continue; /* Loop/switch isn't completed */
_L19:
            transports = codedinputbytebuffernano.readInt64();
            continue; /* Loop/switch isn't completed */
_L20:
            if(eventCase_ != 19)
                event_ = new NetworkStats();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 19;
            continue; /* Loop/switch isn't completed */
_L21:
            if(eventCase_ != 20)
                event_ = new WakeupStats();
            codedinputbytebuffernano.readMessage((MessageNano)event_);
            eventCase_ = 20;
            if(true) goto _L23; else goto _L24
_L24:
        }

        public IpConnectivityEvent setApfProgramEvent(ApfProgramEvent apfprogramevent)
        {
            if(apfprogramevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 9;
                event_ = apfprogramevent;
                return this;
            }
        }

        public IpConnectivityEvent setApfStatistics(ApfStatistics apfstatistics)
        {
            if(apfstatistics == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 10;
                event_ = apfstatistics;
                return this;
            }
        }

        public IpConnectivityEvent setConnectStatistics(ConnectStatistics connectstatistics)
        {
            if(connectstatistics == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 14;
                event_ = connectstatistics;
                return this;
            }
        }

        public IpConnectivityEvent setDefaultNetworkEvent(DefaultNetworkEvent defaultnetworkevent)
        {
            if(defaultnetworkevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 2;
                event_ = defaultnetworkevent;
                return this;
            }
        }

        public IpConnectivityEvent setDhcpEvent(DHCPEvent dhcpevent)
        {
            if(dhcpevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 6;
                event_ = dhcpevent;
                return this;
            }
        }

        public IpConnectivityEvent setDnsLatencies(DNSLatencies dnslatencies)
        {
            if(dnslatencies == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 13;
                event_ = dnslatencies;
                return this;
            }
        }

        public IpConnectivityEvent setDnsLookupBatch(DNSLookupBatch dnslookupbatch)
        {
            if(dnslookupbatch == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 5;
                event_ = dnslookupbatch;
                return this;
            }
        }

        public IpConnectivityEvent setIpProvisioningEvent(IpProvisioningEvent ipprovisioningevent)
        {
            if(ipprovisioningevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 7;
                event_ = ipprovisioningevent;
                return this;
            }
        }

        public IpConnectivityEvent setIpReachabilityEvent(IpReachabilityEvent ipreachabilityevent)
        {
            if(ipreachabilityevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 3;
                event_ = ipreachabilityevent;
                return this;
            }
        }

        public IpConnectivityEvent setNetworkEvent(NetworkEvent networkevent)
        {
            if(networkevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 4;
                event_ = networkevent;
                return this;
            }
        }

        public IpConnectivityEvent setNetworkStats(NetworkStats networkstats)
        {
            if(networkstats == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 19;
                event_ = networkstats;
                return this;
            }
        }

        public IpConnectivityEvent setRaEvent(RaEvent raevent)
        {
            if(raevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 11;
                event_ = raevent;
                return this;
            }
        }

        public IpConnectivityEvent setValidationProbeEvent(ValidationProbeEvent validationprobeevent)
        {
            if(validationprobeevent == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 8;
                event_ = validationprobeevent;
                return this;
            }
        }

        public IpConnectivityEvent setWakeupStats(WakeupStats wakeupstats)
        {
            if(wakeupstats == null)
            {
                throw new NullPointerException();
            } else
            {
                eventCase_ = 20;
                event_ = wakeupstats;
                return this;
            }
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(timeMs != 0L)
                codedoutputbytebuffernano.writeInt64(1, timeMs);
            if(eventCase_ == 2)
                codedoutputbytebuffernano.writeMessage(2, (MessageNano)event_);
            if(eventCase_ == 3)
                codedoutputbytebuffernano.writeMessage(3, (MessageNano)event_);
            if(eventCase_ == 4)
                codedoutputbytebuffernano.writeMessage(4, (MessageNano)event_);
            if(eventCase_ == 5)
                codedoutputbytebuffernano.writeMessage(5, (MessageNano)event_);
            if(eventCase_ == 6)
                codedoutputbytebuffernano.writeMessage(6, (MessageNano)event_);
            if(eventCase_ == 7)
                codedoutputbytebuffernano.writeMessage(7, (MessageNano)event_);
            if(eventCase_ == 8)
                codedoutputbytebuffernano.writeMessage(8, (MessageNano)event_);
            if(eventCase_ == 9)
                codedoutputbytebuffernano.writeMessage(9, (MessageNano)event_);
            if(eventCase_ == 10)
                codedoutputbytebuffernano.writeMessage(10, (MessageNano)event_);
            if(eventCase_ == 11)
                codedoutputbytebuffernano.writeMessage(11, (MessageNano)event_);
            if(eventCase_ == 13)
                codedoutputbytebuffernano.writeMessage(13, (MessageNano)event_);
            if(eventCase_ == 14)
                codedoutputbytebuffernano.writeMessage(14, (MessageNano)event_);
            if(linkLayer != 0)
                codedoutputbytebuffernano.writeInt32(15, linkLayer);
            if(networkId != 0)
                codedoutputbytebuffernano.writeInt32(16, networkId);
            if(!ifName.equals(""))
                codedoutputbytebuffernano.writeString(17, ifName);
            if(transports != 0L)
                codedoutputbytebuffernano.writeInt64(18, transports);
            if(eventCase_ == 19)
                codedoutputbytebuffernano.writeMessage(19, (MessageNano)event_);
            if(eventCase_ == 20)
                codedoutputbytebuffernano.writeMessage(20, (MessageNano)event_);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int APF_PROGRAM_EVENT_FIELD_NUMBER = 9;
        public static final int APF_STATISTICS_FIELD_NUMBER = 10;
        public static final int CONNECT_STATISTICS_FIELD_NUMBER = 14;
        public static final int DEFAULT_NETWORK_EVENT_FIELD_NUMBER = 2;
        public static final int DHCP_EVENT_FIELD_NUMBER = 6;
        public static final int DNS_LATENCIES_FIELD_NUMBER = 13;
        public static final int DNS_LOOKUP_BATCH_FIELD_NUMBER = 5;
        public static final int IP_PROVISIONING_EVENT_FIELD_NUMBER = 7;
        public static final int IP_REACHABILITY_EVENT_FIELD_NUMBER = 3;
        public static final int NETWORK_EVENT_FIELD_NUMBER = 4;
        public static final int NETWORK_STATS_FIELD_NUMBER = 19;
        public static final int RA_EVENT_FIELD_NUMBER = 11;
        public static final int VALIDATION_PROBE_EVENT_FIELD_NUMBER = 8;
        public static final int WAKEUP_STATS_FIELD_NUMBER = 20;
        private static volatile IpConnectivityEvent _emptyArray[];
        private int eventCase_;
        private Object event_;
        public String ifName;
        public int linkLayer;
        public int networkId;
        public long timeMs;
        public long transports;

        public IpConnectivityEvent()
        {
            eventCase_ = 0;
            clear();
        }
    }

    public static final class IpConnectivityLog extends MessageNano
    {

        public static IpConnectivityLog[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new IpConnectivityLog[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static IpConnectivityLog parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new IpConnectivityLog()).mergeFrom(codedinputbytebuffernano);
        }

        public static IpConnectivityLog parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (IpConnectivityLog)MessageNano.mergeFrom(new IpConnectivityLog(), abyte0);
        }

        public IpConnectivityLog clear()
        {
            events = IpConnectivityEvent.emptyArray();
            droppedEvents = 0;
            version = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(events != null)
            {
                j = i;
                if(events.length > 0)
                {
                    int k = 0;
                    do
                    {
                        j = i;
                        if(k >= events.length)
                            break;
                        IpConnectivityEvent ipconnectivityevent = events[k];
                        j = i;
                        if(ipconnectivityevent != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(1, ipconnectivityevent);
                        k++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(droppedEvents != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, droppedEvents);
            j = i;
            if(version != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, version);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public IpConnectivityLog mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    int k = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                    int j;
                    IpConnectivityEvent aipconnectivityevent[];
                    if(events == null)
                        j = 0;
                    else
                        j = events.length;
                    aipconnectivityevent = new IpConnectivityEvent[j + k];
                    k = j;
                    if(j != 0)
                    {
                        System.arraycopy(events, 0, aipconnectivityevent, 0, j);
                        k = j;
                    }
                    for(; k < aipconnectivityevent.length - 1; k++)
                    {
                        aipconnectivityevent[k] = new IpConnectivityEvent();
                        codedinputbytebuffernano.readMessage(aipconnectivityevent[k]);
                        codedinputbytebuffernano.readTag();
                    }

                    aipconnectivityevent[k] = new IpConnectivityEvent();
                    codedinputbytebuffernano.readMessage(aipconnectivityevent[k]);
                    events = aipconnectivityevent;
                    break;

                case 16: // '\020'
                    droppedEvents = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    version = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(events != null && events.length > 0)
            {
                for(int i = 0; i < events.length; i++)
                {
                    IpConnectivityEvent ipconnectivityevent = events[i];
                    if(ipconnectivityevent != null)
                        codedoutputbytebuffernano.writeMessage(1, ipconnectivityevent);
                }

            }
            if(droppedEvents != 0)
                codedoutputbytebuffernano.writeInt32(2, droppedEvents);
            if(version != 0)
                codedoutputbytebuffernano.writeInt32(3, version);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile IpConnectivityLog _emptyArray[];
        public int droppedEvents;
        public IpConnectivityEvent events[];
        public int version;

        public IpConnectivityLog()
        {
            clear();
        }
    }

    public static final class IpProvisioningEvent extends MessageNano
    {

        public static IpProvisioningEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new IpProvisioningEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static IpProvisioningEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new IpProvisioningEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static IpProvisioningEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (IpProvisioningEvent)MessageNano.mergeFrom(new IpProvisioningEvent(), abyte0);
        }

        public IpProvisioningEvent clear()
        {
            ifName = "";
            eventType = 0;
            latencyMs = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(!ifName.equals(""))
                j = i + CodedOutputByteBufferNano.computeStringSize(1, ifName);
            i = j;
            if(eventType != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, eventType);
            j = i;
            if(latencyMs != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, latencyMs);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public IpProvisioningEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    ifName = codedinputbytebuffernano.readString();
                    break;

                case 16: // '\020'
                    eventType = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    latencyMs = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(!ifName.equals(""))
                codedoutputbytebuffernano.writeString(1, ifName);
            if(eventType != 0)
                codedoutputbytebuffernano.writeInt32(2, eventType);
            if(latencyMs != 0)
                codedoutputbytebuffernano.writeInt32(3, latencyMs);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile IpProvisioningEvent _emptyArray[];
        public int eventType;
        public String ifName;
        public int latencyMs;

        public IpProvisioningEvent()
        {
            clear();
        }
    }

    public static final class IpReachabilityEvent extends MessageNano
    {

        public static IpReachabilityEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new IpReachabilityEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static IpReachabilityEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new IpReachabilityEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static IpReachabilityEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (IpReachabilityEvent)MessageNano.mergeFrom(new IpReachabilityEvent(), abyte0);
        }

        public IpReachabilityEvent clear()
        {
            ifName = "";
            eventType = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(!ifName.equals(""))
                j = i + CodedOutputByteBufferNano.computeStringSize(1, ifName);
            i = j;
            if(eventType != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, eventType);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public IpReachabilityEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    ifName = codedinputbytebuffernano.readString();
                    break;

                case 16: // '\020'
                    eventType = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(!ifName.equals(""))
                codedoutputbytebuffernano.writeString(1, ifName);
            if(eventType != 0)
                codedoutputbytebuffernano.writeInt32(2, eventType);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile IpReachabilityEvent _emptyArray[];
        public int eventType;
        public String ifName;

        public IpReachabilityEvent()
        {
            clear();
        }
    }

    public static final class NetworkEvent extends MessageNano
    {

        public static NetworkEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new NetworkEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static NetworkEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new NetworkEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static NetworkEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (NetworkEvent)MessageNano.mergeFrom(new NetworkEvent(), abyte0);
        }

        public NetworkEvent clear()
        {
            networkId = null;
            eventType = 0;
            latencyMs = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(networkId != null)
                j = i + CodedOutputByteBufferNano.computeMessageSize(1, networkId);
            i = j;
            if(eventType != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, eventType);
            j = i;
            if(latencyMs != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, latencyMs);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public NetworkEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    if(networkId == null)
                        networkId = new NetworkId();
                    codedinputbytebuffernano.readMessage(networkId);
                    break;

                case 16: // '\020'
                    eventType = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    latencyMs = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(networkId != null)
                codedoutputbytebuffernano.writeMessage(1, networkId);
            if(eventType != 0)
                codedoutputbytebuffernano.writeInt32(2, eventType);
            if(latencyMs != 0)
                codedoutputbytebuffernano.writeInt32(3, latencyMs);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile NetworkEvent _emptyArray[];
        public int eventType;
        public int latencyMs;
        public NetworkId networkId;

        public NetworkEvent()
        {
            clear();
        }
    }

    public static final class NetworkId extends MessageNano
    {

        public static NetworkId[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new NetworkId[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static NetworkId parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new NetworkId()).mergeFrom(codedinputbytebuffernano);
        }

        public static NetworkId parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (NetworkId)MessageNano.mergeFrom(new NetworkId(), abyte0);
        }

        public NetworkId clear()
        {
            networkId = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(networkId != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, networkId);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public NetworkId mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    networkId = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(networkId != 0)
                codedoutputbytebuffernano.writeInt32(1, networkId);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile NetworkId _emptyArray[];
        public int networkId;

        public NetworkId()
        {
            clear();
        }
    }

    public static final class NetworkStats extends MessageNano
    {

        public static NetworkStats[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new NetworkStats[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static NetworkStats parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new NetworkStats()).mergeFrom(codedinputbytebuffernano);
        }

        public static NetworkStats parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (NetworkStats)MessageNano.mergeFrom(new NetworkStats(), abyte0);
        }

        public NetworkStats clear()
        {
            durationMs = 0L;
            ipSupport = 0;
            everValidated = false;
            portalFound = false;
            noConnectivityReports = 0;
            validationAttempts = 0;
            validationEvents = Pair.emptyArray();
            validationStates = Pair.emptyArray();
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int k = i;
            if(durationMs != 0L)
                k = i + CodedOutputByteBufferNano.computeInt64Size(1, durationMs);
            i = k;
            if(ipSupport != 0)
                i = k + CodedOutputByteBufferNano.computeInt32Size(2, ipSupport);
            k = i;
            if(everValidated)
                k = i + CodedOutputByteBufferNano.computeBoolSize(3, everValidated);
            i = k;
            if(portalFound)
                i = k + CodedOutputByteBufferNano.computeBoolSize(4, portalFound);
            k = i;
            if(noConnectivityReports != 0)
                k = i + CodedOutputByteBufferNano.computeInt32Size(5, noConnectivityReports);
            i = k;
            if(validationAttempts != 0)
                i = k + CodedOutputByteBufferNano.computeInt32Size(6, validationAttempts);
            k = i;
            if(validationEvents != null)
            {
                k = i;
                if(validationEvents.length > 0)
                {
                    int l = 0;
                    do
                    {
                        k = i;
                        if(l >= validationEvents.length)
                            break;
                        Pair pair = validationEvents[l];
                        k = i;
                        if(pair != null)
                            k = i + CodedOutputByteBufferNano.computeMessageSize(7, pair);
                        l++;
                        i = k;
                    } while(true);
                }
            }
            int i1 = k;
            if(validationStates != null)
            {
                i1 = k;
                if(validationStates.length > 0)
                {
                    int j = 0;
                    do
                    {
                        i1 = k;
                        if(j >= validationStates.length)
                            break;
                        Pair pair1 = validationStates[j];
                        i1 = k;
                        if(pair1 != null)
                            i1 = k + CodedOutputByteBufferNano.computeMessageSize(8, pair1);
                        j++;
                        k = i1;
                    } while(true);
                }
            }
            return i1;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public NetworkStats mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L12:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 9: default 88
        //                       0: 98
        //                       8: 100
        //                       16: 111
        //                       24: 159
        //                       32: 170
        //                       40: 181
        //                       48: 192
        //                       58: 203
        //                       66: 323;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L12; else goto _L11
_L11:
            return this;
_L2:
            return this;
_L3:
            durationMs = codedinputbytebuffernano.readInt64();
              goto _L12
_L4:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                ipSupport = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            everValidated = codedinputbytebuffernano.readBool();
            continue; /* Loop/switch isn't completed */
_L6:
            portalFound = codedinputbytebuffernano.readBool();
            continue; /* Loop/switch isn't completed */
_L7:
            noConnectivityReports = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L8:
            validationAttempts = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L9:
            int i1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 58);
            int k;
            Pair apair[];
            if(validationEvents == null)
                k = 0;
            else
                k = validationEvents.length;
            apair = new Pair[k + i1];
            i1 = k;
            if(k != 0)
            {
                System.arraycopy(validationEvents, 0, apair, 0, k);
                i1 = k;
            }
            for(; i1 < apair.length - 1; i1++)
            {
                apair[i1] = new Pair();
                codedinputbytebuffernano.readMessage(apair[i1]);
                codedinputbytebuffernano.readTag();
            }

            apair[i1] = new Pair();
            codedinputbytebuffernano.readMessage(apair[i1]);
            validationEvents = apair;
            continue; /* Loop/switch isn't completed */
_L10:
            int j1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 66);
            int l;
            Pair apair1[];
            if(validationStates == null)
                l = 0;
            else
                l = validationStates.length;
            apair1 = new Pair[l + j1];
            j1 = l;
            if(l != 0)
            {
                System.arraycopy(validationStates, 0, apair1, 0, l);
                j1 = l;
            }
            for(; j1 < apair1.length - 1; j1++)
            {
                apair1[j1] = new Pair();
                codedinputbytebuffernano.readMessage(apair1[j1]);
                codedinputbytebuffernano.readTag();
            }

            apair1[j1] = new Pair();
            codedinputbytebuffernano.readMessage(apair1[j1]);
            validationStates = apair1;
            if(true) goto _L12; else goto _L13
_L13:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(durationMs != 0L)
                codedoutputbytebuffernano.writeInt64(1, durationMs);
            if(ipSupport != 0)
                codedoutputbytebuffernano.writeInt32(2, ipSupport);
            if(everValidated)
                codedoutputbytebuffernano.writeBool(3, everValidated);
            if(portalFound)
                codedoutputbytebuffernano.writeBool(4, portalFound);
            if(noConnectivityReports != 0)
                codedoutputbytebuffernano.writeInt32(5, noConnectivityReports);
            if(validationAttempts != 0)
                codedoutputbytebuffernano.writeInt32(6, validationAttempts);
            if(validationEvents != null && validationEvents.length > 0)
            {
                for(int i = 0; i < validationEvents.length; i++)
                {
                    Pair pair = validationEvents[i];
                    if(pair != null)
                        codedoutputbytebuffernano.writeMessage(7, pair);
                }

            }
            if(validationStates != null && validationStates.length > 0)
            {
                for(int j = 0; j < validationStates.length; j++)
                {
                    Pair pair1 = validationStates[j];
                    if(pair1 != null)
                        codedoutputbytebuffernano.writeMessage(8, pair1);
                }

            }
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile NetworkStats _emptyArray[];
        public long durationMs;
        public boolean everValidated;
        public int ipSupport;
        public int noConnectivityReports;
        public boolean portalFound;
        public int validationAttempts;
        public Pair validationEvents[];
        public Pair validationStates[];

        public NetworkStats()
        {
            clear();
        }
    }

    public static final class Pair extends MessageNano
    {

        public static Pair[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new Pair[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static Pair parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new Pair()).mergeFrom(codedinputbytebuffernano);
        }

        public static Pair parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (Pair)MessageNano.mergeFrom(new Pair(), abyte0);
        }

        public Pair clear()
        {
            key = 0;
            value = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(key != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, key);
            i = j;
            if(value != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, value);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public Pair mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    key = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    value = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(key != 0)
                codedoutputbytebuffernano.writeInt32(1, key);
            if(value != 0)
                codedoutputbytebuffernano.writeInt32(2, value);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile Pair _emptyArray[];
        public int key;
        public int value;

        public Pair()
        {
            clear();
        }
    }

    public static final class RaEvent extends MessageNano
    {

        public static RaEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new RaEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static RaEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new RaEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static RaEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (RaEvent)MessageNano.mergeFrom(new RaEvent(), abyte0);
        }

        public RaEvent clear()
        {
            routerLifetime = 0L;
            prefixValidLifetime = 0L;
            prefixPreferredLifetime = 0L;
            routeInfoLifetime = 0L;
            rdnssLifetime = 0L;
            dnsslLifetime = 0L;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(routerLifetime != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(1, routerLifetime);
            i = j;
            if(prefixValidLifetime != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(2, prefixValidLifetime);
            j = i;
            if(prefixPreferredLifetime != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(3, prefixPreferredLifetime);
            int k = j;
            if(routeInfoLifetime != 0L)
                k = j + CodedOutputByteBufferNano.computeInt64Size(4, routeInfoLifetime);
            i = k;
            if(rdnssLifetime != 0L)
                i = k + CodedOutputByteBufferNano.computeInt64Size(5, rdnssLifetime);
            j = i;
            if(dnsslLifetime != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(6, dnsslLifetime);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public RaEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    routerLifetime = codedinputbytebuffernano.readInt64();
                    break;

                case 16: // '\020'
                    prefixValidLifetime = codedinputbytebuffernano.readInt64();
                    break;

                case 24: // '\030'
                    prefixPreferredLifetime = codedinputbytebuffernano.readInt64();
                    break;

                case 32: // ' '
                    routeInfoLifetime = codedinputbytebuffernano.readInt64();
                    break;

                case 40: // '('
                    rdnssLifetime = codedinputbytebuffernano.readInt64();
                    break;

                case 48: // '0'
                    dnsslLifetime = codedinputbytebuffernano.readInt64();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(routerLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(1, routerLifetime);
            if(prefixValidLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(2, prefixValidLifetime);
            if(prefixPreferredLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(3, prefixPreferredLifetime);
            if(routeInfoLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(4, routeInfoLifetime);
            if(rdnssLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(5, rdnssLifetime);
            if(dnsslLifetime != 0L)
                codedoutputbytebuffernano.writeInt64(6, dnsslLifetime);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile RaEvent _emptyArray[];
        public long dnsslLifetime;
        public long prefixPreferredLifetime;
        public long prefixValidLifetime;
        public long rdnssLifetime;
        public long routeInfoLifetime;
        public long routerLifetime;

        public RaEvent()
        {
            clear();
        }
    }

    public static final class ValidationProbeEvent extends MessageNano
    {

        public static ValidationProbeEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new ValidationProbeEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static ValidationProbeEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new ValidationProbeEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static ValidationProbeEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (ValidationProbeEvent)MessageNano.mergeFrom(new ValidationProbeEvent(), abyte0);
        }

        public ValidationProbeEvent clear()
        {
            networkId = null;
            latencyMs = 0;
            probeType = 0;
            probeResult = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(networkId != null)
                j = i + CodedOutputByteBufferNano.computeMessageSize(1, networkId);
            i = j;
            if(latencyMs != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, latencyMs);
            j = i;
            if(probeType != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, probeType);
            i = j;
            if(probeResult != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, probeResult);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public ValidationProbeEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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

                case 10: // '\n'
                    if(networkId == null)
                        networkId = new NetworkId();
                    codedinputbytebuffernano.readMessage(networkId);
                    break;

                case 16: // '\020'
                    latencyMs = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    probeType = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    probeResult = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(networkId != null)
                codedoutputbytebuffernano.writeMessage(1, networkId);
            if(latencyMs != 0)
                codedoutputbytebuffernano.writeInt32(2, latencyMs);
            if(probeType != 0)
                codedoutputbytebuffernano.writeInt32(3, probeType);
            if(probeResult != 0)
                codedoutputbytebuffernano.writeInt32(4, probeResult);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile ValidationProbeEvent _emptyArray[];
        public int latencyMs;
        public NetworkId networkId;
        public int probeResult;
        public int probeType;

        public ValidationProbeEvent()
        {
            clear();
        }
    }

    public static final class WakeupStats extends MessageNano
    {

        public static WakeupStats[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WakeupStats[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WakeupStats parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WakeupStats()).mergeFrom(codedinputbytebuffernano);
        }

        public static WakeupStats parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WakeupStats)MessageNano.mergeFrom(new WakeupStats(), abyte0);
        }

        public WakeupStats clear()
        {
            durationSec = 0L;
            totalWakeups = 0L;
            rootWakeups = 0L;
            systemWakeups = 0L;
            applicationWakeups = 0L;
            nonApplicationWakeups = 0L;
            noUidWakeups = 0L;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(durationSec != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(1, durationSec);
            i = j;
            if(totalWakeups != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(2, totalWakeups);
            int k = i;
            if(rootWakeups != 0L)
                k = i + CodedOutputByteBufferNano.computeInt64Size(3, rootWakeups);
            j = k;
            if(systemWakeups != 0L)
                j = k + CodedOutputByteBufferNano.computeInt64Size(4, systemWakeups);
            i = j;
            if(applicationWakeups != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(5, applicationWakeups);
            j = i;
            if(nonApplicationWakeups != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(6, nonApplicationWakeups);
            i = j;
            if(noUidWakeups != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(7, noUidWakeups);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WakeupStats mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    durationSec = codedinputbytebuffernano.readInt64();
                    break;

                case 16: // '\020'
                    totalWakeups = codedinputbytebuffernano.readInt64();
                    break;

                case 24: // '\030'
                    rootWakeups = codedinputbytebuffernano.readInt64();
                    break;

                case 32: // ' '
                    systemWakeups = codedinputbytebuffernano.readInt64();
                    break;

                case 40: // '('
                    applicationWakeups = codedinputbytebuffernano.readInt64();
                    break;

                case 48: // '0'
                    nonApplicationWakeups = codedinputbytebuffernano.readInt64();
                    break;

                case 56: // '8'
                    noUidWakeups = codedinputbytebuffernano.readInt64();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(durationSec != 0L)
                codedoutputbytebuffernano.writeInt64(1, durationSec);
            if(totalWakeups != 0L)
                codedoutputbytebuffernano.writeInt64(2, totalWakeups);
            if(rootWakeups != 0L)
                codedoutputbytebuffernano.writeInt64(3, rootWakeups);
            if(systemWakeups != 0L)
                codedoutputbytebuffernano.writeInt64(4, systemWakeups);
            if(applicationWakeups != 0L)
                codedoutputbytebuffernano.writeInt64(5, applicationWakeups);
            if(nonApplicationWakeups != 0L)
                codedoutputbytebuffernano.writeInt64(6, nonApplicationWakeups);
            if(noUidWakeups != 0L)
                codedoutputbytebuffernano.writeInt64(7, noUidWakeups);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile WakeupStats _emptyArray[];
        public long applicationWakeups;
        public long durationSec;
        public long noUidWakeups;
        public long nonApplicationWakeups;
        public long rootWakeups;
        public long systemWakeups;
        public long totalWakeups;

        public WakeupStats()
        {
            clear();
        }
    }


    public static final int BLUETOOTH = 1;
    public static final int CELLULAR = 2;
    public static final int ETHERNET = 3;
    public static final int LOWPAN = 9;
    public static final int MULTIPLE = 6;
    public static final int NONE = 5;
    public static final int UNKNOWN = 0;
    public static final int WIFI = 4;
    public static final int WIFI_NAN = 8;
    public static final int WIFI_P2P = 7;
}
