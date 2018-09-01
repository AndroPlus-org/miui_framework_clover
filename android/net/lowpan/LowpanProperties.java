// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;


// Referenced classes of package android.net.lowpan:
//            LowpanProperty

public final class LowpanProperties
{
    static final class LowpanStandardProperty extends LowpanProperty
    {

        public String getName()
        {
            return mName;
        }

        public Class getType()
        {
            return mType;
        }

        public String toString()
        {
            return getName();
        }

        private final String mName;
        private final Class mType;

        LowpanStandardProperty(String s, Class class1)
        {
            mName = s;
            mType = class1;
        }
    }


    private LowpanProperties()
    {
    }

    public static final LowpanProperty KEY_CHANNEL_MASK = new LowpanStandardProperty("android.net.lowpan.property.CHANNEL_MASK", [I);
    public static final LowpanProperty KEY_MAX_TX_POWER = new LowpanStandardProperty("android.net.lowpan.property.MAX_TX_POWER", java/lang/Integer);

}
