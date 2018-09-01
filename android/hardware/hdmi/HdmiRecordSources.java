// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.util.Log;

public final class HdmiRecordSources
{
    public static final class AnalogueServiceSource extends RecordSource
    {

        int extraParamToByteArray(byte abyte0[], int i)
        {
            abyte0[i] = (byte)mBroadcastType;
            HdmiRecordSources._2D_wrap0((short)mFrequency, abyte0, i + 1);
            abyte0[i + 3] = (byte)mBroadcastSystem;
            return 4;
        }

        static final int EXTRA_DATA_SIZE = 4;
        private final int mBroadcastSystem;
        private final int mBroadcastType;
        private final int mFrequency;

        private AnalogueServiceSource(int i, int j, int k)
        {
            super(3, 4);
            mBroadcastType = i;
            mFrequency = j;
            mBroadcastSystem = k;
        }

        AnalogueServiceSource(int i, int j, int k, AnalogueServiceSource analogueservicesource)
        {
            this(i, j, k);
        }
    }

    public static final class AribData
        implements DigitalServiceIdentification
    {

        public int toByteArray(byte abyte0[], int i)
        {
            return HdmiRecordSources._2D_wrap1(mTransportStreamId, mServiceId, mOriginalNetworkId, abyte0, i);
        }

        private final int mOriginalNetworkId;
        private final int mServiceId;
        private final int mTransportStreamId;

        public AribData(int i, int j, int k)
        {
            mTransportStreamId = i;
            mServiceId = j;
            mOriginalNetworkId = k;
        }
    }

    public static final class AtscData
        implements DigitalServiceIdentification
    {

        public int toByteArray(byte abyte0[], int i)
        {
            return HdmiRecordSources._2D_wrap1(mTransportStreamId, mProgramNumber, 0, abyte0, i);
        }

        private final int mProgramNumber;
        private final int mTransportStreamId;

        public AtscData(int i, int j)
        {
            mTransportStreamId = i;
            mProgramNumber = j;
        }
    }

    private static final class ChannelIdentifier
    {

        static int _2D_wrap0(ChannelIdentifier channelidentifier, byte abyte0[], int i)
        {
            return channelidentifier.toByteArray(abyte0, i);
        }

        private int toByteArray(byte abyte0[], int i)
        {
            abyte0[i] = (byte)(mChannelNumberFormat << 2 | mMajorChannelNumber >>> 8 & 3);
            abyte0[i + 1] = (byte)(mMajorChannelNumber & 0xff);
            HdmiRecordSources._2D_wrap0((short)mMinorChannelNumber, abyte0, i + 2);
            return 4;
        }

        private final int mChannelNumberFormat;
        private final int mMajorChannelNumber;
        private final int mMinorChannelNumber;

        private ChannelIdentifier(int i, int j, int k)
        {
            mChannelNumberFormat = i;
            mMajorChannelNumber = j;
            mMinorChannelNumber = k;
        }

        ChannelIdentifier(int i, int j, int k, ChannelIdentifier channelidentifier)
        {
            this(i, j, k);
        }
    }

    public static final class DigitalChannelData
        implements DigitalServiceIdentification
    {

        public static DigitalChannelData ofOneNumber(int i)
        {
            return new DigitalChannelData(new ChannelIdentifier(1, 0, i, null));
        }

        public static DigitalChannelData ofTwoNumbers(int i, int j)
        {
            return new DigitalChannelData(new ChannelIdentifier(2, i, j, null));
        }

        public int toByteArray(byte abyte0[], int i)
        {
            ChannelIdentifier._2D_wrap0(mChannelIdentifier, abyte0, i);
            abyte0[i + 4] = (byte)0;
            abyte0[i + 5] = (byte)0;
            return 6;
        }

        private final ChannelIdentifier mChannelIdentifier;

        private DigitalChannelData(ChannelIdentifier channelidentifier)
        {
            mChannelIdentifier = channelidentifier;
        }
    }

