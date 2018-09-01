// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb.V1_0;


public class Constants
{
    public final class PortDataRole
    {

        public static final int DEVICE = 2;
        public static final int HOST = 1;
        public static final int NONE = 0;
        public static final int NUM_DATA_ROLES = 3;
        final Constants this$0;

        public PortDataRole()
        {
            this$0 = Constants.this;
            super();
        }
    }

    public final class PortMode
    {

        public static final int DFP = 2;
        public static final int DRP = 3;
        public static final int NONE = 0;
        public static final int NUM_MODES = 4;
        public static final int UFP = 1;
        final Constants this$0;

        public PortMode()
        {
            this$0 = Constants.this;
            super();
        }
    }

    public final class PortPowerRole
    {

        public static final int NONE = 0;
        public static final int NUM_POWER_ROLES = 3;
        public static final int SINK = 2;
        public static final int SOURCE = 1;
        final Constants this$0;

        public PortPowerRole()
        {
            this$0 = Constants.this;
            super();
        }
    }


    public Constants()
    {
    }
}
