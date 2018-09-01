// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.power;


public final class WirelessChargerDetectorProto
{
    public final class VectorProto
    {

        public static final long X = 0x10200000001L;
        public static final long Y = 0x10200000002L;
        public static final long Z = 0x10200000003L;
        final WirelessChargerDetectorProto this$0;

        public VectorProto()
        {
            this$0 = WirelessChargerDetectorProto.this;
            super();
        }
    }


    public WirelessChargerDetectorProto()
    {
    }

    public static final long DETECTION_START_TIME_MS = 0x10400000005L;
    public static final long FIRST_SAMPLE = 0x11100000009L;
    public static final long IS_AT_REST = 0x10d00000002L;
    public static final long IS_DETECTION_IN_PROGRESS = 0x10d00000004L;
    public static final long IS_MUST_UPDATE_REST_POSITION = 0x10d00000006L;
    public static final long IS_POWERED_WIRELESSLY = 0x10d00000001L;
    public static final long LAST_SAMPLE = 0x1110000000aL;
    public static final long MOVING_SAMPLES = 0x10300000008L;
    public static final long REST = 0x11100000003L;
    public static final long TOTAL_SAMPLES = 0x10300000007L;
}
