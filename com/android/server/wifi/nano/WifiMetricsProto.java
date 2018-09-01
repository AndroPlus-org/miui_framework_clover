// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.wifi.nano;

import com.android.framework.protobuf.nano.*;
import java.io.IOException;

public interface WifiMetricsProto
{
    public static final class AlertReasonCount extends MessageNano
    {

        public static AlertReasonCount[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new AlertReasonCount[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static AlertReasonCount parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new AlertReasonCount()).mergeFrom(codedinputbytebuffernano);
        }

        public static AlertReasonCount parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (AlertReasonCount)MessageNano.mergeFrom(new AlertReasonCount(), abyte0);
        }

        public AlertReasonCount clear()
        {
            reason = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(reason != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, reason);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, count);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public AlertReasonCount mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    reason = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    count = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(reason != 0)
                codedoutputbytebuffernano.writeInt32(1, reason);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(2, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile AlertReasonCount _emptyArray[];
        public int count;
        public int reason;

        public AlertReasonCount()
        {
            clear();
        }
    }

    public static final class ConnectToNetworkNotificationAndActionCount extends MessageNano
    {

        public static ConnectToNetworkNotificationAndActionCount[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new ConnectToNetworkNotificationAndActionCount[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static ConnectToNetworkNotificationAndActionCount parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new ConnectToNetworkNotificationAndActionCount()).mergeFrom(codedinputbytebuffernano);
        }

        public static ConnectToNetworkNotificationAndActionCount parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (ConnectToNetworkNotificationAndActionCount)MessageNano.mergeFrom(new ConnectToNetworkNotificationAndActionCount(), abyte0);
        }