    private static interface DigitalServiceIdentification
    {

        public abstract int toByteArray(byte abyte0[], int i);
    }

    public static final class DigitalServiceSource extends RecordSource
    {

        int extraParamToByteArray(byte abyte0[], int i)
        {
            abyte0[i] = (byte)(mIdentificationMethod << 7 | mBroadcastSystem & 0x7f);
            mIdentification.toByteArray(abyte0, i + 1);
            return 7;
        }

        private static final int DIGITAL_SERVICE_IDENTIFIED_BY_CHANNEL = 1;
        private static final int DIGITAL_SERVICE_IDENTIFIED_BY_DIGITAL_ID = 0;
        static final int EXTRA_DATA_SIZE = 7;
        private final int mBroadcastSystem;
        private final DigitalServiceIdentification mIdentification;
        private final int mIdentificationMethod;

        private DigitalServiceSource(int i, int j, DigitalServiceIdentification digitalserviceidentification)
        {
            super(2, 7);
            mIdentificationMethod = i;
            mBroadcastSystem = j;
            mIdentification = digitalserviceidentification;
        }

        DigitalServiceSource(int i, int j, DigitalServiceIdentification digitalserviceidentification, DigitalServiceSource digitalservicesource)
        {
            this(i, j, digitalserviceidentification);
        }
    }

    public static final class DvbData
        implements DigitalServiceIdentification
    {

        public int toByteArray(byte abyte0[], int i)
        {
            return HdmiRecordSources._2D_wrap1(mTransportStreamId, mServiceId, mOriginalNetworkId, abyte0, i);
        }

        private final int mOriginalNetworkId;
        private final int mServiceId;
        private final int mTransportStreamId;

        public DvbData(int i, int j, int k)
        {
            mTransportStreamId = i;
            mServiceId = j;
            mOriginalNetworkId = k;
        }
    }

    public static final class ExternalPhysicalAddress extends RecordSource
    {

        int extraParamToByteArray(byte abyte0[], int i)
        {
            HdmiRecordSources._2D_wrap0((short)mPhysicalAddress, abyte0, i);
            return 2;
        }

        static final int EXTRA_DATA_SIZE = 2;
        private final int mPhysicalAddress;

        private ExternalPhysicalAddress(int i)
        {
            super(5, 2);
            mPhysicalAddress = i;
        }

        ExternalPhysicalAddress(int i, ExternalPhysicalAddress externalphysicaladdress)
        {
            this(i);
        }
    }

    public static final class ExternalPlugData extends RecordSource
    {

        int extraParamToByteArray(byte abyte0[], int i)
        {
            abyte0[i] = (byte)mPlugNumber;
            return 1;
        }

        static final int EXTRA_DATA_SIZE = 1;
        private final int mPlugNumber;

        private ExternalPlugData(int i)
        {
            super(4, 1);
            mPlugNumber = i;
        }

        ExternalPlugData(int i, ExternalPlugData externalplugdata)
        {
            this(i);
        }
    }

    public static final class OwnSource extends RecordSource
    {

        int extraParamToByteArray(byte abyte0[], int i)
        {
            return 0;
        }

        private static final int EXTRA_DATA_SIZE = 0;

        private OwnSource()
        {
            super(1, 0);
        }

        OwnSource(OwnSource ownsource)
        {
            this();
        }
    }

    public static abstract class RecordSource
    {

        abstract int extraParamToByteArray(byte abyte0[], int i);

        final int getDataSize(boolean flag)
        {
            int i;
            if(flag)
                i = mExtraDataSize + 1;
            else
                i = mExtraDataSize;
            return i;
        }

        final int toByteArray(boolean flag, byte abyte0[], int i)
        {
            int j = i;
            if(flag)
            {
                abyte0[i] = (byte)mSourceType;
                j = i + 1;
            }
            extraParamToByteArray(abyte0, j);
            return getDataSize(flag);
        }

        final int mExtraDataSize;
        final int mSourceType;

