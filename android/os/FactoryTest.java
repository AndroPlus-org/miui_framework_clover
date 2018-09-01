// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import com.android.internal.os.RoSystemProperties;

// Referenced classes of package android.os:
//            SystemProperties

public final class FactoryTest
{

    public FactoryTest()
    {
    }

    public static int getMode()
    {
        return RoSystemProperties.FACTORYTEST;
    }

    public static boolean isLongPressOnPowerOffEnabled()
    {
        boolean flag = false;
        if(SystemProperties.getInt("factory.long_press_power_off", 0) != 0)
            flag = true;
        return flag;
    }

    public static final int FACTORY_TEST_HIGH_LEVEL = 2;
    public static final int FACTORY_TEST_LOW_LEVEL = 1;
    public static final int FACTORY_TEST_OFF = 0;
}