        public ConnectToNetworkNotificationAndActionCount clear()
        {
            notification = 0;
            action = 0;
            recommender = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(notification != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, notification);
            i = j;
            if(action != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, action);
            j = i;
            if(recommender != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, recommender);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, count);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public ConnectToNetworkNotificationAndActionCount mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L8:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 5: default 56
        //                       0: 66
        //                       8: 68
        //                       16: 119
        //                       24: 171
        //                       32: 211;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L8; else goto _L7
_L7:
            return this;
_L2:
            return this;
_L3:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                notification = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            int k = codedinputbytebuffernano.readInt32();
            switch(k)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                action = k;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            int l = codedinputbytebuffernano.readInt32();
            switch(l)
            {
            case 0: // '\0'
            case 1: // '\001'
                recommender = l;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            count = codedinputbytebuffernano.readInt32();
            if(true) goto _L8; else goto _L9
_L9:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(notification != 0)
                codedoutputbytebuffernano.writeInt32(1, notification);
            if(action != 0)
                codedoutputbytebuffernano.writeInt32(2, action);
            if(recommender != 0)
                codedoutputbytebuffernano.writeInt32(3, recommender);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(4, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int ACTION_CONNECT_TO_NETWORK = 2;
        public static final int ACTION_PICK_WIFI_NETWORK = 3;
        public static final int ACTION_PICK_WIFI_NETWORK_AFTER_CONNECT_FAILURE = 4;
        public static final int ACTION_UNKNOWN = 0;
        public static final int ACTION_USER_DISMISSED_NOTIFICATION = 1;
        public static final int NOTIFICATION_CONNECTED_TO_NETWORK = 3;
        public static final int NOTIFICATION_CONNECTING_TO_NETWORK = 2;
        public static final int NOTIFICATION_FAILED_TO_CONNECT = 4;
        public static final int NOTIFICATION_RECOMMEND_NETWORK = 1;
        public static final int NOTIFICATION_UNKNOWN = 0;
        public static final int RECOMMENDER_OPEN = 1;
        public static final int RECOMMENDER_UNKNOWN = 0;
        private static volatile ConnectToNetworkNotificationAndActionCount _emptyArray[];
        public int action;
        public int count;
        public int notification;
        public int recommender;

        public ConnectToNetworkNotificationAndActionCount()
        {
            clear();
        }
    }

    public static final class ConnectionEvent extends MessageNano
    {

        public static ConnectionEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new ConnectionEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static ConnectionEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new ConnectionEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static ConnectionEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (ConnectionEvent)MessageNano.mergeFrom(new ConnectionEvent(), abyte0);
        }

        public ConnectionEvent clear()
        {
            startTimeMillis = 0L;
            durationTakenToConnectMillis = 0;
            routerFingerprint = null;
            signalStrength = 0;
            roamType = 0;
            connectionResult = 0;
            level2FailureCode = 0;
            connectivityLevelFailureCode = 0;
            automaticBugReportTaken = false;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(startTimeMillis != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(1, startTimeMillis);
            i = j;
            if(durationTakenToConnectMillis != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, durationTakenToConnectMillis);
            j = i;
            if(routerFingerprint != null)
                j = i + CodedOutputByteBufferNano.computeMessageSize(3, routerFingerprint);
            i = j;
            if(signalStrength != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, signalStrength);
            j = i;
            if(roamType != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(5, roamType);
            int k = j;
            if(connectionResult != 0)
                k = j + CodedOutputByteBufferNano.computeInt32Size(6, connectionResult);
            i = k;
            if(level2FailureCode != 0)
                i = k + CodedOutputByteBufferNano.computeInt32Size(7, level2FailureCode);
            j = i;
            if(connectivityLevelFailureCode != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(8, connectivityLevelFailureCode);
            i = j;
            if(automaticBugReportTaken)
                i = j + CodedOutputByteBufferNano.computeBoolSize(9, automaticBugReportTaken);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public ConnectionEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L13:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 10: default 96
        //                       0: 106
        //                       8: 108
        //                       16: 119
        //                       26: 130
        //                       32: 159
        //                       40: 170
        //                       48: 227
        //                       56: 238
        //                       64: 249
        //                       72: 299;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L13; else goto _L12
_L12:
            return this;
_L2:
            return this;
_L3:
            startTimeMillis = codedinputbytebuffernano.readInt64();
              goto _L13
_L4:
            durationTakenToConnectMillis = codedinputbytebuffernano.readInt32();
              goto _L13
_L5:
            if(routerFingerprint == null)
                routerFingerprint = new RouterFingerPrint();
            codedinputbytebuffernano.readMessage(routerFingerprint);
              goto _L13
_L6:
            signalStrength = codedinputbytebuffernano.readInt32();
              goto _L13
_L7:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
                roamType = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L8:
            connectionResult = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L9:
            level2FailureCode = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L10:
            int k = codedinputbytebuffernano.readInt32();
            switch(k)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                connectivityLevelFailureCode = k;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L11:
            automaticBugReportTaken = codedinputbytebuffernano.readBool();
            if(true) goto _L13; else goto _L14
_L14:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(startTimeMillis != 0L)
                codedoutputbytebuffernano.writeInt64(1, startTimeMillis);
            if(durationTakenToConnectMillis != 0)
                codedoutputbytebuffernano.writeInt32(2, durationTakenToConnectMillis);
            if(routerFingerprint != null)
                codedoutputbytebuffernano.writeMessage(3, routerFingerprint);
            if(signalStrength != 0)
                codedoutputbytebuffernano.writeInt32(4, signalStrength);
            if(roamType != 0)
                codedoutputbytebuffernano.writeInt32(5, roamType);
            if(connectionResult != 0)
                codedoutputbytebuffernano.writeInt32(6, connectionResult);
            if(level2FailureCode != 0)
                codedoutputbytebuffernano.writeInt32(7, level2FailureCode);
            if(connectivityLevelFailureCode != 0)
                codedoutputbytebuffernano.writeInt32(8, connectivityLevelFailureCode);
            if(automaticBugReportTaken)
                codedoutputbytebuffernano.writeBool(9, automaticBugReportTaken);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int HLF_DHCP = 2;
        public static final int HLF_NONE = 1;
        public static final int HLF_NO_INTERNET = 3;
        public static final int HLF_UNKNOWN = 0;
        public static final int HLF_UNWANTED = 4;
        public static final int ROAM_DBDC = 2;
        public static final int ROAM_ENTERPRISE = 3;
        public static final int ROAM_NONE = 1;
        public static final int ROAM_UNKNOWN = 0;
        public static final int ROAM_UNRELATED = 5;
        public static final int ROAM_USER_SELECTED = 4;
        private static volatile ConnectionEvent _emptyArray[];
        public boolean automaticBugReportTaken;
        public int connectionResult;
        public int connectivityLevelFailureCode;
        public int durationTakenToConnectMillis;
        public int level2FailureCode;
        public int roamType;
        public RouterFingerPrint routerFingerprint;
        public int signalStrength;
        public long startTimeMillis;

        public ConnectionEvent()
        {
            clear();
        }
    }

    public static final class NumConnectableNetworksBucket extends MessageNano
    {

        public static NumConnectableNetworksBucket[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new NumConnectableNetworksBucket[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static NumConnectableNetworksBucket parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new NumConnectableNetworksBucket()).mergeFrom(codedinputbytebuffernano);
        }

        public static NumConnectableNetworksBucket parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (NumConnectableNetworksBucket)MessageNano.mergeFrom(new NumConnectableNetworksBucket(), abyte0);
        }

        public NumConnectableNetworksBucket clear()
        {
            numConnectableNetworks = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(numConnectableNetworks != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, numConnectableNetworks);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, count);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public NumConnectableNetworksBucket mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    numConnectableNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    count = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(numConnectableNetworks != 0)
                codedoutputbytebuffernano.writeInt32(1, numConnectableNetworks);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(2, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile NumConnectableNetworksBucket _emptyArray[];
        public int count;
        public int numConnectableNetworks;

        public NumConnectableNetworksBucket()
        {
            clear();
        }
    }

    public static final class PnoScanMetrics extends MessageNano
    {

        public static PnoScanMetrics[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new PnoScanMetrics[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static PnoScanMetrics parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new PnoScanMetrics()).mergeFrom(codedinputbytebuffernano);
        }

        public static PnoScanMetrics parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (PnoScanMetrics)MessageNano.mergeFrom(new PnoScanMetrics(), abyte0);
        }

        public PnoScanMetrics clear()
        {
            numPnoScanAttempts = 0;
            numPnoScanFailed = 0;
            numPnoScanStartedOverOffload = 0;
            numPnoScanFailedOverOffload = 0;
            numPnoFoundNetworkEvents = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(numPnoScanAttempts != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, numPnoScanAttempts);
            i = j;
            if(numPnoScanFailed != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, numPnoScanFailed);
            int k = i;
            if(numPnoScanStartedOverOffload != 0)
                k = i + CodedOutputByteBufferNano.computeInt32Size(3, numPnoScanStartedOverOffload);
            j = k;
            if(numPnoScanFailedOverOffload != 0)
                j = k + CodedOutputByteBufferNano.computeInt32Size(4, numPnoScanFailedOverOffload);
            i = j;
            if(numPnoFoundNetworkEvents != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(5, numPnoFoundNetworkEvents);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public PnoScanMetrics mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    numPnoScanAttempts = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    numPnoScanFailed = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    numPnoScanStartedOverOffload = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    numPnoScanFailedOverOffload = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    numPnoFoundNetworkEvents = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(numPnoScanAttempts != 0)
                codedoutputbytebuffernano.writeInt32(1, numPnoScanAttempts);
            if(numPnoScanFailed != 0)
                codedoutputbytebuffernano.writeInt32(2, numPnoScanFailed);
            if(numPnoScanStartedOverOffload != 0)
                codedoutputbytebuffernano.writeInt32(3, numPnoScanStartedOverOffload);
            if(numPnoScanFailedOverOffload != 0)
                codedoutputbytebuffernano.writeInt32(4, numPnoScanFailedOverOffload);
            if(numPnoFoundNetworkEvents != 0)
                codedoutputbytebuffernano.writeInt32(5, numPnoFoundNetworkEvents);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile PnoScanMetrics _emptyArray[];
        public int numPnoFoundNetworkEvents;
        public int numPnoScanAttempts;
        public int numPnoScanFailed;
        public int numPnoScanFailedOverOffload;
        public int numPnoScanStartedOverOffload;

        public PnoScanMetrics()
        {
            clear();
        }
    }

    public static final class RouterFingerPrint extends MessageNano
    {

        public static RouterFingerPrint[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new RouterFingerPrint[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static RouterFingerPrint parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new RouterFingerPrint()).mergeFrom(codedinputbytebuffernano);
        }

        public static RouterFingerPrint parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (RouterFingerPrint)MessageNano.mergeFrom(new RouterFingerPrint(), abyte0);
        }

        public RouterFingerPrint clear()
        {
            roamType = 0;
            channelInfo = 0;
            dtim = 0;
            authentication = 0;
            hidden = false;
            routerTechnology = 0;
            supportsIpv6 = false;
            passpoint = false;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(roamType != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, roamType);
            i = j;
            if(channelInfo != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, channelInfo);
            j = i;
            if(dtim != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, dtim);
            i = j;
            if(authentication != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(4, authentication);
            j = i;
            if(hidden)
                j = i + CodedOutputByteBufferNano.computeBoolSize(5, hidden);
            i = j;
            if(routerTechnology != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(6, routerTechnology);
            j = i;
            if(supportsIpv6)
                j = i + CodedOutputByteBufferNano.computeBoolSize(7, supportsIpv6);
            i = j;
            if(passpoint)
                i = j + CodedOutputByteBufferNano.computeBoolSize(8, passpoint);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public RouterFingerPrint mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L12:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 9: default 88
        //                       0: 98
        //                       8: 100
        //                       16: 147
        //                       24: 158
        //                       32: 169
        //                       40: 215
        //                       48: 226
        //                       56: 287
        //                       64: 298;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L12; else goto _L11
_L11:
            return this;
_L2:
            return this;
_L3:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                roamType = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            channelInfo = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L5:
            dtim = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L6:
            int k = codedinputbytebuffernano.readInt32();
            switch(k)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                authentication = k;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L7:
            hidden = codedinputbytebuffernano.readBool();
            continue; /* Loop/switch isn't completed */
_L8:
            int l = codedinputbytebuffernano.readInt32();
            switch(l)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
                routerTechnology = l;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L9:
            supportsIpv6 = codedinputbytebuffernano.readBool();
            continue; /* Loop/switch isn't completed */
_L10:
            passpoint = codedinputbytebuffernano.readBool();
            if(true) goto _L12; else goto _L13
_L13:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(roamType != 0)
                codedoutputbytebuffernano.writeInt32(1, roamType);
            if(channelInfo != 0)
                codedoutputbytebuffernano.writeInt32(2, channelInfo);
            if(dtim != 0)
                codedoutputbytebuffernano.writeInt32(3, dtim);
            if(authentication != 0)
                codedoutputbytebuffernano.writeInt32(4, authentication);
            if(hidden)
                codedoutputbytebuffernano.writeBool(5, hidden);
            if(routerTechnology != 0)
                codedoutputbytebuffernano.writeInt32(6, routerTechnology);
            if(supportsIpv6)
                codedoutputbytebuffernano.writeBool(7, supportsIpv6);
            if(passpoint)
                codedoutputbytebuffernano.writeBool(8, passpoint);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int AUTH_ENTERPRISE = 3;
        public static final int AUTH_OPEN = 1;
        public static final int AUTH_PERSONAL = 2;
        public static final int AUTH_UNKNOWN = 0;
        public static final int ROAM_TYPE_DBDC = 3;
        public static final int ROAM_TYPE_ENTERPRISE = 2;
        public static final int ROAM_TYPE_NONE = 1;
        public static final int ROAM_TYPE_UNKNOWN = 0;
        public static final int ROUTER_TECH_A = 1;
        public static final int ROUTER_TECH_AC = 5;
        public static final int ROUTER_TECH_B = 2;
        public static final int ROUTER_TECH_G = 3;
        public static final int ROUTER_TECH_N = 4;
        public static final int ROUTER_TECH_OTHER = 6;
        public static final int ROUTER_TECH_UNKNOWN = 0;
        private static volatile RouterFingerPrint _emptyArray[];
        public int authentication;
        public int channelInfo;
        public int dtim;
        public boolean hidden;
        public boolean passpoint;
        public int roamType;
        public int routerTechnology;
        public boolean supportsIpv6;

        public RouterFingerPrint()
        {
            clear();
        }
    }

    public static final class RssiPollCount extends MessageNano
    {

        public static RssiPollCount[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new RssiPollCount[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static RssiPollCount parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new RssiPollCount()).mergeFrom(codedinputbytebuffernano);
        }

        public static RssiPollCount parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (RssiPollCount)MessageNano.mergeFrom(new RssiPollCount(), abyte0);
        }

        public RssiPollCount clear()
        {
            rssi = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(rssi != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, rssi);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, count);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public RssiPollCount mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    rssi = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    count = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(rssi != 0)
                codedoutputbytebuffernano.writeInt32(1, rssi);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(2, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile RssiPollCount _emptyArray[];
        public int count;
        public int rssi;

        public RssiPollCount()
        {
            clear();
        }
    }

    public static final class SoftApDurationBucket extends MessageNano
    {

        public static SoftApDurationBucket[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new SoftApDurationBucket[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static SoftApDurationBucket parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new SoftApDurationBucket()).mergeFrom(codedinputbytebuffernano);
        }

        public static SoftApDurationBucket parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (SoftApDurationBucket)MessageNano.mergeFrom(new SoftApDurationBucket(), abyte0);
        }

        public SoftApDurationBucket clear()
        {
            durationSec = 0;
            bucketSizeSec = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(durationSec != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, durationSec);
            i = j;
            if(bucketSizeSec != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, bucketSizeSec);
            j = i;
            if(count != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, count);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public SoftApDurationBucket mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    durationSec = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    bucketSizeSec = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    count = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(durationSec != 0)
                codedoutputbytebuffernano.writeInt32(1, durationSec);
            if(bucketSizeSec != 0)
                codedoutputbytebuffernano.writeInt32(2, bucketSizeSec);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(3, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile SoftApDurationBucket _emptyArray[];
        public int bucketSizeSec;
        public int count;
        public int durationSec;

        public SoftApDurationBucket()
        {
            clear();
        }
    }

    public static final class SoftApReturnCodeCount extends MessageNano
    {

        public static SoftApReturnCodeCount[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new SoftApReturnCodeCount[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static SoftApReturnCodeCount parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new SoftApReturnCodeCount()).mergeFrom(codedinputbytebuffernano);
        }

        public static SoftApReturnCodeCount parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (SoftApReturnCodeCount)MessageNano.mergeFrom(new SoftApReturnCodeCount(), abyte0);
        }

        public SoftApReturnCodeCount clear()
        {
            returnCode = 0;
            count = 0;
            startResult = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(returnCode != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, returnCode);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, count);
            j = i;
            if(startResult != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, startResult);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public SoftApReturnCodeCount mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L7:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 4: default 48
        //                       0: 58
        //                       8: 60
        //                       16: 71
        //                       24: 82;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L7; else goto _L6
_L6:
            return this;
_L2:
            return this;
_L3:
            returnCode = codedinputbytebuffernano.readInt32();
              goto _L7
_L4:
            count = codedinputbytebuffernano.readInt32();
              goto _L7
_L5:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                startResult = j;
                break;
            }
            if(true) goto _L7; else goto _L8
_L8:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(returnCode != 0)
                codedoutputbytebuffernano.writeInt32(1, returnCode);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(2, count);
            if(startResult != 0)
                codedoutputbytebuffernano.writeInt32(3, startResult);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int SOFT_AP_FAILED_GENERAL_ERROR = 2;
        public static final int SOFT_AP_FAILED_NO_CHANNEL = 3;
        public static final int SOFT_AP_RETURN_CODE_UNKNOWN = 0;
        public static final int SOFT_AP_STARTED_SUCCESSFULLY = 1;
        private static volatile SoftApReturnCodeCount _emptyArray[];
        public int count;
        public int returnCode;
        public int startResult;

        public SoftApReturnCodeCount()
        {
            clear();
        }
    }

    public static final class StaEvent extends MessageNano
    {

        public static StaEvent[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new StaEvent[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static StaEvent parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new StaEvent()).mergeFrom(codedinputbytebuffernano);
        }

        public static StaEvent parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (StaEvent)MessageNano.mergeFrom(new StaEvent(), abyte0);
        }

        public StaEvent clear()
        {
            type = 0;
            reason = -1;
            status = -1;
            localGen = false;
            configInfo = null;
            lastRssi = -127;
            lastLinkSpeed = -1;
            lastFreq = -1;
            supplicantStateChangesBitmask = 0;
            startTimeMillis = 0L;
            frameworkDisconnectReason = 0;
            associationTimedOut = false;
            authFailureReason = 0;
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
            if(reason != -1)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, reason);
            j = i;
            if(status != -1)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, status);
            int k = j;
            if(localGen)
                k = j + CodedOutputByteBufferNano.computeBoolSize(4, localGen);
            i = k;
            if(configInfo != null)
                i = k + CodedOutputByteBufferNano.computeMessageSize(5, configInfo);
            j = i;
            if(lastRssi != -127)
                j = i + CodedOutputByteBufferNano.computeInt32Size(6, lastRssi);
            k = j;
            if(lastLinkSpeed != -1)
                k = j + CodedOutputByteBufferNano.computeInt32Size(7, lastLinkSpeed);
            i = k;
            if(lastFreq != -1)
                i = k + CodedOutputByteBufferNano.computeInt32Size(8, lastFreq);
            j = i;
            if(supplicantStateChangesBitmask != 0)
                j = i + CodedOutputByteBufferNano.computeUInt32Size(9, supplicantStateChangesBitmask);
            i = j;
            if(startTimeMillis != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(10, startTimeMillis);
            j = i;
            if(frameworkDisconnectReason != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(11, frameworkDisconnectReason);
            i = j;
            if(associationTimedOut)
                i = j + CodedOutputByteBufferNano.computeBoolSize(12, associationTimedOut);
            j = i;
            if(authFailureReason != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(13, authFailureReason);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public StaEvent mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L17:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 14: default 128
        //                       0: 138
        //                       8: 140
        //                       16: 235
        //                       24: 246
        //                       32: 257
        //                       42: 268
        //                       48: 297
        //                       56: 308
        //                       64: 319
        //                       72: 330
        //                       80: 341
        //                       88: 352
        //                       96: 411
        //                       104: 422;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L17; else goto _L16
_L16:
            return this;
_L2:
            return this;
_L3:
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
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            case 15: // '\017'
                type = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            reason = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L5:
            status = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L6:
            localGen = codedinputbytebuffernano.readBool();
            continue; /* Loop/switch isn't completed */
_L7:
            if(configInfo == null)
                configInfo = new ConfigInfo();
            codedinputbytebuffernano.readMessage(configInfo);
            continue; /* Loop/switch isn't completed */
_L8:
            lastRssi = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L9:
            lastLinkSpeed = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L10:
            lastFreq = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L11:
            supplicantStateChangesBitmask = codedinputbytebuffernano.readUInt32();
            continue; /* Loop/switch isn't completed */
_L12:
            startTimeMillis = codedinputbytebuffernano.readInt64();
            continue; /* Loop/switch isn't completed */
_L13:
            int k = codedinputbytebuffernano.readInt32();
            switch(k)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
                frameworkDisconnectReason = k;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L14:
            associationTimedOut = codedinputbytebuffernano.readBool();
            continue; /* Loop/switch isn't completed */
_L15:
            int l = codedinputbytebuffernano.readInt32();
            switch(l)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                authFailureReason = l;
                break;
            }
            if(true) goto _L17; else goto _L18
_L18:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(type != 0)
                codedoutputbytebuffernano.writeInt32(1, type);
            if(reason != -1)
                codedoutputbytebuffernano.writeInt32(2, reason);
            if(status != -1)
                codedoutputbytebuffernano.writeInt32(3, status);
            if(localGen)
                codedoutputbytebuffernano.writeBool(4, localGen);
            if(configInfo != null)
                codedoutputbytebuffernano.writeMessage(5, configInfo);
            if(lastRssi != -127)
                codedoutputbytebuffernano.writeInt32(6, lastRssi);
            if(lastLinkSpeed != -1)
                codedoutputbytebuffernano.writeInt32(7, lastLinkSpeed);
            if(lastFreq != -1)
                codedoutputbytebuffernano.writeInt32(8, lastFreq);
            if(supplicantStateChangesBitmask != 0)
                codedoutputbytebuffernano.writeUInt32(9, supplicantStateChangesBitmask);
            if(startTimeMillis != 0L)
                codedoutputbytebuffernano.writeInt64(10, startTimeMillis);
            if(frameworkDisconnectReason != 0)
                codedoutputbytebuffernano.writeInt32(11, frameworkDisconnectReason);
            if(associationTimedOut)
                codedoutputbytebuffernano.writeBool(12, associationTimedOut);
            if(authFailureReason != 0)
                codedoutputbytebuffernano.writeInt32(13, authFailureReason);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int AUTH_FAILURE_EAP_FAILURE = 4;
        public static final int AUTH_FAILURE_NONE = 1;
        public static final int AUTH_FAILURE_TIMEOUT = 2;
        public static final int AUTH_FAILURE_UNKNOWN = 0;
        public static final int AUTH_FAILURE_WRONG_PSWD = 3;
        public static final int DISCONNECT_API = 1;
        public static final int DISCONNECT_GENERIC = 2;
        public static final int DISCONNECT_P2P_DISCONNECT_WIFI_REQUEST = 5;
        public static final int DISCONNECT_RESET_SIM_NETWORKS = 6;
        public static final int DISCONNECT_ROAM_WATCHDOG_TIMER = 4;
        public static final int DISCONNECT_UNKNOWN = 0;
        public static final int DISCONNECT_UNWANTED = 3;
        public static final int STATE_ASSOCIATED = 6;
        public static final int STATE_ASSOCIATING = 5;
        public static final int STATE_AUTHENTICATING = 4;
        public static final int STATE_COMPLETED = 9;
        public static final int STATE_DISCONNECTED = 0;
        public static final int STATE_DORMANT = 10;
        public static final int STATE_FOUR_WAY_HANDSHAKE = 7;
        public static final int STATE_GROUP_HANDSHAKE = 8;
        public static final int STATE_INACTIVE = 2;
        public static final int STATE_INTERFACE_DISABLED = 1;
        public static final int STATE_INVALID = 12;
        public static final int STATE_SCANNING = 3;
        public static final int STATE_UNINITIALIZED = 11;
        public static final int TYPE_ASSOCIATION_REJECTION_EVENT = 1;
        public static final int TYPE_AUTHENTICATION_FAILURE_EVENT = 2;
        public static final int TYPE_CMD_ASSOCIATED_BSSID = 6;
        public static final int TYPE_CMD_IP_CONFIGURATION_LOST = 8;
        public static final int TYPE_CMD_IP_CONFIGURATION_SUCCESSFUL = 7;
        public static final int TYPE_CMD_IP_REACHABILITY_LOST = 9;
        public static final int TYPE_CMD_START_CONNECT = 11;
        public static final int TYPE_CMD_START_ROAM = 12;
        public static final int TYPE_CMD_TARGET_BSSID = 10;
        public static final int TYPE_CONNECT_NETWORK = 13;
        public static final int TYPE_FRAMEWORK_DISCONNECT = 15;
        public static final int TYPE_NETWORK_AGENT_VALID_NETWORK = 14;
        public static final int TYPE_NETWORK_CONNECTION_EVENT = 3;
        public static final int TYPE_NETWORK_DISCONNECTION_EVENT = 4;
        public static final int TYPE_SUPPLICANT_STATE_CHANGE_EVENT = 5;
        public static final int TYPE_UNKNOWN = 0;
        private static volatile StaEvent _emptyArray[];
        public boolean associationTimedOut;
        public int authFailureReason;
        public ConfigInfo configInfo;
        public int frameworkDisconnectReason;
        public int lastFreq;
        public int lastLinkSpeed;
        public int lastRssi;
        public boolean localGen;
        public int reason;
        public long startTimeMillis;
        public int status;
        public int supplicantStateChangesBitmask;
        public int type;

        public StaEvent()
        {
            clear();
        }
    }

    public static final class StaEvent.ConfigInfo extends MessageNano
    {

        public static StaEvent.ConfigInfo[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new StaEvent.ConfigInfo[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static StaEvent.ConfigInfo parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new StaEvent.ConfigInfo()).mergeFrom(codedinputbytebuffernano);
        }

        public static StaEvent.ConfigInfo parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (StaEvent.ConfigInfo)MessageNano.mergeFrom(new StaEvent.ConfigInfo(), abyte0);
        }

        public StaEvent.ConfigInfo clear()
        {
            allowedKeyManagement = 0;
            allowedProtocols = 0;
            allowedAuthAlgorithms = 0;
            allowedPairwiseCiphers = 0;
            allowedGroupCiphers = 0;
            hiddenSsid = false;
            isPasspoint = false;
            isEphemeral = false;
            hasEverConnected = false;
            scanRssi = -127;
            scanFreq = -1;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(allowedKeyManagement != 0)
                j = i + CodedOutputByteBufferNano.computeUInt32Size(1, allowedKeyManagement);
            i = j;
            if(allowedProtocols != 0)
                i = j + CodedOutputByteBufferNano.computeUInt32Size(2, allowedProtocols);
            int k = i;
            if(allowedAuthAlgorithms != 0)
                k = i + CodedOutputByteBufferNano.computeUInt32Size(3, allowedAuthAlgorithms);
            j = k;
            if(allowedPairwiseCiphers != 0)
                j = k + CodedOutputByteBufferNano.computeUInt32Size(4, allowedPairwiseCiphers);
            i = j;
            if(allowedGroupCiphers != 0)
                i = j + CodedOutputByteBufferNano.computeUInt32Size(5, allowedGroupCiphers);
            j = i;
            if(hiddenSsid)
                j = i + CodedOutputByteBufferNano.computeBoolSize(6, hiddenSsid);
            i = j;
            if(isPasspoint)
                i = j + CodedOutputByteBufferNano.computeBoolSize(7, isPasspoint);
            j = i;
            if(isEphemeral)
                j = i + CodedOutputByteBufferNano.computeBoolSize(8, isEphemeral);
            i = j;
            if(hasEverConnected)
                i = j + CodedOutputByteBufferNano.computeBoolSize(9, hasEverConnected);
            j = i;
            if(scanRssi != -127)
                j = i + CodedOutputByteBufferNano.computeInt32Size(10, scanRssi);
            i = j;
            if(scanFreq != -1)
                i = j + CodedOutputByteBufferNano.computeInt32Size(11, scanFreq);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public StaEvent.ConfigInfo mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    allowedKeyManagement = codedinputbytebuffernano.readUInt32();
                    break;

                case 16: // '\020'
                    allowedProtocols = codedinputbytebuffernano.readUInt32();
                    break;

                case 24: // '\030'
                    allowedAuthAlgorithms = codedinputbytebuffernano.readUInt32();
                    break;

                case 32: // ' '
                    allowedPairwiseCiphers = codedinputbytebuffernano.readUInt32();
                    break;

                case 40: // '('
                    allowedGroupCiphers = codedinputbytebuffernano.readUInt32();
                    break;

                case 48: // '0'
                    hiddenSsid = codedinputbytebuffernano.readBool();
                    break;

                case 56: // '8'
                    isPasspoint = codedinputbytebuffernano.readBool();
                    break;

                case 64: // '@'
                    isEphemeral = codedinputbytebuffernano.readBool();
                    break;

                case 72: // 'H'
                    hasEverConnected = codedinputbytebuffernano.readBool();
                    break;

                case 80: // 'P'
                    scanRssi = codedinputbytebuffernano.readInt32();
                    break;

                case 88: // 'X'
                    scanFreq = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(allowedKeyManagement != 0)
                codedoutputbytebuffernano.writeUInt32(1, allowedKeyManagement);
            if(allowedProtocols != 0)
                codedoutputbytebuffernano.writeUInt32(2, allowedProtocols);
            if(allowedAuthAlgorithms != 0)
                codedoutputbytebuffernano.writeUInt32(3, allowedAuthAlgorithms);
            if(allowedPairwiseCiphers != 0)
                codedoutputbytebuffernano.writeUInt32(4, allowedPairwiseCiphers);
            if(allowedGroupCiphers != 0)
                codedoutputbytebuffernano.writeUInt32(5, allowedGroupCiphers);
            if(hiddenSsid)
                codedoutputbytebuffernano.writeBool(6, hiddenSsid);
            if(isPasspoint)
                codedoutputbytebuffernano.writeBool(7, isPasspoint);
            if(isEphemeral)
                codedoutputbytebuffernano.writeBool(8, isEphemeral);
            if(hasEverConnected)
                codedoutputbytebuffernano.writeBool(9, hasEverConnected);
            if(scanRssi != -127)
                codedoutputbytebuffernano.writeInt32(10, scanRssi);
            if(scanFreq != -1)
                codedoutputbytebuffernano.writeInt32(11, scanFreq);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile StaEvent.ConfigInfo _emptyArray[];
        public int allowedAuthAlgorithms;
        public int allowedGroupCiphers;
        public int allowedKeyManagement;
        public int allowedPairwiseCiphers;
        public int allowedProtocols;
        public boolean hasEverConnected;
        public boolean hiddenSsid;
        public boolean isEphemeral;
        public boolean isPasspoint;
        public int scanFreq;
        public int scanRssi;

        public StaEvent.ConfigInfo()
        {
            clear();
        }
    }

    public static final class WifiAwareLog extends MessageNano
    {

        public static WifiAwareLog[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiAwareLog[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiAwareLog parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiAwareLog()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiAwareLog parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiAwareLog)MessageNano.mergeFrom(new WifiAwareLog(), abyte0);
        }

        public WifiAwareLog clear()
        {
            numApps = 0;
            numAppsUsingIdentityCallback = 0;
            maxConcurrentAttachSessionsInApp = 0;
            histogramAttachSessionStatus = NanStatusHistogramBucket.emptyArray();
            maxConcurrentPublishInApp = 0;
            maxConcurrentSubscribeInApp = 0;
            maxConcurrentDiscoverySessionsInApp = 0;
            maxConcurrentPublishInSystem = 0;
            maxConcurrentSubscribeInSystem = 0;
            maxConcurrentDiscoverySessionsInSystem = 0;
            histogramPublishStatus = NanStatusHistogramBucket.emptyArray();
            histogramSubscribeStatus = NanStatusHistogramBucket.emptyArray();
            numAppsWithDiscoverySessionFailureOutOfResources = 0;
            histogramRequestNdpStatus = NanStatusHistogramBucket.emptyArray();
            histogramRequestNdpOobStatus = NanStatusHistogramBucket.emptyArray();
            maxConcurrentNdiInApp = 0;
            maxConcurrentNdiInSystem = 0;
            maxConcurrentNdpInApp = 0;
            maxConcurrentNdpInSystem = 0;
            maxConcurrentSecureNdpInApp = 0;
            maxConcurrentSecureNdpInSystem = 0;
            maxConcurrentNdpPerNdi = 0;
            histogramAwareAvailableDurationMs = HistogramBucket.emptyArray();
            histogramAwareEnabledDurationMs = HistogramBucket.emptyArray();
            histogramAttachDurationMs = HistogramBucket.emptyArray();
            histogramPublishSessionDurationMs = HistogramBucket.emptyArray();
            histogramSubscribeSessionDurationMs = HistogramBucket.emptyArray();
            histogramNdpSessionDurationMs = HistogramBucket.emptyArray();
            histogramNdpSessionDataUsageMb = HistogramBucket.emptyArray();
            histogramNdpCreationTimeMs = HistogramBucket.emptyArray();
            ndpCreationTimeMsMin = 0L;
            ndpCreationTimeMsMax = 0L;
            ndpCreationTimeMsSum = 0L;
            ndpCreationTimeMsSumOfSq = 0L;
            ndpCreationTimeMsNumSamples = 0L;
            availableTimeMs = 0L;
            enabledTimeMs = 0L;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(numApps != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, numApps);
            i = j;
            if(numAppsUsingIdentityCallback != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, numAppsUsingIdentityCallback);
            j = i;
            if(maxConcurrentAttachSessionsInApp != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, maxConcurrentAttachSessionsInApp);
            i = j;
            if(histogramAttachSessionStatus != null)
            {
                i = j;
                if(histogramAttachSessionStatus.length > 0)
                {
                    int k = 0;
                    do
                    {
                        i = j;
                        if(k >= histogramAttachSessionStatus.length)
                            break;
                        NanStatusHistogramBucket nanstatushistogrambucket = histogramAttachSessionStatus[k];
                        i = j;
                        if(nanstatushistogrambucket != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(4, nanstatushistogrambucket);
                        k++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(maxConcurrentPublishInApp != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(5, maxConcurrentPublishInApp);
            i = j;
            if(maxConcurrentSubscribeInApp != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(6, maxConcurrentSubscribeInApp);
            j = i;
            if(maxConcurrentDiscoverySessionsInApp != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(7, maxConcurrentDiscoverySessionsInApp);
            i = j;
            if(maxConcurrentPublishInSystem != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(8, maxConcurrentPublishInSystem);
            j = i;
            if(maxConcurrentSubscribeInSystem != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(9, maxConcurrentSubscribeInSystem);
            i = j;
            if(maxConcurrentDiscoverySessionsInSystem != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(10, maxConcurrentDiscoverySessionsInSystem);
            j = i;
            if(histogramPublishStatus != null)
            {
                j = i;
                if(histogramPublishStatus.length > 0)
                {
                    int l = 0;
                    do
                    {
                        j = i;
                        if(l >= histogramPublishStatus.length)
                            break;
                        NanStatusHistogramBucket nanstatushistogrambucket1 = histogramPublishStatus[l];
                        j = i;
                        if(nanstatushistogrambucket1 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(11, nanstatushistogrambucket1);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            int i1 = j;
            if(histogramSubscribeStatus != null)
            {
                i1 = j;
                if(histogramSubscribeStatus.length > 0)
                {
                    i = 0;
                    do
                    {
                        i1 = j;
                        if(i >= histogramSubscribeStatus.length)
                            break;
                        NanStatusHistogramBucket nanstatushistogrambucket2 = histogramSubscribeStatus[i];
                        i1 = j;
                        if(nanstatushistogrambucket2 != null)
                            i1 = j + CodedOutputByteBufferNano.computeMessageSize(12, nanstatushistogrambucket2);
                        i++;
                        j = i1;
                    } while(true);
                }
            }
            i = i1;
            if(numAppsWithDiscoverySessionFailureOutOfResources != 0)
                i = i1 + CodedOutputByteBufferNano.computeInt32Size(13, numAppsWithDiscoverySessionFailureOutOfResources);
            j = i;
            if(histogramRequestNdpStatus != null)
            {
                j = i;
                if(histogramRequestNdpStatus.length > 0)
                {
                    i1 = 0;
                    do
                    {
                        j = i;
                        if(i1 >= histogramRequestNdpStatus.length)
                            break;
                        NanStatusHistogramBucket nanstatushistogrambucket3 = histogramRequestNdpStatus[i1];
                        j = i;
                        if(nanstatushistogrambucket3 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(14, nanstatushistogrambucket3);
                        i1++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(histogramRequestNdpOobStatus != null)
            {
                i = j;
                if(histogramRequestNdpOobStatus.length > 0)
                {
                    i1 = 0;
                    do
                    {
                        i = j;
                        if(i1 >= histogramRequestNdpOobStatus.length)
                            break;
                        NanStatusHistogramBucket nanstatushistogrambucket4 = histogramRequestNdpOobStatus[i1];
                        i = j;
                        if(nanstatushistogrambucket4 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(15, nanstatushistogrambucket4);
                        i1++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(maxConcurrentNdiInApp != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(19, maxConcurrentNdiInApp);
            i1 = j;
            if(maxConcurrentNdiInSystem != 0)
                i1 = j + CodedOutputByteBufferNano.computeInt32Size(20, maxConcurrentNdiInSystem);
            i = i1;
            if(maxConcurrentNdpInApp != 0)
                i = i1 + CodedOutputByteBufferNano.computeInt32Size(21, maxConcurrentNdpInApp);
            j = i;
            if(maxConcurrentNdpInSystem != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(22, maxConcurrentNdpInSystem);
            i = j;
            if(maxConcurrentSecureNdpInApp != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(23, maxConcurrentSecureNdpInApp);
            j = i;
            if(maxConcurrentSecureNdpInSystem != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(24, maxConcurrentSecureNdpInSystem);
            i = j;
            if(maxConcurrentNdpPerNdi != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(25, maxConcurrentNdpPerNdi);
            j = i;
            if(histogramAwareAvailableDurationMs != null)
            {
                j = i;
                if(histogramAwareAvailableDurationMs.length > 0)
                {
                    int j1 = 0;
                    do
                    {
                        j = i;
                        if(j1 >= histogramAwareAvailableDurationMs.length)
                            break;
                        HistogramBucket histogrambucket = histogramAwareAvailableDurationMs[j1];
                        j = i;
                        if(histogrambucket != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(26, histogrambucket);
                        j1++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(histogramAwareEnabledDurationMs != null)
            {
                i = j;
                if(histogramAwareEnabledDurationMs.length > 0)
                {
                    int k1 = 0;
                    do
                    {
                        i = j;
                        if(k1 >= histogramAwareEnabledDurationMs.length)
                            break;
                        HistogramBucket histogrambucket1 = histogramAwareEnabledDurationMs[k1];
                        i = j;
                        if(histogrambucket1 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(27, histogrambucket1);
                        k1++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(histogramAttachDurationMs != null)
            {
                j = i;
                if(histogramAttachDurationMs.length > 0)
                {
                    int l1 = 0;
                    do
                    {
                        j = i;
                        if(l1 >= histogramAttachDurationMs.length)
                            break;
                        HistogramBucket histogrambucket2 = histogramAttachDurationMs[l1];
                        j = i;
                        if(histogrambucket2 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(28, histogrambucket2);
                        l1++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(histogramPublishSessionDurationMs != null)
            {
                i = j;
                if(histogramPublishSessionDurationMs.length > 0)
                {
                    int i2 = 0;
                    do
                    {
                        i = j;
                        if(i2 >= histogramPublishSessionDurationMs.length)
                            break;
                        HistogramBucket histogrambucket3 = histogramPublishSessionDurationMs[i2];
                        i = j;
                        if(histogrambucket3 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(29, histogrambucket3);
                        i2++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(histogramSubscribeSessionDurationMs != null)
            {
                j = i;
                if(histogramSubscribeSessionDurationMs.length > 0)
                {
                    int j2 = 0;
                    do
                    {
                        j = i;
                        if(j2 >= histogramSubscribeSessionDurationMs.length)
                            break;
                        HistogramBucket histogrambucket4 = histogramSubscribeSessionDurationMs[j2];
                        j = i;
                        if(histogrambucket4 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(30, histogrambucket4);
                        j2++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(histogramNdpSessionDurationMs != null)
            {
                i = j;
                if(histogramNdpSessionDurationMs.length > 0)
                {
                    int k2 = 0;
                    do
                    {
                        i = j;
                        if(k2 >= histogramNdpSessionDurationMs.length)
                            break;
                        HistogramBucket histogrambucket5 = histogramNdpSessionDurationMs[k2];
                        i = j;
                        if(histogrambucket5 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(31, histogrambucket5);
                        k2++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(histogramNdpSessionDataUsageMb != null)
            {
                j = i;
                if(histogramNdpSessionDataUsageMb.length > 0)
                {
                    int l2 = 0;
                    do
                    {
                        j = i;
                        if(l2 >= histogramNdpSessionDataUsageMb.length)
                            break;
                        HistogramBucket histogrambucket6 = histogramNdpSessionDataUsageMb[l2];
                        j = i;
                        if(histogrambucket6 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(32, histogrambucket6);
                        l2++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(histogramNdpCreationTimeMs != null)
            {
                i = j;
                if(histogramNdpCreationTimeMs.length > 0)
                {
                    int i3 = 0;
                    do
                    {
                        i = j;
                        if(i3 >= histogramNdpCreationTimeMs.length)
                            break;
                        HistogramBucket histogrambucket7 = histogramNdpCreationTimeMs[i3];
                        i = j;
                        if(histogrambucket7 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(33, histogrambucket7);
                        i3++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(ndpCreationTimeMsMin != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(34, ndpCreationTimeMsMin);
            i = j;
            if(ndpCreationTimeMsMax != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(35, ndpCreationTimeMsMax);
            j = i;
            if(ndpCreationTimeMsSum != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(36, ndpCreationTimeMsSum);
            i = j;
            if(ndpCreationTimeMsSumOfSq != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(37, ndpCreationTimeMsSumOfSq);
            j = i;
            if(ndpCreationTimeMsNumSamples != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(38, ndpCreationTimeMsNumSamples);
            i = j;
            if(availableTimeMs != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(39, availableTimeMs);
            j = i;
            if(enabledTimeMs != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(40, enabledTimeMs);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiAwareLog mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    numApps = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    numAppsUsingIdentityCallback = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    maxConcurrentAttachSessionsInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 34: // '"'
                    int k3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 34);
                    int j;
                    NanStatusHistogramBucket ananstatushistogrambucket[];
                    if(histogramAttachSessionStatus == null)
                        j = 0;
                    else
                        j = histogramAttachSessionStatus.length;
                    ananstatushistogrambucket = new NanStatusHistogramBucket[j + k3];
                    k3 = j;
                    if(j != 0)
                    {
                        System.arraycopy(histogramAttachSessionStatus, 0, ananstatushistogrambucket, 0, j);
                        k3 = j;
                    }
                    for(; k3 < ananstatushistogrambucket.length - 1; k3++)
                    {
                        ananstatushistogrambucket[k3] = new NanStatusHistogramBucket();
                        codedinputbytebuffernano.readMessage(ananstatushistogrambucket[k3]);
                        codedinputbytebuffernano.readTag();
                    }

                    ananstatushistogrambucket[k3] = new NanStatusHistogramBucket();
                    codedinputbytebuffernano.readMessage(ananstatushistogrambucket[k3]);
                    histogramAttachSessionStatus = ananstatushistogrambucket;
                    break;

                case 40: // '('
                    maxConcurrentPublishInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 48: // '0'
                    maxConcurrentSubscribeInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 56: // '8'
                    maxConcurrentDiscoverySessionsInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 64: // '@'
                    maxConcurrentPublishInSystem = codedinputbytebuffernano.readInt32();
                    break;

                case 72: // 'H'
                    maxConcurrentSubscribeInSystem = codedinputbytebuffernano.readInt32();
                    break;

                case 80: // 'P'
                    maxConcurrentDiscoverySessionsInSystem = codedinputbytebuffernano.readInt32();
                    break;

                case 90: // 'Z'
                    int l3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 90);
                    int k;
                    NanStatusHistogramBucket ananstatushistogrambucket1[];
                    if(histogramPublishStatus == null)
                        k = 0;
                    else
                        k = histogramPublishStatus.length;
                    ananstatushistogrambucket1 = new NanStatusHistogramBucket[k + l3];
                    l3 = k;
                    if(k != 0)
                    {
                        System.arraycopy(histogramPublishStatus, 0, ananstatushistogrambucket1, 0, k);
                        l3 = k;
                    }
                    for(; l3 < ananstatushistogrambucket1.length - 1; l3++)
                    {
                        ananstatushistogrambucket1[l3] = new NanStatusHistogramBucket();
                        codedinputbytebuffernano.readMessage(ananstatushistogrambucket1[l3]);
                        codedinputbytebuffernano.readTag();
                    }

                    ananstatushistogrambucket1[l3] = new NanStatusHistogramBucket();
                    codedinputbytebuffernano.readMessage(ananstatushistogrambucket1[l3]);
                    histogramPublishStatus = ananstatushistogrambucket1;
                    break;

                case 98: // 'b'
                    int i4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 98);
                    int l;
                    NanStatusHistogramBucket ananstatushistogrambucket2[];
                    if(histogramSubscribeStatus == null)
                        l = 0;
                    else
                        l = histogramSubscribeStatus.length;
                    ananstatushistogrambucket2 = new NanStatusHistogramBucket[l + i4];
                    i4 = l;
                    if(l != 0)
                    {
                        System.arraycopy(histogramSubscribeStatus, 0, ananstatushistogrambucket2, 0, l);
                        i4 = l;
                    }
                    for(; i4 < ananstatushistogrambucket2.length - 1; i4++)
                    {
                        ananstatushistogrambucket2[i4] = new NanStatusHistogramBucket();
                        codedinputbytebuffernano.readMessage(ananstatushistogrambucket2[i4]);
                        codedinputbytebuffernano.readTag();
                    }

                    ananstatushistogrambucket2[i4] = new NanStatusHistogramBucket();
                    codedinputbytebuffernano.readMessage(ananstatushistogrambucket2[i4]);
                    histogramSubscribeStatus = ananstatushistogrambucket2;
                    break;

                case 104: // 'h'
                    numAppsWithDiscoverySessionFailureOutOfResources = codedinputbytebuffernano.readInt32();
                    break;

                case 114: // 'r'
                    int j4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 114);
                    int i1;
                    NanStatusHistogramBucket ananstatushistogrambucket3[];
                    if(histogramRequestNdpStatus == null)
                        i1 = 0;
                    else
                        i1 = histogramRequestNdpStatus.length;
                    ananstatushistogrambucket3 = new NanStatusHistogramBucket[i1 + j4];
                    j4 = i1;
                    if(i1 != 0)
                    {
                        System.arraycopy(histogramRequestNdpStatus, 0, ananstatushistogrambucket3, 0, i1);
                        j4 = i1;
                    }
                    for(; j4 < ananstatushistogrambucket3.length - 1; j4++)
                    {
                        ananstatushistogrambucket3[j4] = new NanStatusHistogramBucket();
                        codedinputbytebuffernano.readMessage(ananstatushistogrambucket3[j4]);
                        codedinputbytebuffernano.readTag();
                    }

                    ananstatushistogrambucket3[j4] = new NanStatusHistogramBucket();
                    codedinputbytebuffernano.readMessage(ananstatushistogrambucket3[j4]);
                    histogramRequestNdpStatus = ananstatushistogrambucket3;
                    break;

                case 122: // 'z'
                    int k4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 122);
                    int j1;
                    NanStatusHistogramBucket ananstatushistogrambucket4[];
                    if(histogramRequestNdpOobStatus == null)
                        j1 = 0;
                    else
                        j1 = histogramRequestNdpOobStatus.length;
                    ananstatushistogrambucket4 = new NanStatusHistogramBucket[j1 + k4];
                    k4 = j1;
                    if(j1 != 0)
                    {
                        System.arraycopy(histogramRequestNdpOobStatus, 0, ananstatushistogrambucket4, 0, j1);
                        k4 = j1;
                    }
                    for(; k4 < ananstatushistogrambucket4.length - 1; k4++)
                    {
                        ananstatushistogrambucket4[k4] = new NanStatusHistogramBucket();
                        codedinputbytebuffernano.readMessage(ananstatushistogrambucket4[k4]);
                        codedinputbytebuffernano.readTag();
                    }

                    ananstatushistogrambucket4[k4] = new NanStatusHistogramBucket();
                    codedinputbytebuffernano.readMessage(ananstatushistogrambucket4[k4]);
                    histogramRequestNdpOobStatus = ananstatushistogrambucket4;
                    break;

                case 152: 
                    maxConcurrentNdiInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 160: 
                    maxConcurrentNdiInSystem = codedinputbytebuffernano.readInt32();
                    break;

                case 168: 
                    maxConcurrentNdpInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 176: 
                    maxConcurrentNdpInSystem = codedinputbytebuffernano.readInt32();
                    break;

                case 184: 
                    maxConcurrentSecureNdpInApp = codedinputbytebuffernano.readInt32();
                    break;

                case 192: 
                    maxConcurrentSecureNdpInSystem = codedinputbytebuffernano.readInt32();
                    break;

                case 200: 
                    maxConcurrentNdpPerNdi = codedinputbytebuffernano.readInt32();
                    break;

                case 210: 
                    int l4 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 210);
                    int k1;
                    HistogramBucket ahistogrambucket[];
                    if(histogramAwareAvailableDurationMs == null)
                        k1 = 0;
                    else
                        k1 = histogramAwareAvailableDurationMs.length;
                    ahistogrambucket = new HistogramBucket[k1 + l4];
                    l4 = k1;
                    if(k1 != 0)
                    {
                        System.arraycopy(histogramAwareAvailableDurationMs, 0, ahistogrambucket, 0, k1);
                        l4 = k1;
                    }
                    for(; l4 < ahistogrambucket.length - 1; l4++)
                    {
                        ahistogrambucket[l4] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket[l4]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket[l4] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket[l4]);
                    histogramAwareAvailableDurationMs = ahistogrambucket;
                    break;

                case 218: 
                    int i5 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 218);
                    int l1;
                    HistogramBucket ahistogrambucket1[];
                    if(histogramAwareEnabledDurationMs == null)
                        l1 = 0;
                    else
                        l1 = histogramAwareEnabledDurationMs.length;
                    ahistogrambucket1 = new HistogramBucket[l1 + i5];
                    i5 = l1;
                    if(l1 != 0)
                    {
                        System.arraycopy(histogramAwareEnabledDurationMs, 0, ahistogrambucket1, 0, l1);
                        i5 = l1;
                    }
                    for(; i5 < ahistogrambucket1.length - 1; i5++)
                    {
                        ahistogrambucket1[i5] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket1[i5]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket1[i5] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket1[i5]);
                    histogramAwareEnabledDurationMs = ahistogrambucket1;
                    break;

                case 226: 
                    int j5 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 226);
                    int i2;
                    HistogramBucket ahistogrambucket2[];
                    if(histogramAttachDurationMs == null)
                        i2 = 0;
                    else
                        i2 = histogramAttachDurationMs.length;
                    ahistogrambucket2 = new HistogramBucket[i2 + j5];
                    j5 = i2;
                    if(i2 != 0)
                    {
                        System.arraycopy(histogramAttachDurationMs, 0, ahistogrambucket2, 0, i2);
                        j5 = i2;
                    }
                    for(; j5 < ahistogrambucket2.length - 1; j5++)
                    {
                        ahistogrambucket2[j5] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket2[j5]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket2[j5] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket2[j5]);
                    histogramAttachDurationMs = ahistogrambucket2;
                    break;

                case 234: 
                    int k5 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 234);
                    int j2;
                    HistogramBucket ahistogrambucket3[];
                    if(histogramPublishSessionDurationMs == null)
                        j2 = 0;
                    else
                        j2 = histogramPublishSessionDurationMs.length;
                    ahistogrambucket3 = new HistogramBucket[j2 + k5];
                    k5 = j2;
                    if(j2 != 0)
                    {
                        System.arraycopy(histogramPublishSessionDurationMs, 0, ahistogrambucket3, 0, j2);
                        k5 = j2;
                    }
                    for(; k5 < ahistogrambucket3.length - 1; k5++)
                    {
                        ahistogrambucket3[k5] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket3[k5]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket3[k5] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket3[k5]);
                    histogramPublishSessionDurationMs = ahistogrambucket3;
                    break;

                case 242: 
                    int l5 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 242);
                    int k2;
                    HistogramBucket ahistogrambucket4[];
                    if(histogramSubscribeSessionDurationMs == null)
                        k2 = 0;
                    else
                        k2 = histogramSubscribeSessionDurationMs.length;
                    ahistogrambucket4 = new HistogramBucket[k2 + l5];
                    l5 = k2;
                    if(k2 != 0)
                    {
                        System.arraycopy(histogramSubscribeSessionDurationMs, 0, ahistogrambucket4, 0, k2);
                        l5 = k2;
                    }
                    for(; l5 < ahistogrambucket4.length - 1; l5++)
                    {
                        ahistogrambucket4[l5] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket4[l5]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket4[l5] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket4[l5]);
                    histogramSubscribeSessionDurationMs = ahistogrambucket4;
                    break;

                case 250: 
                    int i6 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 250);
                    int l2;
                    HistogramBucket ahistogrambucket5[];
                    if(histogramNdpSessionDurationMs == null)
                        l2 = 0;
                    else
                        l2 = histogramNdpSessionDurationMs.length;
                    ahistogrambucket5 = new HistogramBucket[l2 + i6];
                    i6 = l2;
                    if(l2 != 0)
                    {
                        System.arraycopy(histogramNdpSessionDurationMs, 0, ahistogrambucket5, 0, l2);
                        i6 = l2;
                    }
                    for(; i6 < ahistogrambucket5.length - 1; i6++)
                    {
                        ahistogrambucket5[i6] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket5[i6]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket5[i6] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket5[i6]);
                    histogramNdpSessionDurationMs = ahistogrambucket5;
                    break;

                case 258: 
                    int j6 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 258);
                    int i3;
                    HistogramBucket ahistogrambucket6[];
                    if(histogramNdpSessionDataUsageMb == null)
                        i3 = 0;
                    else
                        i3 = histogramNdpSessionDataUsageMb.length;
                    ahistogrambucket6 = new HistogramBucket[i3 + j6];
                    j6 = i3;
                    if(i3 != 0)
                    {
                        System.arraycopy(histogramNdpSessionDataUsageMb, 0, ahistogrambucket6, 0, i3);
                        j6 = i3;
                    }
                    for(; j6 < ahistogrambucket6.length - 1; j6++)
                    {
                        ahistogrambucket6[j6] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket6[j6]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket6[j6] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket6[j6]);
                    histogramNdpSessionDataUsageMb = ahistogrambucket6;
                    break;

                case 266: 
                    int k6 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 266);
                    int j3;
                    HistogramBucket ahistogrambucket7[];
                    if(histogramNdpCreationTimeMs == null)
                        j3 = 0;
                    else
                        j3 = histogramNdpCreationTimeMs.length;
                    ahistogrambucket7 = new HistogramBucket[j3 + k6];
                    k6 = j3;
                    if(j3 != 0)
                    {
                        System.arraycopy(histogramNdpCreationTimeMs, 0, ahistogrambucket7, 0, j3);
                        k6 = j3;
                    }
                    for(; k6 < ahistogrambucket7.length - 1; k6++)
                    {
                        ahistogrambucket7[k6] = new HistogramBucket();
                        codedinputbytebuffernano.readMessage(ahistogrambucket7[k6]);
                        codedinputbytebuffernano.readTag();
                    }

                    ahistogrambucket7[k6] = new HistogramBucket();
                    codedinputbytebuffernano.readMessage(ahistogrambucket7[k6]);
                    histogramNdpCreationTimeMs = ahistogrambucket7;
                    break;

                case 272: 
                    ndpCreationTimeMsMin = codedinputbytebuffernano.readInt64();
                    break;

                case 280: 
                    ndpCreationTimeMsMax = codedinputbytebuffernano.readInt64();
                    break;

                case 288: 
                    ndpCreationTimeMsSum = codedinputbytebuffernano.readInt64();
                    break;

                case 296: 
                    ndpCreationTimeMsSumOfSq = codedinputbytebuffernano.readInt64();
                    break;

                case 304: 
                    ndpCreationTimeMsNumSamples = codedinputbytebuffernano.readInt64();
                    break;

                case 312: 
                    availableTimeMs = codedinputbytebuffernano.readInt64();
                    break;

                case 320: 
                    enabledTimeMs = codedinputbytebuffernano.readInt64();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(numApps != 0)
                codedoutputbytebuffernano.writeInt32(1, numApps);
            if(numAppsUsingIdentityCallback != 0)
                codedoutputbytebuffernano.writeInt32(2, numAppsUsingIdentityCallback);
            if(maxConcurrentAttachSessionsInApp != 0)
                codedoutputbytebuffernano.writeInt32(3, maxConcurrentAttachSessionsInApp);
            if(histogramAttachSessionStatus != null && histogramAttachSessionStatus.length > 0)
            {
                for(int i = 0; i < histogramAttachSessionStatus.length; i++)
                {
                    NanStatusHistogramBucket nanstatushistogrambucket = histogramAttachSessionStatus[i];
                    if(nanstatushistogrambucket != null)
                        codedoutputbytebuffernano.writeMessage(4, nanstatushistogrambucket);
                }

            }
            if(maxConcurrentPublishInApp != 0)
                codedoutputbytebuffernano.writeInt32(5, maxConcurrentPublishInApp);
            if(maxConcurrentSubscribeInApp != 0)
                codedoutputbytebuffernano.writeInt32(6, maxConcurrentSubscribeInApp);
            if(maxConcurrentDiscoverySessionsInApp != 0)
                codedoutputbytebuffernano.writeInt32(7, maxConcurrentDiscoverySessionsInApp);
            if(maxConcurrentPublishInSystem != 0)
                codedoutputbytebuffernano.writeInt32(8, maxConcurrentPublishInSystem);
            if(maxConcurrentSubscribeInSystem != 0)
                codedoutputbytebuffernano.writeInt32(9, maxConcurrentSubscribeInSystem);
            if(maxConcurrentDiscoverySessionsInSystem != 0)
                codedoutputbytebuffernano.writeInt32(10, maxConcurrentDiscoverySessionsInSystem);
            if(histogramPublishStatus != null && histogramPublishStatus.length > 0)
            {
                for(int j = 0; j < histogramPublishStatus.length; j++)
                {
                    NanStatusHistogramBucket nanstatushistogrambucket1 = histogramPublishStatus[j];
                    if(nanstatushistogrambucket1 != null)
                        codedoutputbytebuffernano.writeMessage(11, nanstatushistogrambucket1);
                }

            }
            if(histogramSubscribeStatus != null && histogramSubscribeStatus.length > 0)
            {
                for(int k = 0; k < histogramSubscribeStatus.length; k++)
                {
                    NanStatusHistogramBucket nanstatushistogrambucket2 = histogramSubscribeStatus[k];
                    if(nanstatushistogrambucket2 != null)
                        codedoutputbytebuffernano.writeMessage(12, nanstatushistogrambucket2);
                }

            }
            if(numAppsWithDiscoverySessionFailureOutOfResources != 0)
                codedoutputbytebuffernano.writeInt32(13, numAppsWithDiscoverySessionFailureOutOfResources);
            if(histogramRequestNdpStatus != null && histogramRequestNdpStatus.length > 0)
            {
                for(int l = 0; l < histogramRequestNdpStatus.length; l++)
                {
                    NanStatusHistogramBucket nanstatushistogrambucket3 = histogramRequestNdpStatus[l];
                    if(nanstatushistogrambucket3 != null)
                        codedoutputbytebuffernano.writeMessage(14, nanstatushistogrambucket3);
                }

            }
            if(histogramRequestNdpOobStatus != null && histogramRequestNdpOobStatus.length > 0)
            {
                for(int i1 = 0; i1 < histogramRequestNdpOobStatus.length; i1++)
                {
                    NanStatusHistogramBucket nanstatushistogrambucket4 = histogramRequestNdpOobStatus[i1];
                    if(nanstatushistogrambucket4 != null)
                        codedoutputbytebuffernano.writeMessage(15, nanstatushistogrambucket4);
                }

            }
            if(maxConcurrentNdiInApp != 0)
                codedoutputbytebuffernano.writeInt32(19, maxConcurrentNdiInApp);
            if(maxConcurrentNdiInSystem != 0)
                codedoutputbytebuffernano.writeInt32(20, maxConcurrentNdiInSystem);
            if(maxConcurrentNdpInApp != 0)
                codedoutputbytebuffernano.writeInt32(21, maxConcurrentNdpInApp);
            if(maxConcurrentNdpInSystem != 0)
                codedoutputbytebuffernano.writeInt32(22, maxConcurrentNdpInSystem);
            if(maxConcurrentSecureNdpInApp != 0)
                codedoutputbytebuffernano.writeInt32(23, maxConcurrentSecureNdpInApp);
            if(maxConcurrentSecureNdpInSystem != 0)
                codedoutputbytebuffernano.writeInt32(24, maxConcurrentSecureNdpInSystem);
            if(maxConcurrentNdpPerNdi != 0)
                codedoutputbytebuffernano.writeInt32(25, maxConcurrentNdpPerNdi);
            if(histogramAwareAvailableDurationMs != null && histogramAwareAvailableDurationMs.length > 0)
            {
                for(int j1 = 0; j1 < histogramAwareAvailableDurationMs.length; j1++)
                {
                    HistogramBucket histogrambucket = histogramAwareAvailableDurationMs[j1];
                    if(histogrambucket != null)
                        codedoutputbytebuffernano.writeMessage(26, histogrambucket);
                }

            }
            if(histogramAwareEnabledDurationMs != null && histogramAwareEnabledDurationMs.length > 0)
            {
                for(int k1 = 0; k1 < histogramAwareEnabledDurationMs.length; k1++)
                {
                    HistogramBucket histogrambucket1 = histogramAwareEnabledDurationMs[k1];
                    if(histogrambucket1 != null)
                        codedoutputbytebuffernano.writeMessage(27, histogrambucket1);
                }

            }
            if(histogramAttachDurationMs != null && histogramAttachDurationMs.length > 0)
            {
                for(int l1 = 0; l1 < histogramAttachDurationMs.length; l1++)
                {
                    HistogramBucket histogrambucket2 = histogramAttachDurationMs[l1];
                    if(histogrambucket2 != null)
                        codedoutputbytebuffernano.writeMessage(28, histogrambucket2);
                }

            }
            if(histogramPublishSessionDurationMs != null && histogramPublishSessionDurationMs.length > 0)
            {
                for(int i2 = 0; i2 < histogramPublishSessionDurationMs.length; i2++)
                {
                    HistogramBucket histogrambucket3 = histogramPublishSessionDurationMs[i2];
                    if(histogrambucket3 != null)
                        codedoutputbytebuffernano.writeMessage(29, histogrambucket3);
                }

            }
            if(histogramSubscribeSessionDurationMs != null && histogramSubscribeSessionDurationMs.length > 0)
            {
                for(int j2 = 0; j2 < histogramSubscribeSessionDurationMs.length; j2++)
                {
                    HistogramBucket histogrambucket4 = histogramSubscribeSessionDurationMs[j2];
                    if(histogrambucket4 != null)
                        codedoutputbytebuffernano.writeMessage(30, histogrambucket4);
                }

            }
            if(histogramNdpSessionDurationMs != null && histogramNdpSessionDurationMs.length > 0)
            {
                for(int k2 = 0; k2 < histogramNdpSessionDurationMs.length; k2++)
                {
                    HistogramBucket histogrambucket5 = histogramNdpSessionDurationMs[k2];
                    if(histogrambucket5 != null)
                        codedoutputbytebuffernano.writeMessage(31, histogrambucket5);
                }

            }
            if(histogramNdpSessionDataUsageMb != null && histogramNdpSessionDataUsageMb.length > 0)
            {
                for(int l2 = 0; l2 < histogramNdpSessionDataUsageMb.length; l2++)
                {
                    HistogramBucket histogrambucket6 = histogramNdpSessionDataUsageMb[l2];
                    if(histogrambucket6 != null)
                        codedoutputbytebuffernano.writeMessage(32, histogrambucket6);
                }

            }
            if(histogramNdpCreationTimeMs != null && histogramNdpCreationTimeMs.length > 0)
            {
                for(int i3 = 0; i3 < histogramNdpCreationTimeMs.length; i3++)
                {
                    HistogramBucket histogrambucket7 = histogramNdpCreationTimeMs[i3];
                    if(histogrambucket7 != null)
                        codedoutputbytebuffernano.writeMessage(33, histogrambucket7);
                }

            }
            if(ndpCreationTimeMsMin != 0L)
                codedoutputbytebuffernano.writeInt64(34, ndpCreationTimeMsMin);
            if(ndpCreationTimeMsMax != 0L)
                codedoutputbytebuffernano.writeInt64(35, ndpCreationTimeMsMax);
            if(ndpCreationTimeMsSum != 0L)
                codedoutputbytebuffernano.writeInt64(36, ndpCreationTimeMsSum);
            if(ndpCreationTimeMsSumOfSq != 0L)
                codedoutputbytebuffernano.writeInt64(37, ndpCreationTimeMsSumOfSq);
            if(ndpCreationTimeMsNumSamples != 0L)
                codedoutputbytebuffernano.writeInt64(38, ndpCreationTimeMsNumSamples);
            if(availableTimeMs != 0L)
                codedoutputbytebuffernano.writeInt64(39, availableTimeMs);
            if(enabledTimeMs != 0L)
                codedoutputbytebuffernano.writeInt64(40, enabledTimeMs);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int ALREADY_ENABLED = 11;
        public static final int FOLLOWUP_TX_QUEUE_FULL = 12;
        public static final int INTERNAL_FAILURE = 2;
        public static final int INVALID_ARGS = 6;
        public static final int INVALID_NDP_ID = 8;
        public static final int INVALID_PEER_ID = 7;
        public static final int INVALID_SESSION_ID = 4;
        public static final int NAN_NOT_ALLOWED = 9;
        public static final int NO_OTA_ACK = 10;
        public static final int NO_RESOURCES_AVAILABLE = 5;
        public static final int PROTOCOL_FAILURE = 3;
        public static final int SUCCESS = 1;
        public static final int UNKNOWN = 0;
        public static final int UNKNOWN_HAL_STATUS = 14;
        public static final int UNSUPPORTED_CONCURRENCY_NAN_DISABLED = 13;
        private static volatile WifiAwareLog _emptyArray[];
        public long availableTimeMs;
        public long enabledTimeMs;
        public HistogramBucket histogramAttachDurationMs[];
        public NanStatusHistogramBucket histogramAttachSessionStatus[];
        public HistogramBucket histogramAwareAvailableDurationMs[];
        public HistogramBucket histogramAwareEnabledDurationMs[];
        public HistogramBucket histogramNdpCreationTimeMs[];
        public HistogramBucket histogramNdpSessionDataUsageMb[];
        public HistogramBucket histogramNdpSessionDurationMs[];
        public HistogramBucket histogramPublishSessionDurationMs[];
        public NanStatusHistogramBucket histogramPublishStatus[];
        public NanStatusHistogramBucket histogramRequestNdpOobStatus[];
        public NanStatusHistogramBucket histogramRequestNdpStatus[];
        public HistogramBucket histogramSubscribeSessionDurationMs[];
        public NanStatusHistogramBucket histogramSubscribeStatus[];
        public int maxConcurrentAttachSessionsInApp;
        public int maxConcurrentDiscoverySessionsInApp;
        public int maxConcurrentDiscoverySessionsInSystem;
        public int maxConcurrentNdiInApp;
        public int maxConcurrentNdiInSystem;
        public int maxConcurrentNdpInApp;
        public int maxConcurrentNdpInSystem;
        public int maxConcurrentNdpPerNdi;
        public int maxConcurrentPublishInApp;
        public int maxConcurrentPublishInSystem;
        public int maxConcurrentSecureNdpInApp;
        public int maxConcurrentSecureNdpInSystem;
        public int maxConcurrentSubscribeInApp;
        public int maxConcurrentSubscribeInSystem;
        public long ndpCreationTimeMsMax;
        public long ndpCreationTimeMsMin;
        public long ndpCreationTimeMsNumSamples;
        public long ndpCreationTimeMsSum;
        public long ndpCreationTimeMsSumOfSq;
        public int numApps;
        public int numAppsUsingIdentityCallback;
        public int numAppsWithDiscoverySessionFailureOutOfResources;

        public WifiAwareLog()
        {
            clear();
        }
    }

    public static final class WifiAwareLog.HistogramBucket extends MessageNano
    {

        public static WifiAwareLog.HistogramBucket[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiAwareLog.HistogramBucket[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiAwareLog.HistogramBucket parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiAwareLog.HistogramBucket()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiAwareLog.HistogramBucket parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiAwareLog.HistogramBucket)MessageNano.mergeFrom(new WifiAwareLog.HistogramBucket(), abyte0);
        }

        public WifiAwareLog.HistogramBucket clear()
        {
            start = 0L;
            end = 0L;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(start != 0L)
                j = i + CodedOutputByteBufferNano.computeInt64Size(1, start);
            i = j;
            if(end != 0L)
                i = j + CodedOutputByteBufferNano.computeInt64Size(2, end);
            j = i;
            if(count != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(3, count);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiAwareLog.HistogramBucket mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    start = codedinputbytebuffernano.readInt64();
                    break;

                case 16: // '\020'
                    end = codedinputbytebuffernano.readInt64();
                    break;

                case 24: // '\030'
                    count = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(start != 0L)
                codedoutputbytebuffernano.writeInt64(1, start);
            if(end != 0L)
                codedoutputbytebuffernano.writeInt64(2, end);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(3, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile WifiAwareLog.HistogramBucket _emptyArray[];
        public int count;
        public long end;
        public long start;

        public WifiAwareLog.HistogramBucket()
        {
            clear();
        }
    }

    public static final class WifiAwareLog.NanStatusHistogramBucket extends MessageNano
    {

        public static WifiAwareLog.NanStatusHistogramBucket[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiAwareLog.NanStatusHistogramBucket[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiAwareLog.NanStatusHistogramBucket parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiAwareLog.NanStatusHistogramBucket()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiAwareLog.NanStatusHistogramBucket parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiAwareLog.NanStatusHistogramBucket)MessageNano.mergeFrom(new WifiAwareLog.NanStatusHistogramBucket(), abyte0);
        }

        public WifiAwareLog.NanStatusHistogramBucket clear()
        {
            nanStatusType = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(nanStatusType != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, nanStatusType);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, count);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiAwareLog.NanStatusHistogramBucket mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L6:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 3: default 40
        //                       0: 50
        //                       8: 52
        //                       16: 143;
               goto _L1 _L2 _L3 _L4
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L6; else goto _L5
_L5:
            return this;
_L2:
            return this;
_L3:
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
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
                nanStatusType = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            count = codedinputbytebuffernano.readInt32();
            if(true) goto _L6; else goto _L7
_L7:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(nanStatusType != 0)
                codedoutputbytebuffernano.writeInt32(1, nanStatusType);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(2, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile WifiAwareLog.NanStatusHistogramBucket _emptyArray[];
        public int count;
        public int nanStatusType;

        public WifiAwareLog.NanStatusHistogramBucket()
        {
            clear();
        }
    }

    public static final class WifiLog extends MessageNano
    {

        public static WifiLog[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiLog[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiLog parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiLog()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiLog parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiLog)MessageNano.mergeFrom(new WifiLog(), abyte0);
        }

        public WifiLog clear()
        {
            connectionEvent = ConnectionEvent.emptyArray();
            numSavedNetworks = 0;
            numOpenNetworks = 0;
            numPersonalNetworks = 0;
            numEnterpriseNetworks = 0;
            isLocationEnabled = false;
            isScanningAlwaysEnabled = false;
            numWifiToggledViaSettings = 0;
            numWifiToggledViaAirplane = 0;
            numNetworksAddedByUser = 0;
            numNetworksAddedByApps = 0;
            numEmptyScanResults = 0;
            numNonEmptyScanResults = 0;
            numOneshotScans = 0;
            numBackgroundScans = 0;
            scanReturnEntries = ScanReturnEntry.emptyArray();
            wifiSystemStateEntries = WifiSystemStateEntry.emptyArray();
            backgroundScanReturnEntries = ScanReturnEntry.emptyArray();
            backgroundScanRequestState = WifiSystemStateEntry.emptyArray();
            numLastResortWatchdogTriggers = 0;
            numLastResortWatchdogBadAssociationNetworksTotal = 0;
            numLastResortWatchdogBadAuthenticationNetworksTotal = 0;
            numLastResortWatchdogBadDhcpNetworksTotal = 0;
            numLastResortWatchdogBadOtherNetworksTotal = 0;
            numLastResortWatchdogAvailableNetworksTotal = 0;
            numLastResortWatchdogTriggersWithBadAssociation = 0;
            numLastResortWatchdogTriggersWithBadAuthentication = 0;
            numLastResortWatchdogTriggersWithBadDhcp = 0;
            numLastResortWatchdogTriggersWithBadOther = 0;
            numConnectivityWatchdogPnoGood = 0;
            numConnectivityWatchdogPnoBad = 0;
            numConnectivityWatchdogBackgroundGood = 0;
            numConnectivityWatchdogBackgroundBad = 0;
            recordDurationSec = 0;
            rssiPollRssiCount = RssiPollCount.emptyArray();
            numLastResortWatchdogSuccesses = 0;
            numHiddenNetworks = 0;
            numPasspointNetworks = 0;
            numTotalScanResults = 0;
            numOpenNetworkScanResults = 0;
            numPersonalNetworkScanResults = 0;
            numEnterpriseNetworkScanResults = 0;
            numHiddenNetworkScanResults = 0;
            numHotspot2R1NetworkScanResults = 0;
            numHotspot2R2NetworkScanResults = 0;
            numScans = 0;
            alertReasonCount = AlertReasonCount.emptyArray();
            wifiScoreCount = WifiScoreCount.emptyArray();
            softApDuration = SoftApDurationBucket.emptyArray();
            softApReturnCode = SoftApReturnCodeCount.emptyArray();
            rssiPollDeltaCount = RssiPollCount.emptyArray();
            staEventList = StaEvent.emptyArray();
            numHalCrashes = 0;
            numWificondCrashes = 0;
            numWifiOnFailureDueToHal = 0;
            numWifiOnFailureDueToWificond = 0;
            wifiAwareLog = null;
            numPasspointProviders = 0;
            numPasspointProviderInstallation = 0;
            numPasspointProviderInstallSuccess = 0;
            numPasspointProviderUninstallation = 0;
            numPasspointProviderUninstallSuccess = 0;
            numPasspointProvidersSuccessfullyConnected = 0;
            totalSsidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            totalBssidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableOpenSsidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableOpenBssidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableSavedSsidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableSavedBssidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableOpenOrSavedSsidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableOpenOrSavedBssidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableSavedPasspointProviderProfilesInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            availableSavedPasspointProviderBssidsInScanHistogram = NumConnectableNetworksBucket.emptyArray();
            fullBandAllSingleScanListenerResults = 0;
            partialAllSingleScanListenerResults = 0;
            pnoScanMetrics = null;
            connectToNetworkNotificationCount = ConnectToNetworkNotificationAndActionCount.emptyArray();
            connectToNetworkNotificationActionCount = ConnectToNetworkNotificationAndActionCount.emptyArray();
            openNetworkRecommenderBlacklistSize = 0;
            isWifiNetworksAvailableNotificationOn = false;
            numOpenNetworkRecommendationUpdates = 0;
            numOpenNetworkConnectMessageFailedToSend = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(connectionEvent != null)
            {
                j = i;
                if(connectionEvent.length > 0)
                {
                    int k = 0;
                    do
                    {
                        j = i;
                        if(k >= connectionEvent.length)
                            break;
                        ConnectionEvent connectionevent = connectionEvent[k];
                        j = i;
                        if(connectionevent != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(1, connectionevent);
                        k++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(numSavedNetworks != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, numSavedNetworks);
            int l = i;
            if(numOpenNetworks != 0)
                l = i + CodedOutputByteBufferNano.computeInt32Size(3, numOpenNetworks);
            j = l;
            if(numPersonalNetworks != 0)
                j = l + CodedOutputByteBufferNano.computeInt32Size(4, numPersonalNetworks);
            i = j;
            if(numEnterpriseNetworks != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(5, numEnterpriseNetworks);
            j = i;
            if(isLocationEnabled)
                j = i + CodedOutputByteBufferNano.computeBoolSize(6, isLocationEnabled);
            i = j;
            if(isScanningAlwaysEnabled)
                i = j + CodedOutputByteBufferNano.computeBoolSize(7, isScanningAlwaysEnabled);
            j = i;
            if(numWifiToggledViaSettings != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(8, numWifiToggledViaSettings);
            l = j;
            if(numWifiToggledViaAirplane != 0)
                l = j + CodedOutputByteBufferNano.computeInt32Size(9, numWifiToggledViaAirplane);
            i = l;
            if(numNetworksAddedByUser != 0)
                i = l + CodedOutputByteBufferNano.computeInt32Size(10, numNetworksAddedByUser);
            j = i;
            if(numNetworksAddedByApps != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(11, numNetworksAddedByApps);
            i = j;
            if(numEmptyScanResults != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(12, numEmptyScanResults);
            j = i;
            if(numNonEmptyScanResults != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(13, numNonEmptyScanResults);
            l = j;
            if(numOneshotScans != 0)
                l = j + CodedOutputByteBufferNano.computeInt32Size(14, numOneshotScans);
            i = l;
            if(numBackgroundScans != 0)
                i = l + CodedOutputByteBufferNano.computeInt32Size(15, numBackgroundScans);
            j = i;
            if(scanReturnEntries != null)
            {
                j = i;
                if(scanReturnEntries.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= scanReturnEntries.length)
                            break;
                        ScanReturnEntry scanreturnentry = scanReturnEntries[l];
                        j = i;
                        if(scanreturnentry != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(16, scanreturnentry);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(wifiSystemStateEntries != null)
            {
                i = j;
                if(wifiSystemStateEntries.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= wifiSystemStateEntries.length)
                            break;
                        WifiSystemStateEntry wifisystemstateentry = wifiSystemStateEntries[l];
                        i = j;
                        if(wifisystemstateentry != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(17, wifisystemstateentry);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(backgroundScanReturnEntries != null)
            {
                j = i;
                if(backgroundScanReturnEntries.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= backgroundScanReturnEntries.length)
                            break;
                        ScanReturnEntry scanreturnentry1 = backgroundScanReturnEntries[l];
                        j = i;
                        if(scanreturnentry1 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(18, scanreturnentry1);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(backgroundScanRequestState != null)
            {
                i = j;
                if(backgroundScanRequestState.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= backgroundScanRequestState.length)
                            break;
                        WifiSystemStateEntry wifisystemstateentry1 = backgroundScanRequestState[l];
                        i = j;
                        if(wifisystemstateentry1 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(19, wifisystemstateentry1);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            l = i;
            if(numLastResortWatchdogTriggers != 0)
                l = i + CodedOutputByteBufferNano.computeInt32Size(20, numLastResortWatchdogTriggers);
            j = l;
            if(numLastResortWatchdogBadAssociationNetworksTotal != 0)
                j = l + CodedOutputByteBufferNano.computeInt32Size(21, numLastResortWatchdogBadAssociationNetworksTotal);
            i = j;
            if(numLastResortWatchdogBadAuthenticationNetworksTotal != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(22, numLastResortWatchdogBadAuthenticationNetworksTotal);
            j = i;
            if(numLastResortWatchdogBadDhcpNetworksTotal != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(23, numLastResortWatchdogBadDhcpNetworksTotal);
            i = j;
            if(numLastResortWatchdogBadOtherNetworksTotal != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(24, numLastResortWatchdogBadOtherNetworksTotal);
            l = i;
            if(numLastResortWatchdogAvailableNetworksTotal != 0)
                l = i + CodedOutputByteBufferNano.computeInt32Size(25, numLastResortWatchdogAvailableNetworksTotal);
            j = l;
            if(numLastResortWatchdogTriggersWithBadAssociation != 0)
                j = l + CodedOutputByteBufferNano.computeInt32Size(26, numLastResortWatchdogTriggersWithBadAssociation);
            i = j;
            if(numLastResortWatchdogTriggersWithBadAuthentication != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(27, numLastResortWatchdogTriggersWithBadAuthentication);
            j = i;
            if(numLastResortWatchdogTriggersWithBadDhcp != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(28, numLastResortWatchdogTriggersWithBadDhcp);
            i = j;
            if(numLastResortWatchdogTriggersWithBadOther != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(29, numLastResortWatchdogTriggersWithBadOther);
            j = i;
            if(numConnectivityWatchdogPnoGood != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(30, numConnectivityWatchdogPnoGood);
            i = j;
            if(numConnectivityWatchdogPnoBad != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(31, numConnectivityWatchdogPnoBad);
            j = i;
            if(numConnectivityWatchdogBackgroundGood != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(32, numConnectivityWatchdogBackgroundGood);
            i = j;
            if(numConnectivityWatchdogBackgroundBad != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(33, numConnectivityWatchdogBackgroundBad);
            j = i;
            if(recordDurationSec != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(34, recordDurationSec);
            i = j;
            if(rssiPollRssiCount != null)
            {
                i = j;
                if(rssiPollRssiCount.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= rssiPollRssiCount.length)
                            break;
                        RssiPollCount rssipollcount = rssiPollRssiCount[l];
                        i = j;
                        if(rssipollcount != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(35, rssipollcount);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(numLastResortWatchdogSuccesses != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(36, numLastResortWatchdogSuccesses);
            i = j;
            if(numHiddenNetworks != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(37, numHiddenNetworks);
            j = i;
            if(numPasspointNetworks != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(38, numPasspointNetworks);
            i = j;
            if(numTotalScanResults != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(39, numTotalScanResults);
            j = i;
            if(numOpenNetworkScanResults != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(40, numOpenNetworkScanResults);
            i = j;
            if(numPersonalNetworkScanResults != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(41, numPersonalNetworkScanResults);
            l = i;
            if(numEnterpriseNetworkScanResults != 0)
                l = i + CodedOutputByteBufferNano.computeInt32Size(42, numEnterpriseNetworkScanResults);
            j = l;
            if(numHiddenNetworkScanResults != 0)
                j = l + CodedOutputByteBufferNano.computeInt32Size(43, numHiddenNetworkScanResults);
            i = j;
            if(numHotspot2R1NetworkScanResults != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(44, numHotspot2R1NetworkScanResults);
            j = i;
            if(numHotspot2R2NetworkScanResults != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(45, numHotspot2R2NetworkScanResults);
            i = j;
            if(numScans != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(46, numScans);
            j = i;
            if(alertReasonCount != null)
            {
                j = i;
                if(alertReasonCount.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= alertReasonCount.length)
                            break;
                        AlertReasonCount alertreasoncount = alertReasonCount[l];
                        j = i;
                        if(alertreasoncount != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(47, alertreasoncount);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            l = j;
            if(wifiScoreCount != null)
            {
                l = j;
                if(wifiScoreCount.length > 0)
                {
                    i = 0;
                    do
                    {
                        l = j;
                        if(i >= wifiScoreCount.length)
                            break;
                        WifiScoreCount wifiscorecount = wifiScoreCount[i];
                        l = j;
                        if(wifiscorecount != null)
                            l = j + CodedOutputByteBufferNano.computeMessageSize(48, wifiscorecount);
                        i++;
                        j = l;
                    } while(true);
                }
            }
            i = l;
            if(softApDuration != null)
            {
                i = l;
                if(softApDuration.length > 0)
                {
                    j = 0;
                    do
                    {
                        i = l;
                        if(j >= softApDuration.length)
                            break;
                        SoftApDurationBucket softapdurationbucket = softApDuration[j];
                        i = l;
                        if(softapdurationbucket != null)
                            i = l + CodedOutputByteBufferNano.computeMessageSize(49, softapdurationbucket);
                        j++;
                        l = i;
                    } while(true);
                }
            }
            j = i;
            if(softApReturnCode != null)
            {
                j = i;
                if(softApReturnCode.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= softApReturnCode.length)
                            break;
                        SoftApReturnCodeCount softapreturncodecount = softApReturnCode[l];
                        j = i;
                        if(softapreturncodecount != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(50, softapreturncodecount);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(rssiPollDeltaCount != null)
            {
                i = j;
                if(rssiPollDeltaCount.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= rssiPollDeltaCount.length)
                            break;
                        RssiPollCount rssipollcount1 = rssiPollDeltaCount[l];
                        i = j;
                        if(rssipollcount1 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(51, rssipollcount1);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(staEventList != null)
            {
                j = i;
                if(staEventList.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= staEventList.length)
                            break;
                        StaEvent staevent = staEventList[l];
                        j = i;
                        if(staevent != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(52, staevent);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(numHalCrashes != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(53, numHalCrashes);
            j = i;
            if(numWificondCrashes != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(54, numWificondCrashes);
            i = j;
            if(numWifiOnFailureDueToHal != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(55, numWifiOnFailureDueToHal);
            j = i;
            if(numWifiOnFailureDueToWificond != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(56, numWifiOnFailureDueToWificond);
            i = j;
            if(wifiAwareLog != null)
                i = j + CodedOutputByteBufferNano.computeMessageSize(57, wifiAwareLog);
            j = i;
            if(numPasspointProviders != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(58, numPasspointProviders);
            i = j;
            if(numPasspointProviderInstallation != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(59, numPasspointProviderInstallation);
            j = i;
            if(numPasspointProviderInstallSuccess != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(60, numPasspointProviderInstallSuccess);
            i = j;
            if(numPasspointProviderUninstallation != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(61, numPasspointProviderUninstallation);
            j = i;
            if(numPasspointProviderUninstallSuccess != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(62, numPasspointProviderUninstallSuccess);
            i = j;
            if(numPasspointProvidersSuccessfullyConnected != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(63, numPasspointProvidersSuccessfullyConnected);
            j = i;
            if(totalSsidsInScanHistogram != null)
            {
                j = i;
                if(totalSsidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= totalSsidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket = totalSsidsInScanHistogram[l];
                        j = i;
                        if(numconnectablenetworksbucket != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(64, numconnectablenetworksbucket);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(totalBssidsInScanHistogram != null)
            {
                i = j;
                if(totalBssidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= totalBssidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket1 = totalBssidsInScanHistogram[l];
                        i = j;
                        if(numconnectablenetworksbucket1 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(65, numconnectablenetworksbucket1);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(availableOpenSsidsInScanHistogram != null)
            {
                j = i;
                if(availableOpenSsidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= availableOpenSsidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket2 = availableOpenSsidsInScanHistogram[l];
                        j = i;
                        if(numconnectablenetworksbucket2 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(66, numconnectablenetworksbucket2);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(availableOpenBssidsInScanHistogram != null)
            {
                i = j;
                if(availableOpenBssidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= availableOpenBssidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket3 = availableOpenBssidsInScanHistogram[l];
                        i = j;
                        if(numconnectablenetworksbucket3 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(67, numconnectablenetworksbucket3);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(availableSavedSsidsInScanHistogram != null)
            {
                j = i;
                if(availableSavedSsidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= availableSavedSsidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket4 = availableSavedSsidsInScanHistogram[l];
                        j = i;
                        if(numconnectablenetworksbucket4 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(68, numconnectablenetworksbucket4);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(availableSavedBssidsInScanHistogram != null)
            {
                i = j;
                if(availableSavedBssidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= availableSavedBssidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket5 = availableSavedBssidsInScanHistogram[l];
                        i = j;
                        if(numconnectablenetworksbucket5 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(69, numconnectablenetworksbucket5);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(availableOpenOrSavedSsidsInScanHistogram != null)
            {
                j = i;
                if(availableOpenOrSavedSsidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= availableOpenOrSavedSsidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket6 = availableOpenOrSavedSsidsInScanHistogram[l];
                        j = i;
                        if(numconnectablenetworksbucket6 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(70, numconnectablenetworksbucket6);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(availableOpenOrSavedBssidsInScanHistogram != null)
            {
                i = j;
                if(availableOpenOrSavedBssidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= availableOpenOrSavedBssidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket7 = availableOpenOrSavedBssidsInScanHistogram[l];
                        i = j;
                        if(numconnectablenetworksbucket7 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(71, numconnectablenetworksbucket7);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(availableSavedPasspointProviderProfilesInScanHistogram != null)
            {
                j = i;
                if(availableSavedPasspointProviderProfilesInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        j = i;
                        if(l >= availableSavedPasspointProviderProfilesInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket8 = availableSavedPasspointProviderProfilesInScanHistogram[l];
                        j = i;
                        if(numconnectablenetworksbucket8 != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(72, numconnectablenetworksbucket8);
                        l++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(availableSavedPasspointProviderBssidsInScanHistogram != null)
            {
                i = j;
                if(availableSavedPasspointProviderBssidsInScanHistogram.length > 0)
                {
                    l = 0;
                    do
                    {
                        i = j;
                        if(l >= availableSavedPasspointProviderBssidsInScanHistogram.length)
                            break;
                        NumConnectableNetworksBucket numconnectablenetworksbucket9 = availableSavedPasspointProviderBssidsInScanHistogram[l];
                        i = j;
                        if(numconnectablenetworksbucket9 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(73, numconnectablenetworksbucket9);
                        l++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(fullBandAllSingleScanListenerResults != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(74, fullBandAllSingleScanListenerResults);
            l = j;
            if(partialAllSingleScanListenerResults != 0)
                l = j + CodedOutputByteBufferNano.computeInt32Size(75, partialAllSingleScanListenerResults);
            i = l;
            if(pnoScanMetrics != null)
                i = l + CodedOutputByteBufferNano.computeMessageSize(76, pnoScanMetrics);
            j = i;
            if(connectToNetworkNotificationCount != null)
            {
                j = i;
                if(connectToNetworkNotificationCount.length > 0)
                {
                    int i1 = 0;
                    do
                    {
                        j = i;
                        if(i1 >= connectToNetworkNotificationCount.length)
                            break;
                        ConnectToNetworkNotificationAndActionCount connecttonetworknotificationandactioncount = connectToNetworkNotificationCount[i1];
                        j = i;
                        if(connecttonetworknotificationandactioncount != null)
                            j = i + CodedOutputByteBufferNano.computeMessageSize(77, connecttonetworknotificationandactioncount);
                        i1++;
                        i = j;
                    } while(true);
                }
            }
            i = j;
            if(connectToNetworkNotificationActionCount != null)
            {
                i = j;
                if(connectToNetworkNotificationActionCount.length > 0)
                {
                    int j1 = 0;
                    do
                    {
                        i = j;
                        if(j1 >= connectToNetworkNotificationActionCount.length)
                            break;
                        ConnectToNetworkNotificationAndActionCount connecttonetworknotificationandactioncount1 = connectToNetworkNotificationActionCount[j1];
                        i = j;
                        if(connecttonetworknotificationandactioncount1 != null)
                            i = j + CodedOutputByteBufferNano.computeMessageSize(78, connecttonetworknotificationandactioncount1);
                        j1++;
                        j = i;
                    } while(true);
                }
            }
            j = i;
            if(openNetworkRecommenderBlacklistSize != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(79, openNetworkRecommenderBlacklistSize);
            i = j;
            if(isWifiNetworksAvailableNotificationOn)
                i = j + CodedOutputByteBufferNano.computeBoolSize(80, isWifiNetworksAvailableNotificationOn);
            j = i;
            if(numOpenNetworkRecommendationUpdates != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(81, numOpenNetworkRecommendationUpdates);
            i = j;
            if(numOpenNetworkConnectMessageFailedToSend != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(82, numOpenNetworkConnectMessageFailedToSend);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiLog mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    int j6 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                    int j;
                    ConnectionEvent aconnectionevent[];
                    if(connectionEvent == null)
                        j = 0;
                    else
                        j = connectionEvent.length;
                    aconnectionevent = new ConnectionEvent[j + j6];
                    j6 = j;
                    if(j != 0)
                    {
                        System.arraycopy(connectionEvent, 0, aconnectionevent, 0, j);
                        j6 = j;
                    }
                    for(; j6 < aconnectionevent.length - 1; j6++)
                    {
                        aconnectionevent[j6] = new ConnectionEvent();
                        codedinputbytebuffernano.readMessage(aconnectionevent[j6]);
                        codedinputbytebuffernano.readTag();
                    }

                    aconnectionevent[j6] = new ConnectionEvent();
                    codedinputbytebuffernano.readMessage(aconnectionevent[j6]);
                    connectionEvent = aconnectionevent;
                    break;

                case 16: // '\020'
                    numSavedNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 24: // '\030'
                    numOpenNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 32: // ' '
                    numPersonalNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 40: // '('
                    numEnterpriseNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 48: // '0'
                    isLocationEnabled = codedinputbytebuffernano.readBool();
                    break;

                case 56: // '8'
                    isScanningAlwaysEnabled = codedinputbytebuffernano.readBool();
                    break;

                case 64: // '@'
                    numWifiToggledViaSettings = codedinputbytebuffernano.readInt32();
                    break;

                case 72: // 'H'
                    numWifiToggledViaAirplane = codedinputbytebuffernano.readInt32();
                    break;

                case 80: // 'P'
                    numNetworksAddedByUser = codedinputbytebuffernano.readInt32();
                    break;

                case 88: // 'X'
                    numNetworksAddedByApps = codedinputbytebuffernano.readInt32();
                    break;

                case 96: // '`'
                    numEmptyScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 104: // 'h'
                    numNonEmptyScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 112: // 'p'
                    numOneshotScans = codedinputbytebuffernano.readInt32();
                    break;

                case 120: // 'x'
                    numBackgroundScans = codedinputbytebuffernano.readInt32();
                    break;

                case 130: 
                    int k6 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 130);
                    int k;
                    ScanReturnEntry ascanreturnentry[];
                    if(scanReturnEntries == null)
                        k = 0;
                    else
                        k = scanReturnEntries.length;
                    ascanreturnentry = new ScanReturnEntry[k + k6];
                    k6 = k;
                    if(k != 0)
                    {
                        System.arraycopy(scanReturnEntries, 0, ascanreturnentry, 0, k);
                        k6 = k;
                    }
                    for(; k6 < ascanreturnentry.length - 1; k6++)
                    {
                        ascanreturnentry[k6] = new ScanReturnEntry();
                        codedinputbytebuffernano.readMessage(ascanreturnentry[k6]);
                        codedinputbytebuffernano.readTag();
                    }

                    ascanreturnentry[k6] = new ScanReturnEntry();
                    codedinputbytebuffernano.readMessage(ascanreturnentry[k6]);
                    scanReturnEntries = ascanreturnentry;
                    break;

                case 138: 
                    int l6 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 138);
                    int l;
                    WifiSystemStateEntry awifisystemstateentry[];
                    if(wifiSystemStateEntries == null)
                        l = 0;
                    else
                        l = wifiSystemStateEntries.length;
                    awifisystemstateentry = new WifiSystemStateEntry[l + l6];
                    l6 = l;
                    if(l != 0)
                    {
                        System.arraycopy(wifiSystemStateEntries, 0, awifisystemstateentry, 0, l);
                        l6 = l;
                    }
                    for(; l6 < awifisystemstateentry.length - 1; l6++)
                    {
                        awifisystemstateentry[l6] = new WifiSystemStateEntry();
                        codedinputbytebuffernano.readMessage(awifisystemstateentry[l6]);
                        codedinputbytebuffernano.readTag();
                    }

                    awifisystemstateentry[l6] = new WifiSystemStateEntry();
                    codedinputbytebuffernano.readMessage(awifisystemstateentry[l6]);
                    wifiSystemStateEntries = awifisystemstateentry;
                    break;

                case 146: 
                    int i7 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 146);
                    int i1;
                    ScanReturnEntry ascanreturnentry1[];
                    if(backgroundScanReturnEntries == null)
                        i1 = 0;
                    else
                        i1 = backgroundScanReturnEntries.length;
                    ascanreturnentry1 = new ScanReturnEntry[i1 + i7];
                    i7 = i1;
                    if(i1 != 0)
                    {
                        System.arraycopy(backgroundScanReturnEntries, 0, ascanreturnentry1, 0, i1);
                        i7 = i1;
                    }
                    for(; i7 < ascanreturnentry1.length - 1; i7++)
                    {
                        ascanreturnentry1[i7] = new ScanReturnEntry();
                        codedinputbytebuffernano.readMessage(ascanreturnentry1[i7]);
                        codedinputbytebuffernano.readTag();
                    }

                    ascanreturnentry1[i7] = new ScanReturnEntry();
                    codedinputbytebuffernano.readMessage(ascanreturnentry1[i7]);
                    backgroundScanReturnEntries = ascanreturnentry1;
                    break;

                case 154: 
                    int j7 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 154);
                    int j1;
                    WifiSystemStateEntry awifisystemstateentry1[];
                    if(backgroundScanRequestState == null)
                        j1 = 0;
                    else
                        j1 = backgroundScanRequestState.length;
                    awifisystemstateentry1 = new WifiSystemStateEntry[j1 + j7];
                    j7 = j1;
                    if(j1 != 0)
                    {
                        System.arraycopy(backgroundScanRequestState, 0, awifisystemstateentry1, 0, j1);
                        j7 = j1;
                    }
                    for(; j7 < awifisystemstateentry1.length - 1; j7++)
                    {
                        awifisystemstateentry1[j7] = new WifiSystemStateEntry();
                        codedinputbytebuffernano.readMessage(awifisystemstateentry1[j7]);
                        codedinputbytebuffernano.readTag();
                    }

                    awifisystemstateentry1[j7] = new WifiSystemStateEntry();
                    codedinputbytebuffernano.readMessage(awifisystemstateentry1[j7]);
                    backgroundScanRequestState = awifisystemstateentry1;
                    break;

                case 160: 
                    numLastResortWatchdogTriggers = codedinputbytebuffernano.readInt32();
                    break;

                case 168: 
                    numLastResortWatchdogBadAssociationNetworksTotal = codedinputbytebuffernano.readInt32();
                    break;

                case 176: 
                    numLastResortWatchdogBadAuthenticationNetworksTotal = codedinputbytebuffernano.readInt32();
                    break;

                case 184: 
                    numLastResortWatchdogBadDhcpNetworksTotal = codedinputbytebuffernano.readInt32();
                    break;

                case 192: 
                    numLastResortWatchdogBadOtherNetworksTotal = codedinputbytebuffernano.readInt32();
                    break;

                case 200: 
                    numLastResortWatchdogAvailableNetworksTotal = codedinputbytebuffernano.readInt32();
                    break;

                case 208: 
                    numLastResortWatchdogTriggersWithBadAssociation = codedinputbytebuffernano.readInt32();
                    break;

                case 216: 
                    numLastResortWatchdogTriggersWithBadAuthentication = codedinputbytebuffernano.readInt32();
                    break;

                case 224: 
                    numLastResortWatchdogTriggersWithBadDhcp = codedinputbytebuffernano.readInt32();
                    break;

                case 232: 
                    numLastResortWatchdogTriggersWithBadOther = codedinputbytebuffernano.readInt32();
                    break;

                case 240: 
                    numConnectivityWatchdogPnoGood = codedinputbytebuffernano.readInt32();
                    break;

                case 248: 
                    numConnectivityWatchdogPnoBad = codedinputbytebuffernano.readInt32();
                    break;

                case 256: 
                    numConnectivityWatchdogBackgroundGood = codedinputbytebuffernano.readInt32();
                    break;

                case 264: 
                    numConnectivityWatchdogBackgroundBad = codedinputbytebuffernano.readInt32();
                    break;

                case 272: 
                    recordDurationSec = codedinputbytebuffernano.readInt32();
                    break;

                case 282: 
                    int k7 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 282);
                    int k1;
                    RssiPollCount arssipollcount[];
                    if(rssiPollRssiCount == null)
                        k1 = 0;
                    else
                        k1 = rssiPollRssiCount.length;
                    arssipollcount = new RssiPollCount[k1 + k7];
                    k7 = k1;
                    if(k1 != 0)
                    {
                        System.arraycopy(rssiPollRssiCount, 0, arssipollcount, 0, k1);
                        k7 = k1;
                    }
                    for(; k7 < arssipollcount.length - 1; k7++)
                    {
                        arssipollcount[k7] = new RssiPollCount();
                        codedinputbytebuffernano.readMessage(arssipollcount[k7]);
                        codedinputbytebuffernano.readTag();
                    }

                    arssipollcount[k7] = new RssiPollCount();
                    codedinputbytebuffernano.readMessage(arssipollcount[k7]);
                    rssiPollRssiCount = arssipollcount;
                    break;

                case 288: 
                    numLastResortWatchdogSuccesses = codedinputbytebuffernano.readInt32();
                    break;

                case 296: 
                    numHiddenNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 304: 
                    numPasspointNetworks = codedinputbytebuffernano.readInt32();
                    break;

                case 312: 
                    numTotalScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 320: 
                    numOpenNetworkScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 328: 
                    numPersonalNetworkScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 336: 
                    numEnterpriseNetworkScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 344: 
                    numHiddenNetworkScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 352: 
                    numHotspot2R1NetworkScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 360: 
                    numHotspot2R2NetworkScanResults = codedinputbytebuffernano.readInt32();
                    break;

                case 368: 
                    numScans = codedinputbytebuffernano.readInt32();
                    break;

                case 378: 
                    int l7 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 378);
                    int l1;
                    AlertReasonCount aalertreasoncount[];
                    if(alertReasonCount == null)
                        l1 = 0;
                    else
                        l1 = alertReasonCount.length;
                    aalertreasoncount = new AlertReasonCount[l1 + l7];
                    l7 = l1;
                    if(l1 != 0)
                    {
                        System.arraycopy(alertReasonCount, 0, aalertreasoncount, 0, l1);
                        l7 = l1;
                    }
                    for(; l7 < aalertreasoncount.length - 1; l7++)
                    {
                        aalertreasoncount[l7] = new AlertReasonCount();
                        codedinputbytebuffernano.readMessage(aalertreasoncount[l7]);
                        codedinputbytebuffernano.readTag();
                    }

                    aalertreasoncount[l7] = new AlertReasonCount();
                    codedinputbytebuffernano.readMessage(aalertreasoncount[l7]);
                    alertReasonCount = aalertreasoncount;
                    break;

                case 386: 
                    int i8 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 386);
                    int i2;
                    WifiScoreCount awifiscorecount[];
                    if(wifiScoreCount == null)
                        i2 = 0;
                    else
                        i2 = wifiScoreCount.length;
                    awifiscorecount = new WifiScoreCount[i2 + i8];
                    i8 = i2;
                    if(i2 != 0)
                    {
                        System.arraycopy(wifiScoreCount, 0, awifiscorecount, 0, i2);
                        i8 = i2;
                    }
                    for(; i8 < awifiscorecount.length - 1; i8++)
                    {
                        awifiscorecount[i8] = new WifiScoreCount();
                        codedinputbytebuffernano.readMessage(awifiscorecount[i8]);
                        codedinputbytebuffernano.readTag();
                    }

                    awifiscorecount[i8] = new WifiScoreCount();
                    codedinputbytebuffernano.readMessage(awifiscorecount[i8]);
                    wifiScoreCount = awifiscorecount;
                    break;

                case 394: 
                    int j8 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 394);
                    int j2;
                    SoftApDurationBucket asoftapdurationbucket[];
                    if(softApDuration == null)
                        j2 = 0;
                    else
                        j2 = softApDuration.length;
                    asoftapdurationbucket = new SoftApDurationBucket[j2 + j8];
                    j8 = j2;
                    if(j2 != 0)
                    {
                        System.arraycopy(softApDuration, 0, asoftapdurationbucket, 0, j2);
                        j8 = j2;
                    }
                    for(; j8 < asoftapdurationbucket.length - 1; j8++)
                    {
                        asoftapdurationbucket[j8] = new SoftApDurationBucket();
                        codedinputbytebuffernano.readMessage(asoftapdurationbucket[j8]);
                        codedinputbytebuffernano.readTag();
                    }

                    asoftapdurationbucket[j8] = new SoftApDurationBucket();
                    codedinputbytebuffernano.readMessage(asoftapdurationbucket[j8]);
                    softApDuration = asoftapdurationbucket;
                    break;

                case 402: 
                    int k8 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 402);
                    int k2;
                    SoftApReturnCodeCount asoftapreturncodecount[];
                    if(softApReturnCode == null)
                        k2 = 0;
                    else
                        k2 = softApReturnCode.length;
                    asoftapreturncodecount = new SoftApReturnCodeCount[k2 + k8];
                    k8 = k2;
                    if(k2 != 0)
                    {
                        System.arraycopy(softApReturnCode, 0, asoftapreturncodecount, 0, k2);
                        k8 = k2;
                    }
                    for(; k8 < asoftapreturncodecount.length - 1; k8++)
                    {
                        asoftapreturncodecount[k8] = new SoftApReturnCodeCount();
                        codedinputbytebuffernano.readMessage(asoftapreturncodecount[k8]);
                        codedinputbytebuffernano.readTag();
                    }

                    asoftapreturncodecount[k8] = new SoftApReturnCodeCount();
                    codedinputbytebuffernano.readMessage(asoftapreturncodecount[k8]);
                    softApReturnCode = asoftapreturncodecount;
                    break;

                case 410: 
                    int l8 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 410);
                    int l2;
                    RssiPollCount arssipollcount1[];
                    if(rssiPollDeltaCount == null)
                        l2 = 0;
                    else
                        l2 = rssiPollDeltaCount.length;
                    arssipollcount1 = new RssiPollCount[l2 + l8];
                    l8 = l2;
                    if(l2 != 0)
                    {
                        System.arraycopy(rssiPollDeltaCount, 0, arssipollcount1, 0, l2);
                        l8 = l2;
                    }
                    for(; l8 < arssipollcount1.length - 1; l8++)
                    {
                        arssipollcount1[l8] = new RssiPollCount();
                        codedinputbytebuffernano.readMessage(arssipollcount1[l8]);
                        codedinputbytebuffernano.readTag();
                    }

                    arssipollcount1[l8] = new RssiPollCount();
                    codedinputbytebuffernano.readMessage(arssipollcount1[l8]);
                    rssiPollDeltaCount = arssipollcount1;
                    break;

                case 418: 
                    int i9 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 418);
                    int i3;
                    StaEvent astaevent[];
                    if(staEventList == null)
                        i3 = 0;
                    else
                        i3 = staEventList.length;
                    astaevent = new StaEvent[i3 + i9];
                    i9 = i3;
                    if(i3 != 0)
                    {
                        System.arraycopy(staEventList, 0, astaevent, 0, i3);
                        i9 = i3;
                    }
                    for(; i9 < astaevent.length - 1; i9++)
                    {
                        astaevent[i9] = new StaEvent();
                        codedinputbytebuffernano.readMessage(astaevent[i9]);
                        codedinputbytebuffernano.readTag();
                    }

                    astaevent[i9] = new StaEvent();
                    codedinputbytebuffernano.readMessage(astaevent[i9]);
                    staEventList = astaevent;
                    break;

                case 424: 
                    numHalCrashes = codedinputbytebuffernano.readInt32();
                    break;

                case 432: 
                    numWificondCrashes = codedinputbytebuffernano.readInt32();
                    break;

                case 440: 
                    numWifiOnFailureDueToHal = codedinputbytebuffernano.readInt32();
                    break;

                case 448: 
                    numWifiOnFailureDueToWificond = codedinputbytebuffernano.readInt32();
                    break;

                case 458: 
                    if(wifiAwareLog == null)
                        wifiAwareLog = new WifiAwareLog();
                    codedinputbytebuffernano.readMessage(wifiAwareLog);
                    break;

                case 464: 
                    numPasspointProviders = codedinputbytebuffernano.readInt32();
                    break;

                case 472: 
                    numPasspointProviderInstallation = codedinputbytebuffernano.readInt32();
                    break;

                case 480: 
                    numPasspointProviderInstallSuccess = codedinputbytebuffernano.readInt32();
                    break;

                case 488: 
                    numPasspointProviderUninstallation = codedinputbytebuffernano.readInt32();
                    break;

                case 496: 
                    numPasspointProviderUninstallSuccess = codedinputbytebuffernano.readInt32();
                    break;

                case 504: 
                    numPasspointProvidersSuccessfullyConnected = codedinputbytebuffernano.readInt32();
                    break;

                case 514: 
                    int j9 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 514);
                    int j3;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket[];
                    if(totalSsidsInScanHistogram == null)
                        j3 = 0;
                    else
                        j3 = totalSsidsInScanHistogram.length;
                    anumconnectablenetworksbucket = new NumConnectableNetworksBucket[j3 + j9];
                    j9 = j3;
                    if(j3 != 0)
                    {
                        System.arraycopy(totalSsidsInScanHistogram, 0, anumconnectablenetworksbucket, 0, j3);
                        j9 = j3;
                    }
                    for(; j9 < anumconnectablenetworksbucket.length - 1; j9++)
                    {
                        anumconnectablenetworksbucket[j9] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket[j9]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket[j9] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket[j9]);
                    totalSsidsInScanHistogram = anumconnectablenetworksbucket;
                    break;

                case 522: 
                    int k9 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 522);
                    int k3;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket1[];
                    if(totalBssidsInScanHistogram == null)
                        k3 = 0;
                    else
                        k3 = totalBssidsInScanHistogram.length;
                    anumconnectablenetworksbucket1 = new NumConnectableNetworksBucket[k3 + k9];
                    k9 = k3;
                    if(k3 != 0)
                    {
                        System.arraycopy(totalBssidsInScanHistogram, 0, anumconnectablenetworksbucket1, 0, k3);
                        k9 = k3;
                    }
                    for(; k9 < anumconnectablenetworksbucket1.length - 1; k9++)
                    {
                        anumconnectablenetworksbucket1[k9] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket1[k9]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket1[k9] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket1[k9]);
                    totalBssidsInScanHistogram = anumconnectablenetworksbucket1;
                    break;

                case 530: 
                    int l9 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 530);
                    int l3;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket2[];
                    if(availableOpenSsidsInScanHistogram == null)
                        l3 = 0;
                    else
                        l3 = availableOpenSsidsInScanHistogram.length;
                    anumconnectablenetworksbucket2 = new NumConnectableNetworksBucket[l3 + l9];
                    l9 = l3;
                    if(l3 != 0)
                    {
                        System.arraycopy(availableOpenSsidsInScanHistogram, 0, anumconnectablenetworksbucket2, 0, l3);
                        l9 = l3;
                    }
                    for(; l9 < anumconnectablenetworksbucket2.length - 1; l9++)
                    {
                        anumconnectablenetworksbucket2[l9] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket2[l9]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket2[l9] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket2[l9]);
                    availableOpenSsidsInScanHistogram = anumconnectablenetworksbucket2;
                    break;

                case 538: 
                    int i10 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 538);
                    int i4;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket3[];
                    if(availableOpenBssidsInScanHistogram == null)
                        i4 = 0;
                    else
                        i4 = availableOpenBssidsInScanHistogram.length;
                    anumconnectablenetworksbucket3 = new NumConnectableNetworksBucket[i4 + i10];
                    i10 = i4;
                    if(i4 != 0)
                    {
                        System.arraycopy(availableOpenBssidsInScanHistogram, 0, anumconnectablenetworksbucket3, 0, i4);
                        i10 = i4;
                    }
                    for(; i10 < anumconnectablenetworksbucket3.length - 1; i10++)
                    {
                        anumconnectablenetworksbucket3[i10] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket3[i10]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket3[i10] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket3[i10]);
                    availableOpenBssidsInScanHistogram = anumconnectablenetworksbucket3;
                    break;

                case 546: 
                    int j10 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 546);
                    int j4;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket4[];
                    if(availableSavedSsidsInScanHistogram == null)
                        j4 = 0;
                    else
                        j4 = availableSavedSsidsInScanHistogram.length;
                    anumconnectablenetworksbucket4 = new NumConnectableNetworksBucket[j4 + j10];
                    j10 = j4;
                    if(j4 != 0)
                    {
                        System.arraycopy(availableSavedSsidsInScanHistogram, 0, anumconnectablenetworksbucket4, 0, j4);
                        j10 = j4;
                    }
                    for(; j10 < anumconnectablenetworksbucket4.length - 1; j10++)
                    {
                        anumconnectablenetworksbucket4[j10] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket4[j10]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket4[j10] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket4[j10]);
                    availableSavedSsidsInScanHistogram = anumconnectablenetworksbucket4;
                    break;

                case 554: 
                    int k10 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 554);
                    int k4;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket5[];
                    if(availableSavedBssidsInScanHistogram == null)
                        k4 = 0;
                    else
                        k4 = availableSavedBssidsInScanHistogram.length;
                    anumconnectablenetworksbucket5 = new NumConnectableNetworksBucket[k4 + k10];
                    k10 = k4;
                    if(k4 != 0)
                    {
                        System.arraycopy(availableSavedBssidsInScanHistogram, 0, anumconnectablenetworksbucket5, 0, k4);
                        k10 = k4;
                    }
                    for(; k10 < anumconnectablenetworksbucket5.length - 1; k10++)
                    {
                        anumconnectablenetworksbucket5[k10] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket5[k10]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket5[k10] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket5[k10]);
                    availableSavedBssidsInScanHistogram = anumconnectablenetworksbucket5;
                    break;

                case 562: 
                    int l10 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 562);
                    int l4;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket6[];
                    if(availableOpenOrSavedSsidsInScanHistogram == null)
                        l4 = 0;
                    else
                        l4 = availableOpenOrSavedSsidsInScanHistogram.length;
                    anumconnectablenetworksbucket6 = new NumConnectableNetworksBucket[l4 + l10];
                    l10 = l4;
                    if(l4 != 0)
                    {
                        System.arraycopy(availableOpenOrSavedSsidsInScanHistogram, 0, anumconnectablenetworksbucket6, 0, l4);
                        l10 = l4;
                    }
                    for(; l10 < anumconnectablenetworksbucket6.length - 1; l10++)
                    {
                        anumconnectablenetworksbucket6[l10] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket6[l10]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket6[l10] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket6[l10]);
                    availableOpenOrSavedSsidsInScanHistogram = anumconnectablenetworksbucket6;
                    break;

                case 570: 
                    int i11 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 570);
                    int i5;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket7[];
                    if(availableOpenOrSavedBssidsInScanHistogram == null)
                        i5 = 0;
                    else
                        i5 = availableOpenOrSavedBssidsInScanHistogram.length;
                    anumconnectablenetworksbucket7 = new NumConnectableNetworksBucket[i5 + i11];
                    i11 = i5;
                    if(i5 != 0)
                    {
                        System.arraycopy(availableOpenOrSavedBssidsInScanHistogram, 0, anumconnectablenetworksbucket7, 0, i5);
                        i11 = i5;
                    }
                    for(; i11 < anumconnectablenetworksbucket7.length - 1; i11++)
                    {
                        anumconnectablenetworksbucket7[i11] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket7[i11]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket7[i11] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket7[i11]);
                    availableOpenOrSavedBssidsInScanHistogram = anumconnectablenetworksbucket7;
                    break;

                case 578: 
                    int j11 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 578);
                    int j5;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket8[];
                    if(availableSavedPasspointProviderProfilesInScanHistogram == null)
                        j5 = 0;
                    else
                        j5 = availableSavedPasspointProviderProfilesInScanHistogram.length;
                    anumconnectablenetworksbucket8 = new NumConnectableNetworksBucket[j5 + j11];
                    j11 = j5;
                    if(j5 != 0)
                    {
                        System.arraycopy(availableSavedPasspointProviderProfilesInScanHistogram, 0, anumconnectablenetworksbucket8, 0, j5);
                        j11 = j5;
                    }
                    for(; j11 < anumconnectablenetworksbucket8.length - 1; j11++)
                    {
                        anumconnectablenetworksbucket8[j11] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket8[j11]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket8[j11] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket8[j11]);
                    availableSavedPasspointProviderProfilesInScanHistogram = anumconnectablenetworksbucket8;
                    break;

                case 586: 
                    int k11 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 586);
                    int k5;
                    NumConnectableNetworksBucket anumconnectablenetworksbucket9[];
                    if(availableSavedPasspointProviderBssidsInScanHistogram == null)
                        k5 = 0;
                    else
                        k5 = availableSavedPasspointProviderBssidsInScanHistogram.length;
                    anumconnectablenetworksbucket9 = new NumConnectableNetworksBucket[k5 + k11];
                    k11 = k5;
                    if(k5 != 0)
                    {
                        System.arraycopy(availableSavedPasspointProviderBssidsInScanHistogram, 0, anumconnectablenetworksbucket9, 0, k5);
                        k11 = k5;
                    }
                    for(; k11 < anumconnectablenetworksbucket9.length - 1; k11++)
                    {
                        anumconnectablenetworksbucket9[k11] = new NumConnectableNetworksBucket();
                        codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket9[k11]);
                        codedinputbytebuffernano.readTag();
                    }

                    anumconnectablenetworksbucket9[k11] = new NumConnectableNetworksBucket();
                    codedinputbytebuffernano.readMessage(anumconnectablenetworksbucket9[k11]);
                    availableSavedPasspointProviderBssidsInScanHistogram = anumconnectablenetworksbucket9;
                    break;

                case 592: 
                    fullBandAllSingleScanListenerResults = codedinputbytebuffernano.readInt32();
                    break;

                case 600: 
                    partialAllSingleScanListenerResults = codedinputbytebuffernano.readInt32();
                    break;

                case 610: 
                    if(pnoScanMetrics == null)
                        pnoScanMetrics = new PnoScanMetrics();
                    codedinputbytebuffernano.readMessage(pnoScanMetrics);
                    break;

                case 618: 
                    int l11 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 618);
                    int l5;
                    ConnectToNetworkNotificationAndActionCount aconnecttonetworknotificationandactioncount[];
                    if(connectToNetworkNotificationCount == null)
                        l5 = 0;
                    else
                        l5 = connectToNetworkNotificationCount.length;
                    aconnecttonetworknotificationandactioncount = new ConnectToNetworkNotificationAndActionCount[l5 + l11];
                    l11 = l5;
                    if(l5 != 0)
                    {
                        System.arraycopy(connectToNetworkNotificationCount, 0, aconnecttonetworknotificationandactioncount, 0, l5);
                        l11 = l5;
                    }
                    for(; l11 < aconnecttonetworknotificationandactioncount.length - 1; l11++)
                    {
                        aconnecttonetworknotificationandactioncount[l11] = new ConnectToNetworkNotificationAndActionCount();
                        codedinputbytebuffernano.readMessage(aconnecttonetworknotificationandactioncount[l11]);
                        codedinputbytebuffernano.readTag();
                    }

                    aconnecttonetworknotificationandactioncount[l11] = new ConnectToNetworkNotificationAndActionCount();
                    codedinputbytebuffernano.readMessage(aconnecttonetworknotificationandactioncount[l11]);
                    connectToNetworkNotificationCount = aconnecttonetworknotificationandactioncount;
                    break;

                case 626: 
                    int i12 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 626);
                    int i6;
                    ConnectToNetworkNotificationAndActionCount aconnecttonetworknotificationandactioncount1[];
                    if(connectToNetworkNotificationActionCount == null)
                        i6 = 0;
                    else
                        i6 = connectToNetworkNotificationActionCount.length;
                    aconnecttonetworknotificationandactioncount1 = new ConnectToNetworkNotificationAndActionCount[i6 + i12];
                    i12 = i6;
                    if(i6 != 0)
                    {
                        System.arraycopy(connectToNetworkNotificationActionCount, 0, aconnecttonetworknotificationandactioncount1, 0, i6);
                        i12 = i6;
                    }
                    for(; i12 < aconnecttonetworknotificationandactioncount1.length - 1; i12++)
                    {
                        aconnecttonetworknotificationandactioncount1[i12] = new ConnectToNetworkNotificationAndActionCount();
                        codedinputbytebuffernano.readMessage(aconnecttonetworknotificationandactioncount1[i12]);
                        codedinputbytebuffernano.readTag();
                    }

                    aconnecttonetworknotificationandactioncount1[i12] = new ConnectToNetworkNotificationAndActionCount();
                    codedinputbytebuffernano.readMessage(aconnecttonetworknotificationandactioncount1[i12]);
                    connectToNetworkNotificationActionCount = aconnecttonetworknotificationandactioncount1;
                    break;

                case 632: 
                    openNetworkRecommenderBlacklistSize = codedinputbytebuffernano.readInt32();
                    break;

                case 640: 
                    isWifiNetworksAvailableNotificationOn = codedinputbytebuffernano.readBool();
                    break;

                case 648: 
                    numOpenNetworkRecommendationUpdates = codedinputbytebuffernano.readInt32();
                    break;

                case 656: 
                    numOpenNetworkConnectMessageFailedToSend = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(connectionEvent != null && connectionEvent.length > 0)
            {
                for(int i = 0; i < connectionEvent.length; i++)
                {
                    ConnectionEvent connectionevent = connectionEvent[i];
                    if(connectionevent != null)
                        codedoutputbytebuffernano.writeMessage(1, connectionevent);
                }

            }
            if(numSavedNetworks != 0)
                codedoutputbytebuffernano.writeInt32(2, numSavedNetworks);
            if(numOpenNetworks != 0)
                codedoutputbytebuffernano.writeInt32(3, numOpenNetworks);
            if(numPersonalNetworks != 0)
                codedoutputbytebuffernano.writeInt32(4, numPersonalNetworks);
            if(numEnterpriseNetworks != 0)
                codedoutputbytebuffernano.writeInt32(5, numEnterpriseNetworks);
            if(isLocationEnabled)
                codedoutputbytebuffernano.writeBool(6, isLocationEnabled);
            if(isScanningAlwaysEnabled)
                codedoutputbytebuffernano.writeBool(7, isScanningAlwaysEnabled);
            if(numWifiToggledViaSettings != 0)
                codedoutputbytebuffernano.writeInt32(8, numWifiToggledViaSettings);
            if(numWifiToggledViaAirplane != 0)
                codedoutputbytebuffernano.writeInt32(9, numWifiToggledViaAirplane);
            if(numNetworksAddedByUser != 0)
                codedoutputbytebuffernano.writeInt32(10, numNetworksAddedByUser);
            if(numNetworksAddedByApps != 0)
                codedoutputbytebuffernano.writeInt32(11, numNetworksAddedByApps);
            if(numEmptyScanResults != 0)
                codedoutputbytebuffernano.writeInt32(12, numEmptyScanResults);
            if(numNonEmptyScanResults != 0)
                codedoutputbytebuffernano.writeInt32(13, numNonEmptyScanResults);
            if(numOneshotScans != 0)
                codedoutputbytebuffernano.writeInt32(14, numOneshotScans);
            if(numBackgroundScans != 0)
                codedoutputbytebuffernano.writeInt32(15, numBackgroundScans);
            if(scanReturnEntries != null && scanReturnEntries.length > 0)
            {
                for(int j = 0; j < scanReturnEntries.length; j++)
                {
                    ScanReturnEntry scanreturnentry = scanReturnEntries[j];
                    if(scanreturnentry != null)
                        codedoutputbytebuffernano.writeMessage(16, scanreturnentry);
                }

            }
            if(wifiSystemStateEntries != null && wifiSystemStateEntries.length > 0)
            {
                for(int k = 0; k < wifiSystemStateEntries.length; k++)
                {
                    WifiSystemStateEntry wifisystemstateentry = wifiSystemStateEntries[k];
                    if(wifisystemstateentry != null)
                        codedoutputbytebuffernano.writeMessage(17, wifisystemstateentry);
                }

            }
            if(backgroundScanReturnEntries != null && backgroundScanReturnEntries.length > 0)
            {
                for(int l = 0; l < backgroundScanReturnEntries.length; l++)
                {
                    ScanReturnEntry scanreturnentry1 = backgroundScanReturnEntries[l];
                    if(scanreturnentry1 != null)
                        codedoutputbytebuffernano.writeMessage(18, scanreturnentry1);
                }

            }
            if(backgroundScanRequestState != null && backgroundScanRequestState.length > 0)
            {
                for(int i1 = 0; i1 < backgroundScanRequestState.length; i1++)
                {
                    WifiSystemStateEntry wifisystemstateentry1 = backgroundScanRequestState[i1];
                    if(wifisystemstateentry1 != null)
                        codedoutputbytebuffernano.writeMessage(19, wifisystemstateentry1);
                }

            }
            if(numLastResortWatchdogTriggers != 0)
                codedoutputbytebuffernano.writeInt32(20, numLastResortWatchdogTriggers);
            if(numLastResortWatchdogBadAssociationNetworksTotal != 0)
                codedoutputbytebuffernano.writeInt32(21, numLastResortWatchdogBadAssociationNetworksTotal);
            if(numLastResortWatchdogBadAuthenticationNetworksTotal != 0)
                codedoutputbytebuffernano.writeInt32(22, numLastResortWatchdogBadAuthenticationNetworksTotal);
            if(numLastResortWatchdogBadDhcpNetworksTotal != 0)
                codedoutputbytebuffernano.writeInt32(23, numLastResortWatchdogBadDhcpNetworksTotal);
            if(numLastResortWatchdogBadOtherNetworksTotal != 0)
                codedoutputbytebuffernano.writeInt32(24, numLastResortWatchdogBadOtherNetworksTotal);
            if(numLastResortWatchdogAvailableNetworksTotal != 0)
                codedoutputbytebuffernano.writeInt32(25, numLastResortWatchdogAvailableNetworksTotal);
            if(numLastResortWatchdogTriggersWithBadAssociation != 0)
                codedoutputbytebuffernano.writeInt32(26, numLastResortWatchdogTriggersWithBadAssociation);
            if(numLastResortWatchdogTriggersWithBadAuthentication != 0)
                codedoutputbytebuffernano.writeInt32(27, numLastResortWatchdogTriggersWithBadAuthentication);
            if(numLastResortWatchdogTriggersWithBadDhcp != 0)
                codedoutputbytebuffernano.writeInt32(28, numLastResortWatchdogTriggersWithBadDhcp);
            if(numLastResortWatchdogTriggersWithBadOther != 0)
                codedoutputbytebuffernano.writeInt32(29, numLastResortWatchdogTriggersWithBadOther);
            if(numConnectivityWatchdogPnoGood != 0)
                codedoutputbytebuffernano.writeInt32(30, numConnectivityWatchdogPnoGood);
            if(numConnectivityWatchdogPnoBad != 0)
                codedoutputbytebuffernano.writeInt32(31, numConnectivityWatchdogPnoBad);
            if(numConnectivityWatchdogBackgroundGood != 0)
                codedoutputbytebuffernano.writeInt32(32, numConnectivityWatchdogBackgroundGood);
            if(numConnectivityWatchdogBackgroundBad != 0)
                codedoutputbytebuffernano.writeInt32(33, numConnectivityWatchdogBackgroundBad);
            if(recordDurationSec != 0)
                codedoutputbytebuffernano.writeInt32(34, recordDurationSec);
            if(rssiPollRssiCount != null && rssiPollRssiCount.length > 0)
            {
                for(int j1 = 0; j1 < rssiPollRssiCount.length; j1++)
                {
                    RssiPollCount rssipollcount = rssiPollRssiCount[j1];
                    if(rssipollcount != null)
                        codedoutputbytebuffernano.writeMessage(35, rssipollcount);
                }

            }
            if(numLastResortWatchdogSuccesses != 0)
                codedoutputbytebuffernano.writeInt32(36, numLastResortWatchdogSuccesses);
            if(numHiddenNetworks != 0)
                codedoutputbytebuffernano.writeInt32(37, numHiddenNetworks);
            if(numPasspointNetworks != 0)
                codedoutputbytebuffernano.writeInt32(38, numPasspointNetworks);
            if(numTotalScanResults != 0)
                codedoutputbytebuffernano.writeInt32(39, numTotalScanResults);
            if(numOpenNetworkScanResults != 0)
                codedoutputbytebuffernano.writeInt32(40, numOpenNetworkScanResults);
            if(numPersonalNetworkScanResults != 0)
                codedoutputbytebuffernano.writeInt32(41, numPersonalNetworkScanResults);
            if(numEnterpriseNetworkScanResults != 0)
                codedoutputbytebuffernano.writeInt32(42, numEnterpriseNetworkScanResults);
            if(numHiddenNetworkScanResults != 0)
                codedoutputbytebuffernano.writeInt32(43, numHiddenNetworkScanResults);
            if(numHotspot2R1NetworkScanResults != 0)
                codedoutputbytebuffernano.writeInt32(44, numHotspot2R1NetworkScanResults);
            if(numHotspot2R2NetworkScanResults != 0)
                codedoutputbytebuffernano.writeInt32(45, numHotspot2R2NetworkScanResults);
            if(numScans != 0)
                codedoutputbytebuffernano.writeInt32(46, numScans);
            if(alertReasonCount != null && alertReasonCount.length > 0)
            {
                for(int k1 = 0; k1 < alertReasonCount.length; k1++)
                {
                    AlertReasonCount alertreasoncount = alertReasonCount[k1];
                    if(alertreasoncount != null)
                        codedoutputbytebuffernano.writeMessage(47, alertreasoncount);
                }

            }
            if(wifiScoreCount != null && wifiScoreCount.length > 0)
            {
                for(int l1 = 0; l1 < wifiScoreCount.length; l1++)
                {
                    WifiScoreCount wifiscorecount = wifiScoreCount[l1];
                    if(wifiscorecount != null)
                        codedoutputbytebuffernano.writeMessage(48, wifiscorecount);
                }

            }
            if(softApDuration != null && softApDuration.length > 0)
            {
                for(int i2 = 0; i2 < softApDuration.length; i2++)
                {
                    SoftApDurationBucket softapdurationbucket = softApDuration[i2];
                    if(softapdurationbucket != null)
                        codedoutputbytebuffernano.writeMessage(49, softapdurationbucket);
                }

            }
            if(softApReturnCode != null && softApReturnCode.length > 0)
            {
                for(int j2 = 0; j2 < softApReturnCode.length; j2++)
                {
                    SoftApReturnCodeCount softapreturncodecount = softApReturnCode[j2];
                    if(softapreturncodecount != null)
                        codedoutputbytebuffernano.writeMessage(50, softapreturncodecount);
                }

            }
            if(rssiPollDeltaCount != null && rssiPollDeltaCount.length > 0)
            {
                for(int k2 = 0; k2 < rssiPollDeltaCount.length; k2++)
                {
                    RssiPollCount rssipollcount1 = rssiPollDeltaCount[k2];
                    if(rssipollcount1 != null)
                        codedoutputbytebuffernano.writeMessage(51, rssipollcount1);
                }

            }
            if(staEventList != null && staEventList.length > 0)
            {
                for(int l2 = 0; l2 < staEventList.length; l2++)
                {
                    StaEvent staevent = staEventList[l2];
                    if(staevent != null)
                        codedoutputbytebuffernano.writeMessage(52, staevent);
                }

            }
            if(numHalCrashes != 0)
                codedoutputbytebuffernano.writeInt32(53, numHalCrashes);
            if(numWificondCrashes != 0)
                codedoutputbytebuffernano.writeInt32(54, numWificondCrashes);
            if(numWifiOnFailureDueToHal != 0)
                codedoutputbytebuffernano.writeInt32(55, numWifiOnFailureDueToHal);
            if(numWifiOnFailureDueToWificond != 0)
                codedoutputbytebuffernano.writeInt32(56, numWifiOnFailureDueToWificond);
            if(wifiAwareLog != null)
                codedoutputbytebuffernano.writeMessage(57, wifiAwareLog);
            if(numPasspointProviders != 0)
                codedoutputbytebuffernano.writeInt32(58, numPasspointProviders);
            if(numPasspointProviderInstallation != 0)
                codedoutputbytebuffernano.writeInt32(59, numPasspointProviderInstallation);
            if(numPasspointProviderInstallSuccess != 0)
                codedoutputbytebuffernano.writeInt32(60, numPasspointProviderInstallSuccess);
            if(numPasspointProviderUninstallation != 0)
                codedoutputbytebuffernano.writeInt32(61, numPasspointProviderUninstallation);
            if(numPasspointProviderUninstallSuccess != 0)
                codedoutputbytebuffernano.writeInt32(62, numPasspointProviderUninstallSuccess);
            if(numPasspointProvidersSuccessfullyConnected != 0)
                codedoutputbytebuffernano.writeInt32(63, numPasspointProvidersSuccessfullyConnected);
            if(totalSsidsInScanHistogram != null && totalSsidsInScanHistogram.length > 0)
            {
                for(int i3 = 0; i3 < totalSsidsInScanHistogram.length; i3++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket = totalSsidsInScanHistogram[i3];
                    if(numconnectablenetworksbucket != null)
                        codedoutputbytebuffernano.writeMessage(64, numconnectablenetworksbucket);
                }

            }
            if(totalBssidsInScanHistogram != null && totalBssidsInScanHistogram.length > 0)
            {
                for(int j3 = 0; j3 < totalBssidsInScanHistogram.length; j3++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket1 = totalBssidsInScanHistogram[j3];
                    if(numconnectablenetworksbucket1 != null)
                        codedoutputbytebuffernano.writeMessage(65, numconnectablenetworksbucket1);
                }

            }
            if(availableOpenSsidsInScanHistogram != null && availableOpenSsidsInScanHistogram.length > 0)
            {
                for(int k3 = 0; k3 < availableOpenSsidsInScanHistogram.length; k3++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket2 = availableOpenSsidsInScanHistogram[k3];
                    if(numconnectablenetworksbucket2 != null)
                        codedoutputbytebuffernano.writeMessage(66, numconnectablenetworksbucket2);
                }

            }
            if(availableOpenBssidsInScanHistogram != null && availableOpenBssidsInScanHistogram.length > 0)
            {
                for(int l3 = 0; l3 < availableOpenBssidsInScanHistogram.length; l3++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket3 = availableOpenBssidsInScanHistogram[l3];
                    if(numconnectablenetworksbucket3 != null)
                        codedoutputbytebuffernano.writeMessage(67, numconnectablenetworksbucket3);
                }

            }
            if(availableSavedSsidsInScanHistogram != null && availableSavedSsidsInScanHistogram.length > 0)
            {
                for(int i4 = 0; i4 < availableSavedSsidsInScanHistogram.length; i4++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket4 = availableSavedSsidsInScanHistogram[i4];
                    if(numconnectablenetworksbucket4 != null)
                        codedoutputbytebuffernano.writeMessage(68, numconnectablenetworksbucket4);
                }

            }
            if(availableSavedBssidsInScanHistogram != null && availableSavedBssidsInScanHistogram.length > 0)
            {
                for(int j4 = 0; j4 < availableSavedBssidsInScanHistogram.length; j4++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket5 = availableSavedBssidsInScanHistogram[j4];
                    if(numconnectablenetworksbucket5 != null)
                        codedoutputbytebuffernano.writeMessage(69, numconnectablenetworksbucket5);
                }

            }
            if(availableOpenOrSavedSsidsInScanHistogram != null && availableOpenOrSavedSsidsInScanHistogram.length > 0)
            {
                for(int k4 = 0; k4 < availableOpenOrSavedSsidsInScanHistogram.length; k4++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket6 = availableOpenOrSavedSsidsInScanHistogram[k4];
                    if(numconnectablenetworksbucket6 != null)
                        codedoutputbytebuffernano.writeMessage(70, numconnectablenetworksbucket6);
                }

            }
            if(availableOpenOrSavedBssidsInScanHistogram != null && availableOpenOrSavedBssidsInScanHistogram.length > 0)
            {
                for(int l4 = 0; l4 < availableOpenOrSavedBssidsInScanHistogram.length; l4++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket7 = availableOpenOrSavedBssidsInScanHistogram[l4];
                    if(numconnectablenetworksbucket7 != null)
                        codedoutputbytebuffernano.writeMessage(71, numconnectablenetworksbucket7);
                }

            }
            if(availableSavedPasspointProviderProfilesInScanHistogram != null && availableSavedPasspointProviderProfilesInScanHistogram.length > 0)
            {
                for(int i5 = 0; i5 < availableSavedPasspointProviderProfilesInScanHistogram.length; i5++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket8 = availableSavedPasspointProviderProfilesInScanHistogram[i5];
                    if(numconnectablenetworksbucket8 != null)
                        codedoutputbytebuffernano.writeMessage(72, numconnectablenetworksbucket8);
                }

            }
            if(availableSavedPasspointProviderBssidsInScanHistogram != null && availableSavedPasspointProviderBssidsInScanHistogram.length > 0)
            {
                for(int j5 = 0; j5 < availableSavedPasspointProviderBssidsInScanHistogram.length; j5++)
                {
                    NumConnectableNetworksBucket numconnectablenetworksbucket9 = availableSavedPasspointProviderBssidsInScanHistogram[j5];
                    if(numconnectablenetworksbucket9 != null)
                        codedoutputbytebuffernano.writeMessage(73, numconnectablenetworksbucket9);
                }

            }
            if(fullBandAllSingleScanListenerResults != 0)
                codedoutputbytebuffernano.writeInt32(74, fullBandAllSingleScanListenerResults);
            if(partialAllSingleScanListenerResults != 0)
                codedoutputbytebuffernano.writeInt32(75, partialAllSingleScanListenerResults);
            if(pnoScanMetrics != null)
                codedoutputbytebuffernano.writeMessage(76, pnoScanMetrics);
            if(connectToNetworkNotificationCount != null && connectToNetworkNotificationCount.length > 0)
            {
                for(int k5 = 0; k5 < connectToNetworkNotificationCount.length; k5++)
                {
                    ConnectToNetworkNotificationAndActionCount connecttonetworknotificationandactioncount = connectToNetworkNotificationCount[k5];
                    if(connecttonetworknotificationandactioncount != null)
                        codedoutputbytebuffernano.writeMessage(77, connecttonetworknotificationandactioncount);
                }

            }
            if(connectToNetworkNotificationActionCount != null && connectToNetworkNotificationActionCount.length > 0)
            {
                for(int l5 = 0; l5 < connectToNetworkNotificationActionCount.length; l5++)
                {
                    ConnectToNetworkNotificationAndActionCount connecttonetworknotificationandactioncount1 = connectToNetworkNotificationActionCount[l5];
                    if(connecttonetworknotificationandactioncount1 != null)
                        codedoutputbytebuffernano.writeMessage(78, connecttonetworknotificationandactioncount1);
                }

            }
            if(openNetworkRecommenderBlacklistSize != 0)
                codedoutputbytebuffernano.writeInt32(79, openNetworkRecommenderBlacklistSize);
            if(isWifiNetworksAvailableNotificationOn)
                codedoutputbytebuffernano.writeBool(80, isWifiNetworksAvailableNotificationOn);
            if(numOpenNetworkRecommendationUpdates != 0)
                codedoutputbytebuffernano.writeInt32(81, numOpenNetworkRecommendationUpdates);
            if(numOpenNetworkConnectMessageFailedToSend != 0)
                codedoutputbytebuffernano.writeInt32(82, numOpenNetworkConnectMessageFailedToSend);
            super.writeTo(codedoutputbytebuffernano);
        }

        public static final int FAILURE_WIFI_DISABLED = 4;
        public static final int SCAN_FAILURE_INTERRUPTED = 2;
        public static final int SCAN_FAILURE_INVALID_CONFIGURATION = 3;
        public static final int SCAN_SUCCESS = 1;
        public static final int SCAN_UNKNOWN = 0;
        public static final int WIFI_ASSOCIATED = 3;
        public static final int WIFI_DISABLED = 1;
        public static final int WIFI_DISCONNECTED = 2;
        public static final int WIFI_UNKNOWN = 0;
        private static volatile WifiLog _emptyArray[];
        public AlertReasonCount alertReasonCount[];
        public NumConnectableNetworksBucket availableOpenBssidsInScanHistogram[];
        public NumConnectableNetworksBucket availableOpenOrSavedBssidsInScanHistogram[];
        public NumConnectableNetworksBucket availableOpenOrSavedSsidsInScanHistogram[];
        public NumConnectableNetworksBucket availableOpenSsidsInScanHistogram[];
        public NumConnectableNetworksBucket availableSavedBssidsInScanHistogram[];
        public NumConnectableNetworksBucket availableSavedPasspointProviderBssidsInScanHistogram[];
        public NumConnectableNetworksBucket availableSavedPasspointProviderProfilesInScanHistogram[];
        public NumConnectableNetworksBucket availableSavedSsidsInScanHistogram[];
        public WifiSystemStateEntry backgroundScanRequestState[];
        public ScanReturnEntry backgroundScanReturnEntries[];
        public ConnectToNetworkNotificationAndActionCount connectToNetworkNotificationActionCount[];
        public ConnectToNetworkNotificationAndActionCount connectToNetworkNotificationCount[];
        public ConnectionEvent connectionEvent[];
        public int fullBandAllSingleScanListenerResults;
        public boolean isLocationEnabled;
        public boolean isScanningAlwaysEnabled;
        public boolean isWifiNetworksAvailableNotificationOn;
        public int numBackgroundScans;
        public int numConnectivityWatchdogBackgroundBad;
        public int numConnectivityWatchdogBackgroundGood;
        public int numConnectivityWatchdogPnoBad;
        public int numConnectivityWatchdogPnoGood;
        public int numEmptyScanResults;
        public int numEnterpriseNetworkScanResults;
        public int numEnterpriseNetworks;
        public int numHalCrashes;
        public int numHiddenNetworkScanResults;
        public int numHiddenNetworks;
        public int numHotspot2R1NetworkScanResults;
        public int numHotspot2R2NetworkScanResults;
        public int numLastResortWatchdogAvailableNetworksTotal;
        public int numLastResortWatchdogBadAssociationNetworksTotal;
        public int numLastResortWatchdogBadAuthenticationNetworksTotal;
        public int numLastResortWatchdogBadDhcpNetworksTotal;
        public int numLastResortWatchdogBadOtherNetworksTotal;
        public int numLastResortWatchdogSuccesses;
        public int numLastResortWatchdogTriggers;
        public int numLastResortWatchdogTriggersWithBadAssociation;
        public int numLastResortWatchdogTriggersWithBadAuthentication;
        public int numLastResortWatchdogTriggersWithBadDhcp;
        public int numLastResortWatchdogTriggersWithBadOther;
        public int numNetworksAddedByApps;
        public int numNetworksAddedByUser;
        public int numNonEmptyScanResults;
        public int numOneshotScans;
        public int numOpenNetworkConnectMessageFailedToSend;
        public int numOpenNetworkRecommendationUpdates;
        public int numOpenNetworkScanResults;
        public int numOpenNetworks;
        public int numPasspointNetworks;
        public int numPasspointProviderInstallSuccess;
        public int numPasspointProviderInstallation;
        public int numPasspointProviderUninstallSuccess;
        public int numPasspointProviderUninstallation;
        public int numPasspointProviders;
        public int numPasspointProvidersSuccessfullyConnected;
        public int numPersonalNetworkScanResults;
        public int numPersonalNetworks;
        public int numSavedNetworks;
        public int numScans;
        public int numTotalScanResults;
        public int numWifiOnFailureDueToHal;
        public int numWifiOnFailureDueToWificond;
        public int numWifiToggledViaAirplane;
        public int numWifiToggledViaSettings;
        public int numWificondCrashes;
        public int openNetworkRecommenderBlacklistSize;
        public int partialAllSingleScanListenerResults;
        public PnoScanMetrics pnoScanMetrics;
        public int recordDurationSec;
        public RssiPollCount rssiPollDeltaCount[];
        public RssiPollCount rssiPollRssiCount[];
        public ScanReturnEntry scanReturnEntries[];
        public SoftApDurationBucket softApDuration[];
        public SoftApReturnCodeCount softApReturnCode[];
        public StaEvent staEventList[];
        public NumConnectableNetworksBucket totalBssidsInScanHistogram[];
        public NumConnectableNetworksBucket totalSsidsInScanHistogram[];
        public WifiAwareLog wifiAwareLog;
        public WifiScoreCount wifiScoreCount[];
        public WifiSystemStateEntry wifiSystemStateEntries[];

        public WifiLog()
        {
            clear();
        }
    }

    public static final class WifiLog.ScanReturnEntry extends MessageNano
    {

        public static WifiLog.ScanReturnEntry[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiLog.ScanReturnEntry[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiLog.ScanReturnEntry parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiLog.ScanReturnEntry()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiLog.ScanReturnEntry parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiLog.ScanReturnEntry)MessageNano.mergeFrom(new WifiLog.ScanReturnEntry(), abyte0);
        }

        public WifiLog.ScanReturnEntry clear()
        {
            scanReturnCode = 0;
            scanResultsCount = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(scanReturnCode != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, scanReturnCode);
            i = j;
            if(scanResultsCount != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, scanResultsCount);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiLog.ScanReturnEntry mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L6:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 3: default 40
        //                       0: 50
        //                       8: 52
        //                       16: 103;
               goto _L1 _L2 _L3 _L4
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L6; else goto _L5
_L5:
            return this;
_L2:
            return this;
_L3:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
                scanReturnCode = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            scanResultsCount = codedinputbytebuffernano.readInt32();
            if(true) goto _L6; else goto _L7
_L7:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(scanReturnCode != 0)
                codedoutputbytebuffernano.writeInt32(1, scanReturnCode);
            if(scanResultsCount != 0)
                codedoutputbytebuffernano.writeInt32(2, scanResultsCount);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile WifiLog.ScanReturnEntry _emptyArray[];
        public int scanResultsCount;
        public int scanReturnCode;

        public WifiLog.ScanReturnEntry()
        {
            clear();
        }
    }

    public static final class WifiLog.WifiSystemStateEntry extends MessageNano
    {

        public static WifiLog.WifiSystemStateEntry[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiLog.WifiSystemStateEntry[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiLog.WifiSystemStateEntry parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiLog.WifiSystemStateEntry()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiLog.WifiSystemStateEntry parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiLog.WifiSystemStateEntry)MessageNano.mergeFrom(new WifiLog.WifiSystemStateEntry(), abyte0);
        }

        public WifiLog.WifiSystemStateEntry clear()
        {
            wifiState = 0;
            wifiStateCount = 0;
            isScreenOn = false;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(wifiState != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, wifiState);
            i = j;
            if(wifiStateCount != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, wifiStateCount);
            j = i;
            if(isScreenOn)
                j = i + CodedOutputByteBufferNano.computeBoolSize(3, isScreenOn);
            return j;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiLog.WifiSystemStateEntry mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
_L7:
            int i = codedinputbytebuffernano.readTag();
            i;
            JVM INSTR lookupswitch 4: default 48
        //                       0: 58
        //                       8: 60
        //                       16: 107
        //                       24: 118;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            if(WireFormatNano.parseUnknownField(codedinputbytebuffernano, i)) goto _L7; else goto _L6
_L6:
            return this;
_L2:
            return this;
_L3:
            int j = codedinputbytebuffernano.readInt32();
            switch(j)
            {
            case 0: // '\0'
            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
                wifiState = j;
                break;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            wifiStateCount = codedinputbytebuffernano.readInt32();
            continue; /* Loop/switch isn't completed */
_L5:
            isScreenOn = codedinputbytebuffernano.readBool();
            if(true) goto _L7; else goto _L8
_L8:
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(wifiState != 0)
                codedoutputbytebuffernano.writeInt32(1, wifiState);
            if(wifiStateCount != 0)
                codedoutputbytebuffernano.writeInt32(2, wifiStateCount);
            if(isScreenOn)
                codedoutputbytebuffernano.writeBool(3, isScreenOn);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile WifiLog.WifiSystemStateEntry _emptyArray[];
        public boolean isScreenOn;
        public int wifiState;
        public int wifiStateCount;

        public WifiLog.WifiSystemStateEntry()
        {
            clear();
        }
    }

    public static final class WifiScoreCount extends MessageNano
    {

        public static WifiScoreCount[] emptyArray()
        {
            if(_emptyArray != null) goto _L2; else goto _L1
_L1:
            Object obj = InternalNano.LAZY_INIT_LOCK;
            obj;
            JVM INSTR monitorenter ;
            if(_emptyArray == null)
                _emptyArray = new WifiScoreCount[0];
            obj;
            JVM INSTR monitorexit ;
_L2:
            return _emptyArray;
            Exception exception;
            exception;
            throw exception;
        }

        public static WifiScoreCount parseFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return (new WifiScoreCount()).mergeFrom(codedinputbytebuffernano);
        }

        public static WifiScoreCount parseFrom(byte abyte0[])
            throws InvalidProtocolBufferNanoException
        {
            return (WifiScoreCount)MessageNano.mergeFrom(new WifiScoreCount(), abyte0);
        }

        public WifiScoreCount clear()
        {
            score = 0;
            count = 0;
            cachedSize = -1;
            return this;
        }

        protected int computeSerializedSize()
        {
            int i = super.computeSerializedSize();
            int j = i;
            if(score != 0)
                j = i + CodedOutputByteBufferNano.computeInt32Size(1, score);
            i = j;
            if(count != 0)
                i = j + CodedOutputByteBufferNano.computeInt32Size(2, count);
            return i;
        }

        public volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
            throws IOException
        {
            return mergeFrom(codedinputbytebuffernano);
        }

        public WifiScoreCount mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
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
                    score = codedinputbytebuffernano.readInt32();
                    break;

                case 16: // '\020'
                    count = codedinputbytebuffernano.readInt32();
                    break;
                }
            } while(true);
        }

        public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
            throws IOException
        {
            if(score != 0)
                codedoutputbytebuffernano.writeInt32(1, score);
            if(count != 0)
                codedoutputbytebuffernano.writeInt32(2, count);
            super.writeTo(codedoutputbytebuffernano);
        }

        private static volatile WifiScoreCount _emptyArray[];
        public int count;
        public int score;

        public WifiScoreCount()
        {
            clear();
        }
    }

}