        RecordSource(int i, int j)
        {
            mSourceType = i;
            mExtraDataSize = j;
        }
    }


    static int _2D_wrap0(short word0, byte abyte0[], int i)
    {
        return shortToByteArray(word0, abyte0, i);
    }

    static int _2D_wrap1(int i, int j, int k, byte abyte0[], int l)
    {
        return threeFieldsToSixBytes(i, j, k, abyte0, l);
    }

    private HdmiRecordSources()
    {
    }

    public static boolean checkRecordSource(byte abyte0[])
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        if(abyte0 == null || abyte0.length == 0)
            return false;
        byte byte0 = abyte0[0];
        int i = abyte0.length - 1;
        switch(byte0)
        {
        default:
            return false;

        case 1: // '\001'
            if(i != 0)
                flag4 = false;
            return flag4;

        case 2: // '\002'
            if(i == 7)
                flag4 = flag;
            else
                flag4 = false;
            return flag4;

        case 3: // '\003'
            if(i == 4)
                flag4 = flag1;
            else
                flag4 = false;
            return flag4;

        case 4: // '\004'
            if(i == 1)
                flag4 = flag2;
            else
                flag4 = false;
            return flag4;

        case 5: // '\005'
            break;
        }
        if(i == 2)
            flag4 = flag3;
        else
            flag4 = false;
        return flag4;
    }

    public static AnalogueServiceSource ofAnalogue(int i, int j, int k)
    {
        if(i < 0 || i > 2)
        {
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid Broadcast type:").append(i).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid Broadcast type:").append(i).toString());
        }
        if(j < 0 || j > 65535)
        {
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid frequency value[0x0000-0xFFFF]:").append(j).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid frequency value[0x0000-0xFFFF]:").append(j).toString());
        }
        if(k < 0 || k > 31)
        {
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid Broadcast system:").append(k).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid Broadcast system:").append(k).toString());
        } else
        {
            return new AnalogueServiceSource(i, j, k, null);
        }
    }

    public static DigitalServiceSource ofArib(int i, AribData aribdata)
    {
        if(aribdata == null)
            throw new IllegalArgumentException("data should not be null.");
        switch(i)
        {
        default:
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid ARIB type:").append(i).toString());
            throw new IllegalArgumentException("type should not be null.");

        case 0: // '\0'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            return new DigitalServiceSource(0, i, aribdata, null);
        }
    }

    public static DigitalServiceSource ofAtsc(int i, AtscData atscdata)
    {
        if(atscdata == null)
            throw new IllegalArgumentException("data should not be null.");
        switch(i)
        {
        default:
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid ATSC type:").append(i).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid ATSC type:").append(i).toString());

        case 1: // '\001'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
            return new DigitalServiceSource(0, i, atscdata, null);
        }
    }

    public static DigitalServiceSource ofDigitalChannelId(int i, DigitalChannelData digitalchanneldata)
    {
        if(digitalchanneldata == null)
            throw new IllegalArgumentException("data should not be null.");
        switch(i)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 19: // '\023'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        default:
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid broadcast type:").append(i).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid broadcast system value:").append(i).toString());

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 27: // '\033'
            return new DigitalServiceSource(1, i, digitalchanneldata, null);
        }
    }

    public static DigitalServiceSource ofDvb(int i, DvbData dvbdata)
    {
        if(dvbdata == null)
            throw new IllegalArgumentException("data should not be null.");
        switch(i)
        {
        default:
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid DVB type:").append(i).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid DVB type:").append(i).toString());

        case 2: // '\002'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 27: // '\033'
            return new DigitalServiceSource(0, i, dvbdata, null);
        }
    }

    public static ExternalPhysicalAddress ofExternalPhysicalAddress(int i)
    {
        if((0xffff0000 & i) != 0)
        {
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid physical address:").append(i).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid physical address:").append(i).toString());
        } else
        {
            return new ExternalPhysicalAddress(i, null);
        }
    }

    public static ExternalPlugData ofExternalPlug(int i)
    {
        if(i < 1 || i > 255)
        {
            Log.w("HdmiRecordSources", (new StringBuilder()).append("Invalid plug number[1-255]").append(i).toString());
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid plug number[1-255]").append(i).toString());
        } else
        {
            return new ExternalPlugData(i, null);
        }
    }

    public static OwnSource ofOwnSource()
    {
        return new OwnSource(null);
    }

    private static int shortToByteArray(short word0, byte abyte0[], int i)
    {
        abyte0[i] = (byte)(word0 >>> 8 & 0xff);
        abyte0[i + 1] = (byte)(word0 & 0xff);
        return 2;
    }

    private static int threeFieldsToSixBytes(int i, int j, int k, byte abyte0[], int l)
    {
        shortToByteArray((short)i, abyte0, l);
        shortToByteArray((short)j, abyte0, l + 2);
        shortToByteArray((short)k, abyte0, l + 4);
        return 6;
    }

    public static final int ANALOGUE_BROADCAST_TYPE_CABLE = 0;
    public static final int ANALOGUE_BROADCAST_TYPE_SATELLITE = 1;
    public static final int ANALOGUE_BROADCAST_TYPE_TERRESTRIAL = 2;
    public static final int BROADCAST_SYSTEM_NTSC_M = 3;
    public static final int BROADCAST_SYSTEM_PAL_BG = 0;
    public static final int BROADCAST_SYSTEM_PAL_DK = 8;
    public static final int BROADCAST_SYSTEM_PAL_I = 4;
    public static final int BROADCAST_SYSTEM_PAL_M = 2;
    public static final int BROADCAST_SYSTEM_PAL_OTHER_SYSTEM = 31;
    public static final int BROADCAST_SYSTEM_SECAM_BG = 6;
    public static final int BROADCAST_SYSTEM_SECAM_DK = 5;
    public static final int BROADCAST_SYSTEM_SECAM_L = 7;
    public static final int BROADCAST_SYSTEM_SECAM_LP = 1;
    private static final int CHANNEL_NUMBER_FORMAT_1_PART = 1;
    private static final int CHANNEL_NUMBER_FORMAT_2_PART = 2;
    public static final int DIGITAL_BROADCAST_TYPE_ARIB = 0;
    public static final int DIGITAL_BROADCAST_TYPE_ARIB_BS = 8;
    public static final int DIGITAL_BROADCAST_TYPE_ARIB_CS = 9;
    public static final int DIGITAL_BROADCAST_TYPE_ARIB_T = 10;
    public static final int DIGITAL_BROADCAST_TYPE_ATSC = 1;
    public static final int DIGITAL_BROADCAST_TYPE_ATSC_CABLE = 16;
    public static final int DIGITAL_BROADCAST_TYPE_ATSC_SATELLITE = 17;
    public static final int DIGITAL_BROADCAST_TYPE_ATSC_TERRESTRIAL = 18;
    public static final int DIGITAL_BROADCAST_TYPE_DVB = 2;
    public static final int DIGITAL_BROADCAST_TYPE_DVB_C = 24;
    public static final int DIGITAL_BROADCAST_TYPE_DVB_S = 25;
    public static final int DIGITAL_BROADCAST_TYPE_DVB_S2 = 26;
    public static final int DIGITAL_BROADCAST_TYPE_DVB_T = 27;
    private static final int RECORD_SOURCE_TYPE_ANALOGUE_SERVICE = 3;
    private static final int RECORD_SOURCE_TYPE_DIGITAL_SERVICE = 2;
    private static final int RECORD_SOURCE_TYPE_EXTERNAL_PHYSICAL_ADDRESS = 5;
    private static final int RECORD_SOURCE_TYPE_EXTERNAL_PLUG = 4;
    private static final int RECORD_SOURCE_TYPE_OWN_SOURCE = 1;
    private static final String TAG = "HdmiRecordSources";
}
